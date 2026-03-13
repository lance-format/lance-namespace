# CommitTableOperationDeclareTable

Declare (reserve) a new table in the namespace.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**location** | **str** | Optional storage location for the table | [optional] 
**properties** | **Dict[str, str]** | Optional table properties | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_operation_declare_table import CommitTableOperationDeclareTable

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableOperationDeclareTable from a JSON string
commit_table_operation_declare_table_instance = CommitTableOperationDeclareTable.from_json(json)
# print the JSON string representation of the object
print(CommitTableOperationDeclareTable.to_json())

# convert the object into a dict
commit_table_operation_declare_table_dict = commit_table_operation_declare_table_instance.to_dict()
# create an instance of CommitTableOperationDeclareTable from a dict
commit_table_operation_declare_table_from_dict = CommitTableOperationDeclareTable.from_dict(commit_table_operation_declare_table_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


