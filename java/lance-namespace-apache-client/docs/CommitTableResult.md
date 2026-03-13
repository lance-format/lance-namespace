

# CommitTableResult

Result of a single operation within a batch commit. Each result corresponds to one operation in the request, in the same order. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**type** | [**TypeEnum**](#TypeEnum) |  |  |
|**id** | **List&lt;String&gt;** | The table identifier |  |
|**location** | **String** | The storage location of the deregistered table |  [optional] |
|**version** | [**TableVersion**](TableVersion.md) | The created table version details |  |
|**deletedCount** | **Long** | Number of version records deleted |  |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| DECLARE_TABLE | &quot;declare_table&quot; |
| CREATE_TABLE_VERSION | &quot;create_table_version&quot; |
| DELETE_TABLE_VERSIONS | &quot;delete_table_versions&quot; |
| DEREGISTER_TABLE | &quot;deregister_table&quot; |



