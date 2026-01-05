# Partitioned Namespace Spec

Partitioning is a common data organization strategy that divides data into physically separated units.
Lance tables do not natively support partitioning, instead promoting clustering to achieve similar performance benefits.

However, there are use cases where true partitioning makes sense.
For example, an organization might want to store one table per business unit, 
where each table is fully isolated yet shares a common schema and data management lifecycle.
Most of the time, queries like vector search are only against a specific partition, but sometimes 
it would be convenient to query across all business units as a unified dataset.

A **Partitioned Namespace** is designed for these use cases.
It is a [Directory Namespace](catalog-spec.md) containing a collection of tables that share a common schema.
These tables are physically separated and independent, but logically related through partition fields definition.

This document defines the storage format for Partitioned Namespace.
Similar to Lance being a storage-only format, the storage-only [Directory Namespace](catalog-spec.md) spec serves as the foundation for this Partitioned Namespace format.

The following example illustrates the storage layout of a partitioned namespace:

```text
/root
    __manifest table (Lance table)
    ┌─────────────────────────────────────────────────────────────────────┐
    │ Table metadata (root namespace properties):                         │
    │     - schema = <shared Schema>                                      │
    │     - partition_spec_v1 = [event_date]                              │
    │     - partition_spec_v2 = [event_year, country]                     │
    └─────────────────────────────────────────────────────────────────────┘
                              │
                      Spec Version Level
                              │
              ┬───────────────────────────────────────────────────────────┐
              │                                                           │
             v1                                                          v2
          (Namespace)                                                 (Namespace)
              │                                                           │
              │── <id1>  ← event_date=2025-12-10                          │── <id3>  ← event_year=2025
              │     └── dataset  (Table)                                  │     │
              │                                                           │     └── <id4>  ← country=US
              │── <id2>  ← event_date=2025-12-11                          │           └── dataset  (Table)
              │     └── dataset  (Table)                                  │
              └── ...                                                     └── ...
```

## Metadata Definition

A directory namespace is identified as a partitioned namespace if the `__manifest` table's 
[metadata](catalog-spec.md#root-namespace-properties) contains at least one partition spec version key.

The following properties are stored in the `__manifest` table's metadata map:

- `partition_spec_v<N>` (String): A JSON string representing an array of partition column definition objects for version N. Each object describes a partition column. See [Partitioning](#partitioning) for details.
- `schema` (String): A json string describing the Schema of the entire partitioned namespace, based on the `JsonArrowSchema` schema in client spec. See [Namespace Schema](#schema) for more details.

See [Appendix A: Metadata Example](#appendix-a-metadata-example) for a complete example.

## Schema

The namespace `schema` defines the schema for all partition tables in the partitioned namespace.
Implementations must enforce that all partition table schemas must be consistent with each other, as well as with the namespace schema.
Most importantly, each field in the schema has a unique field ID stored in metadata under the key `lance:field_id`.
Field IDs are never reused and must remain consistent across partition tables.
This ensures partition specs using `source_ids` remain valid even if columns are renamed.

## Partitioning

Partitioning defines how to derive partition values from a record in a partitioned namespace.
The partitioning information is stored in `partition_spec_v<N>` (e.g., `partition_spec_v1`),
which is a JSON array of partition field objects. Each partition field contains:

* A **field id** uniquely identifying this partition field (also used as the column name in `__manifest`)
* A **list of source field ids** referencing fields in the namespace schema
* A **partition expression** that transforms the source field values into a partition value
* A **result type** declaring the output type of the partition expression

| Field             | JSON representation | Example                     | Description                                                              |
|-------------------|---------------------|-----------------------------|--------------------------------------------------------------------------|
| **`field_id`**    | `JSON string`       | `"event_year"`              | Unique identifier for this partition field (must not be renamed)         |
| **`source_ids`**  | `JSON int array`    | `[1]`                       | Field IDs of the source columns in the schema                            |
| **`expression`**  | `JSON string`       | `"date_part('year', col0)"` | DataFusion SQL expression using `col0`, `col1`, ... as column references |
| **`result_type`** | `JSON object`       | `{ "type": "int32" }`       | The output type of the expression (JsonArrowDataType format)             |

**Partition Field ID**: The `field_id` is a string that uniquely identifies each partition field across all spec versions. It is used as the column name suffix in `__manifest` (e.g., `partition_field_event_year`). Once assigned, a `field_id` must never be renamed or reused. This ensures stable column names in the manifest table.

**Field ID Reuse**: When evolving partition specs, if a new partition field has the same `source_ids` and `expression` as an existing field, the same `field_id` must be reused. Otherwise, a new unique `field_id` must be assigned.

**Source Field IDs**: The `source_ids` array references field IDs stored in the schema's field metadata under the key `lance:field_id`. Using field IDs instead of column names ensures that partition specs remain valid even when source columns are renamed. In the partition expression, source columns are referenced as `col0`, `col1`, `col2`, etc., corresponding to the order of field IDs in the `source_ids` array.

### Partition Expression

The `expression` field contains a [DataFusion SQL expression](https://datafusion.apache.org/user-guide/sql/index.html) that transforms source column values into a partition value.
The placeholders `col0`, `col1`, `col2`, etc. represent the source columns in order corresponding to the `source_ids` array.
For single-column partitions, only `col0` is used.
The expression result type is declared by the `result_type` field.

All partition expressions must satisfy the following requirements:

1. **Deterministic**: The same input value must always produce the same output value.
2. **Stateless**: The expression must not depend on external state (e.g., current time, random values, session variables).
3. **Type-promotion resistant**: The expression must produce the same result for equivalent values regardless of their numeric type (e.g., `int32(5)` and `int64(5)` must yield the same partition value).
4. **Column removal resistant**: If a source field ID is not found in the schema, the column should be interpreted as NULL. For multi-column transforms, the expression should ignore removed columns and produce NULL only if all source columns are removed.

The common expressions listed in [Appendix D](#appendix-d-common-partition-expressions) satisfy these requirements.

The `result_type` field declares the output type of the partition expression using [JsonArrowDataType](https://lance.org/format/namespace/client/operations/models/JsonArrowDataType/) format.
This enables type checking without expression evaluation and ensures consistency across implementations.

## Physical Layout and Naming

A partitioned namespace supports multi-level partitioning with the following physical hierarchy:

- **Root Namespace**: The root namespace is implicit and represented by the `__manifest` table itself. Its properties (partition specs, schema) are stored in the `__manifest` table's metadata.
- **Spec Version Namespace**: The first-level child namespace, named `v1`, `v2`, etc. This identifies which partition spec version the data underneath was written with. When retrieving properties via API, these namespaces dynamically include a `partition_spec` property containing the partition spec for that version (copied from the root's `partition_spec_v<N>`).
- **Partition Namespace**: Each subsequent level of child namespaces represents a partition field. The order of partition namespace levels corresponds to the order of partition fields in the partition spec. Namespace names are randomly generated identifiers (see [Namespace Naming](#partition-namespace-naming)).
- **Partition Table**: At the end of the partition hierarchy, a `Table` object with the fixed name `dataset` contains the actual data. This is a standard, independently accessible Lance `Dataset` containing a subset of the partitioned namespace's data.

See [Appendix B: Physical Layout Example](#appendix-b-physical-layout-example) for a complete directory structure example.

### Partition Namespace Naming

Partition namespaces use **random identifier naming** to avoid issues with special characters in partition values.

Partition namespace names are randomly generated 16-character base36 strings (using characters `a-z0-9`).
This provides ~83 bits of entropy, ensuring virtually zero collision probability for any practical number of partitions.
This approach ensures:

- No conflicts with reserved characters (e.g., `$`, `/`, `=`) that may appear in partition column values
- Consistent namespace names across different client implementations
- Fixed-length, predictable namespace identifiers

Since namespace names are random identifiers,
the actual partition values are stored in the `__manifest` table's partition columns (see [Manifest Table Schema](#manifest-table-schema)).

### Runtime Namespace Properties

Since namespace names are random identifiers, the actual partition values are stored in the
`__manifest` table's partition columns (see [Manifest Table Schema](#manifest-table-schema)).

Implementations may dynamically populate properties when retrieving namespace information via API:

- For partition namespaces: `partition.<field_id> = <value>` entries
- For spec version namespaces (v1, v2, etc.): `partition_spec` containing the partition spec for that version

These runtime properties are optional. Implementations may choose not to expose them for security or other reasons.
See [Appendix F: Runtime Namespace Properties Example](#appendix-f-runtime-namespace-properties-example) for examples.

## Partition Pruning

Partition pruning is performed via the `__manifest` table, which contains partition column values for efficient filtering.

### Manifest Table Schema

The `__manifest` table schema is extended to include partition columns for efficient partition pruning. Instead of parsing namespace names to filter partitions, query engines can directly push down predicates to the manifest table.

**Extended Schema**: For each partition field defined in any partition spec version, the `__manifest` table includes an additional nullable column. The column name is `partition_field_{i}` where `{i}` is the partition field's `field_id`, and the type is the partition field's `result_type`. This naming convention avoids potential conflicts with user-defined column names. When a new partition spec version is defined, the `__manifest` table schema is updated accordingly to include any new partition columns.

| Column                       | Type     | Description                                                                 |
|------------------------------|----------|-----------------------------------------------------------------------------|
| `object_id`                  | `string` | Full namespace path with `$` separator (existing)                           |
| `object_type`                | `string` | `"namespace"` or `"table"` (existing)                                       |
| `metadata`                   | `string` | JSON-encoded metadata/properties (existing)                                 |
| `read_version`               | `uint64` | Table version for reads (optional, see [Transaction](#transaction))         |
| `read_branch`                | `string` | Table branch for reads (optional, see [Transaction](#transaction))          |
| `read_tag`                   | `string` | Table tag for reads (optional, see [Transaction](#transaction))             |
| `partition_field_{field_id}` | `<type>` | Partition value for the field (nullable, inherited from parent namespaces)  |
| ...                          | ...      | Additional partition field columns as needed                                |

Partition values are inherited from parent namespaces - each row has all partition values from its ancestors. See [Appendix C: Manifest Table Example](#appendix-c-manifest-table-example) for a complete example.

### Partition Pruning Workflow

1. Query engine analyzes the query predicate to identify filters on partition columns
2. For each partition expression, the engine evaluates the expression with the query values to compute the expected partition value(s)
3. Engine queries `__manifest` with filters on the partition columns
4. Engine retrieves the paths of matching `dataset` tables
5. Engine scans only the relevant partition tables

## Partition Evolution

The partition spec supports **versioning** to allow partition strategies to evolve over time. 
Each partition spec version defines its own set of partition columns and expressions. 
Data written to the partitioned namespace records which spec version it was created under via the version namespace (`v1/`, `v2/`, etc.).

**Evolution Scenarios**:

- **Adding partition columns**: Create a new spec version with additional partition columns. New data is written under the new version while existing partitions remain accessible.
- **Changing partition expressions**: Create a new spec version with different expressions (e.g., changing from daily to yearly partitioning). Both versions coexist.
- **Removing partition columns**: Create a new spec version without certain columns. Legacy data under old versions remains queryable.

**Compatibility**:

When querying across multiple spec versions, the query engine must handle each version according to its partition spec. 
For example, if `v1` partitions by `event_date` and `v2` partitions by `year(event_date)`, a query filtering on `event_date = '2025-12-10'` will:

1. Match exact partitions in `v1`
2. Compute `year('2025-12-10') = 2025` and scan all matching year partitions in `v2`

This design ensures backward compatibility while enabling partition strategy evolution without data migration.

## Transaction

### Single-Partition Transaction

Operations within a single partition table are ACID-compliant according to the Lance table specification.
Each partition is an independent Lance table, so reads and writes to a single partition follow standard Lance transaction semantics.

### Multi-Partition Transaction

By default, operations across multiple partitions have weaker guarantees:

- **Writes across partitions are not atomic or consistent**: A write that affects multiple partitions may partially succeed, leaving some partitions updated while others are not.
- **Reads across partitions are not isolated**: A read spanning multiple partitions may observe different versions of each partition, leading to inconsistent views.

To enable stronger transactional guarantees across partitions, the `__manifest` table can optionally include `read_version`, `read_branch`, and `read_tag` columns for a table.
These columns record which version of each partition table to read.

#### Read Behavior

Users should specify one of the following combinations:

1. **`read_version` only**: Read the specified version from the main branch.
2. **`read_branch` + `read_version`**: Read the specified version from the specified branch.
3. **`read_tag` only**: Read the version referenced by the specified tag.

When all columns are NULL or not present, readers should read the latest version from the main branch.

#### Commit Behavior

Multi-partition transactions are guarded by commits against the `__manifest` table. A typical multi-partition write follows this pattern:

1. Write data to each affected partition table independently
2. Atomically update the `read_version` (and optionally `read_branch` or `read_tag`) of all affected partitions in a single `__manifest` commit

This ensures all-or-nothing visibility of changes across partitions.

#### Conflict Resolution

If concurrent commits have been committed to `__manifest` since the transaction began, the implementation must either:

1. Rebase the current commit onto the latest `__manifest` version and retry the commit, or
2. Fail the current commit and return an error to the caller

Implementations are responsible for ensuring the appropriate conflict detection and resolution strategy to guarantee ACID semantics during multi-partition transactions.

## Appendices

### Appendix A: Metadata Example

A complete example of partitioned namespace metadata properties with two spec versions:

```json
{
  "partition_spec_v1": [
    {
      "field_id": "event_date",
      "source_ids": [1],
      "expression": "col0",
      "result_type": { "type": "date32" }
    }
  ],
  "partition_spec_v2": [
    {
      "field_id": "event_year",
      "source_ids": [1],
      "expression": "date_part('year', col0)",
      "result_type": { "type": "int32" }
    },
    {
      "field_id": "country",
      "source_ids": [2],
      "expression": "col0",
      "result_type": { "type": "utf8" }
    }
  ],
  "schema": {
    "fields": [
      {
        "name": "id",
        "nullable": false,
        "type": { "type": "int64" },
        "metadata": { "lance:field_id": "0" }
      },
      {
        "name": "event_date",
        "nullable": true,
        "type": { "type": "date32" },
        "metadata": { "lance:field_id": "1" }
      },
      {
        "name": "country",
        "nullable": true,
        "type": { "type": "utf8" },
        "metadata": { "lance:field_id": "2" }
      }
    ]
  }
}
```

In this example:
- `v1` partitions by `event_date` using the identity expression with `result_type: date32`
- `v2` partitions first by year of `event_date` with `result_type: int32`, then by `country` with `result_type: utf8`
- The `__manifest` table will have three partition columns: `partition_field_event_date` (date32), `partition_field_event_year` (int32), `partition_field_country` (utf8)
- The schema follows [JsonArrowSchema](https://lance.org/format/namespace/client/operations/models/JsonArrowSchema/) format

### Appendix B: Physical Layout Example

A partitioned namespace with two spec versions (`v1` partitioned by `event_date`, `v2` partitioned by `event_year` and `country`) in [V2 Manifest](https://lance.org/format/namespace/dir/catalog-spec/#v2-manifest):

Namespaces exist only as entries in the `__manifest` table - they do not have physical directories. Only tables (the leaf `dataset` objects) have directories, following the V2 format `<hash>_<object_id>`.

```text
.
└── /my/dir1/
    ├── __manifest/                                                 # The manifest table
    │   ├── data/
    │   │   └── ...
    │   └── _versions/
    │       └── ...
    ├── b4a3c2d1_v1$k7m2n9p4q8r5s3t6$dataset/                       # Table: event_date=2025-12-10
    │   └── ...
    ├── 55667788_v1$w1x2y3z4a5b6c7d8$dataset/                       # Table: event_date=2025-12-11
    │   └── ...
    ├── aabbccdd_v2$e9f0g1h2i3j4k5l6$m7n8o9p0q1r2s3t4$dataset/      # Table: event_year=2025, country=US
    │   └── ...
    └── ...
```

The namespaces (`v1`, `v1$k7m2n9p4q8r5s3t6`, etc.) are tracked in the `__manifest` table but have no corresponding directories.

### Appendix C: Manifest Table Example

The `__manifest` table for a partitioned namespace with partition fields `event_date` (v1), `event_year` (v2) and `country` (v2), showing entries from both spec versions:

| object_id                                     | object_type | metadata | read_version | read_branch | read_tag | partition_field_event_date | partition_field_event_year | partition_field_country |
|-----------------------------------------------|-------------|----------|--------------|-------------|----------|----------------------------|----------------------------|-------------------------|
| v1                                            | namespace   | {}       | NULL         | NULL        | NULL     | NULL                       | NULL                       | NULL                    |
| v1$k7m2n9p4q8r5s3t6                           | namespace   | {}       | NULL         | NULL        | NULL     | 2025-12-10                 | NULL                       | NULL                    |
| v1$k7m2n9p4q8r5s3t6$dataset                   | table       | {}       | 5            | NULL        | NULL     | 2025-12-10                 | NULL                       | NULL                    |
| v2                                            | namespace   | {}       | NULL         | NULL        | NULL     | NULL                       | NULL                       | NULL                    |
| v2$e9f0g1h2i3j4k5l6                           | namespace   | {}       | NULL         | NULL        | NULL     | NULL                       | 2025                       | NULL                    |
| v2$e9f0g1h2i3j4k5l6$m7n8o9p0q1r2s3t4          | namespace   | {}       | NULL         | NULL        | NULL     | NULL                       | 2025                       | US                      |
| v2$e9f0g1h2i3j4k5l6$m7n8o9p0q1r2s3t4$dataset  | table       | {}       | 3            | NULL        | NULL     | NULL                       | 2025                       | US                      |

Note: The root namespace properties (`partition_spec_v1`, `partition_spec_v2`, `schema`) are stored in the `__manifest` table's metadata, not as a row. The `object_id` uses `$` as the namespace path separator. Partition columns use the naming convention `partition_field_{field_id}` where `{field_id}` is the partition field's string identifier. Partition values are inherited from parent namespaces. When retrieving properties via API, partition values are converted to `partition.<field_id> = <value>` entries.

See [Appendix E: Partition Pruning Example](#appendix-e-partition-pruning-example) for an example of how partition pruning queries work.

### Appendix D: Common Partition Expressions

This appendix provides commonly used partition expressions.
Single-column expressions use `col0`; multi-column expressions use `col0`, `col1`, etc.

| Name                    | Expression                              | Result Type  | Description                              |
|-------------------------|-----------------------------------------|--------------|------------------------------------------|
| `identity`              | `col0`                                  | same as col0 | Source value, unmodified                 |
| `year`                  | `date_part('year', col0)`               | `int32`      | Extract year from date/timestamp         |
| `month`                 | `date_part('month', col0)`              | `int32`      | Extract month (1-12) from date/timestamp |
| `day`                   | `date_part('day', col0)`                | `int32`      | Extract day of month from date/timestamp |
| `hour`                  | `date_part('hour', col0)`               | `int32`      | Extract hour (0-23) from timestamp       |
| `bucket[N]`             | `abs(md5_int(col0)) % N`                | `int64`      | Hash single column into N buckets        |
| `multi_bucket[N]`       | `abs(md5_int(concat(col0, col1))) % N`  | `int64`      | Hash multiple columns into N buckets     |
| `truncate[W]` (string)  | `left(col0, W)`                         | `utf8`       | First W characters of string             |
| `truncate[W]` (numeric) | `col0 - (col0 % W)`                     | same as col0 | Truncate numeric to width W              |

- **Hash function**: The `md5_int()` function computes MD5 and returns the first 8 bytes as a signed 64-bit integer. This is equivalent to `cast(substr(md5(col), 1, 8) as bigint)` in DataFusion. The function returns NULL if any input is NULL.

### Appendix E: Partition Pruning Example

This example demonstrates how a query engine translates a user query into a partition pruning query against the `__manifest` table.

Given a user query:

```sql
SELECT * FROM partitioned_namespace
WHERE event_date = '2025-12-10' AND country = 'US'
```

The engine translates this to the following `__manifest` DataFusion query plan to examine related partition tables.

```sql
SELECT object_id, location, read_version, read_branch, read_tag
FROM __manifest
WHERE object_type = 'table'
  AND (
    (object_id LIKE 'v1$%'
      AND partition_field_event_date = DATE '2025-12-10')
    OR
    (object_id LIKE 'v2$%'
      AND partition_field_event_year = date_part('year', DATE '2025-12-10')
      AND partition_field_country = 'US')
  )
```
Notice here that the query plan can leverage the partition expression, in this case `date_part('year', col0)`.
One example way to perform such substitution is:

1. Parsing the expression string (e.g., `date_part('year', col0)`) into an expression AST using DataFusion's SQL parser
2. Traversing the AST and replacing all `col0`, `col1`, etc. column references with the corresponding literal query values (e.g., `DATE '2025-12-10'`)
3. Evaluating the modified expression to produce the partition filter value (e.g., `2025`)

This query returns:

| object_id                                    | location                                              | read_version | read_branch | read_tag |
|----------------------------------------------|-------------------------------------------------------|--------------|-------------|----------|
| v1$k7m2n9p4q8r5s3t6$dataset                  | b4a3c2d1_v1$k7m2n9p4q8r5s3t6$dataset                  | 5            | NULL        | NULL     |
| v2$e9f0g1h2i3j4k5l6$m7n8o9p0q1r2s3t4$dataset | aabbccdd_v2$e9f0g1h2i3j4k5l6$m7n8o9p0q1r2s3t4$dataset | 3            | NULL        | NULL     |

- For v1, the `country = 'US'` filter cannot be pushed to partition pruning (v1 has no `country` partition), so it must be applied during the table scan
- For v2, both filters are pushed down: `partition_field_event_year = 2025` (computed from `year(event_date)`) and `partition_field_country = 'US'`
- The engine reads each table at the version specified by `read_version`, `read_branch`, or `read_tag` for consistent snapshot reads

### Appendix F: Runtime Namespace Properties Example

This appendix shows examples of runtime properties that implementations MAY return when describing namespaces.
These are optional behaviors - implementations may choose not to expose them for security or other reasons.

**Spec Version Namespace**

`DescribeNamespace(["v1"])` returns:

```json
{
  "properties": {
    "partition_spec": "[{\"field_id\":\"event_date\",\"source_ids\":[1],\"expression\":\"col0\",\"result_type\":{\"type\":\"date32\"}}]"
  }
}
```

**Partition Namespace (v1)**

`DescribeNamespace(["v1", "k7m2n9p4q8r5s3t6"])` returns:

```json
{
  "properties": {
    "partition.event_date": "2025-12-10"
  }
}
```

**Partition Namespace (v2, first level)**

`DescribeNamespace(["v2", "e9f0g1h2i3j4k5l6"])` returns:

```json
{
  "properties": {
    "partition.event_year": "2025"
  }
}
```

**Partition Namespace (v2, second level)**

`DescribeNamespace(["v2", "e9f0g1h2i3j4k5l6", "m7n8o9p0q1r2s3t4"])` returns:

```json
{
  "properties": {
    "partition.country": "US"
  }
}
```

Note: Each namespace only returns the partition value for its own level. 
To get all partition values in a path, the client must query each ancestor namespace.
