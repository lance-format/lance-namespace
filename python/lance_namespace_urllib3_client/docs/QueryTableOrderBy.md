# QueryTableOrderBy

Ordering of a source field, matching Lance Scanner ColumnOrdering. Multiple entries are evaluated in list order. Strings use UTF-8 binary lexicographic order and floating-point values use IEEE 754 total order.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**column_name** | **str** | Source Lance field path to sort by, independent of output aliases. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments. | 
**ascending** | **bool** | Whether to sort in ascending order. | [optional] [default to True]
**nulls_first** | **bool** | Whether null values precede non-null values, independent of direction. | [optional] [default to False]

## Example

```python
from lance_namespace_urllib3_client.models.query_table_order_by import QueryTableOrderBy

# TODO update the JSON string below
json = "{}"
# create an instance of QueryTableOrderBy from a JSON string
query_table_order_by_instance = QueryTableOrderBy.from_json(json)
# print the JSON string representation of the object
print(QueryTableOrderBy.to_json())

# convert the object into a dict
query_table_order_by_dict = query_table_order_by_instance.to_dict()
# create an instance of QueryTableOrderBy from a dict
query_table_order_by_from_dict = QueryTableOrderBy.from_dict(query_table_order_by_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


