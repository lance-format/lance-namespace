# CommitTableResultCreateTableVersion

Result of a create_table_version operation.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**version** | [**TableVersion**](TableVersion.md) | The created table version details | 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result_create_table_version import CommitTableResultCreateTableVersion

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResultCreateTableVersion from a JSON string
commit_table_result_create_table_version_instance = CommitTableResultCreateTableVersion.from_json(json)
# print the JSON string representation of the object
print(CommitTableResultCreateTableVersion.to_json())

# convert the object into a dict
commit_table_result_create_table_version_dict = commit_table_result_create_table_version_instance.to_dict()
# create an instance of CommitTableResultCreateTableVersion from a dict
commit_table_result_create_table_version_from_dict = CommitTableResultCreateTableVersion.from_dict(commit_table_result_create_table_version_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


