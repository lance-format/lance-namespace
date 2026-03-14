# CommitTableOperation

A single operation within a batch commit. Provide exactly one of the operation fields to specify the operation kind. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**declare_table** | [**DeclareTableRequest**](DeclareTableRequest.md) | Declare (reserve) a new table in the namespace | [optional] 
**create_table_version** | [**CreateTableVersionRequest**](CreateTableVersionRequest.md) | Create a new version entry for a table | [optional] 
**delete_table_versions** | [**BatchDeleteTableVersionsRequest**](BatchDeleteTableVersionsRequest.md) | Delete version ranges from a table | [optional] 
**deregister_table** | [**DeregisterTableRequest**](DeregisterTableRequest.md) | Deregister (soft-delete) a table | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_operation import CommitTableOperation

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableOperation from a JSON string
commit_table_operation_instance = CommitTableOperation.from_json(json)
# print the JSON string representation of the object
print(CommitTableOperation.to_json())

# convert the object into a dict
commit_table_operation_dict = commit_table_operation_instance.to_dict()
# create an instance of CommitTableOperation from a dict
commit_table_operation_from_dict = CommitTableOperation.from_dict(commit_table_operation_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


