# CommitTableOperationDeleteTableVersions

Delete version ranges from a table.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**ranges** | [**List[VersionRange]**](VersionRange.md) | List of version ranges to delete | 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_operation_delete_table_versions import CommitTableOperationDeleteTableVersions

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableOperationDeleteTableVersions from a JSON string
commit_table_operation_delete_table_versions_instance = CommitTableOperationDeleteTableVersions.from_json(json)
# print the JSON string representation of the object
print(CommitTableOperationDeleteTableVersions.to_json())

# convert the object into a dict
commit_table_operation_delete_table_versions_dict = commit_table_operation_delete_table_versions_instance.to_dict()
# create an instance of CommitTableOperationDeleteTableVersions from a dict
commit_table_operation_delete_table_versions_from_dict = CommitTableOperationDeleteTableVersions.from_dict(commit_table_operation_delete_table_versions_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


