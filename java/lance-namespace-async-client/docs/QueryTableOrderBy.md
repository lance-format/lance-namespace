

# QueryTableOrderBy

Ordering of a source field, matching Lance Scanner ColumnOrdering. Multiple entries are evaluated in list order. Strings use UTF-8 binary lexicographic order and floating-point values use IEEE 754 total order.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**columnName** | **String** | Source Lance field path to sort by, independent of output aliases. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments. |  |
|**ascending** | **Boolean** | Whether to sort in ascending order. |  [optional] |
|**nullsFirst** | **Boolean** | Whether null values precede non-null values, independent of direction. |  [optional] |



