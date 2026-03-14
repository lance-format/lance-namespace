# CommitTableResult

Result of a single operation within a batch commit. Each result corresponds to one operation in the request, in the same order. Exactly one of the result fields will be set. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**declare_table** | [**DeclareTableResponse**](DeclareTableResponse.md) | Result of a DeclareTable operation | [optional] 
**create_table_version** | [**CreateTableVersionResponse**](CreateTableVersionResponse.md) | Result of a CreateTableVersion operation | [optional] 
**delete_table_versions** | [**BatchDeleteTableVersionsResponse**](BatchDeleteTableVersionsResponse.md) | Result of a DeleteTableVersions operation | [optional] 
**deregister_table** | [**DeregisterTableResponse**](DeregisterTableResponse.md) | Result of a DeregisterTable operation | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result import CommitTableResult

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResult from a JSON string
commit_table_result_instance = CommitTableResult.from_json(json)
# print the JSON string representation of the object
print(CommitTableResult.to_json())

# convert the object into a dict
commit_table_result_dict = commit_table_result_instance.to_dict()
# create an instance of CommitTableResult from a dict
commit_table_result_from_dict = CommitTableResult.from_dict(commit_table_result_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


