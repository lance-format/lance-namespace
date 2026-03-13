# CommitTableOperationDeregisterTable

Deregister (soft-delete) a table. The table's physical data remains intact.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_operation_deregister_table import CommitTableOperationDeregisterTable

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableOperationDeregisterTable from a JSON string
commit_table_operation_deregister_table_instance = CommitTableOperationDeregisterTable.from_json(json)
# print the JSON string representation of the object
print(CommitTableOperationDeregisterTable.to_json())

# convert the object into a dict
commit_table_operation_deregister_table_dict = commit_table_operation_deregister_table_instance.to_dict()
# create an instance of CommitTableOperationDeregisterTable from a dict
commit_table_operation_deregister_table_from_dict = CommitTableOperationDeregisterTable.from_dict(commit_table_operation_deregister_table_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


