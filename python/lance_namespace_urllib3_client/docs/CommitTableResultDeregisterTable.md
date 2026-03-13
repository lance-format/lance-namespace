# CommitTableResultDeregisterTable

Result of a deregister_table operation.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**location** | **str** | The storage location of the deregistered table | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result_deregister_table import CommitTableResultDeregisterTable

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResultDeregisterTable from a JSON string
commit_table_result_deregister_table_instance = CommitTableResultDeregisterTable.from_json(json)
# print the JSON string representation of the object
print(CommitTableResultDeregisterTable.to_json())

# convert the object into a dict
commit_table_result_deregister_table_dict = commit_table_result_deregister_table_instance.to_dict()
# create an instance of CommitTableResultDeregisterTable from a dict
commit_table_result_deregister_table_from_dict = CommitTableResultDeregisterTable.from_dict(commit_table_result_deregister_table_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


