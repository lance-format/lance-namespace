# CommitTableResultDeleteTableVersions

Result of a delete_table_versions operation.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**deleted_count** | **int** | Number of version records deleted | 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result_delete_table_versions import CommitTableResultDeleteTableVersions

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResultDeleteTableVersions from a JSON string
commit_table_result_delete_table_versions_instance = CommitTableResultDeleteTableVersions.from_json(json)
# print the JSON string representation of the object
print(CommitTableResultDeleteTableVersions.to_json())

# convert the object into a dict
commit_table_result_delete_table_versions_dict = commit_table_result_delete_table_versions_instance.to_dict()
# create an instance of CommitTableResultDeleteTableVersions from a dict
commit_table_result_delete_table_versions_from_dict = CommitTableResultDeleteTableVersions.from_dict(commit_table_result_delete_table_versions_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


