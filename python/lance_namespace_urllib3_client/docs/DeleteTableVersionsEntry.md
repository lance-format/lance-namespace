# DeleteTableVersionsEntry

An entry for deleting table versions in a batch operation. Each entry specifies a table and the version ranges to delete. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **List[str]** | The table identifier | 
**ranges** | [**List[VersionRange]**](VersionRange.md) | List of version ranges to delete. Each range specifies start (inclusive) and end (exclusive) versions.  | 

## Example

```python
from lance_namespace_urllib3_client.models.delete_table_versions_entry import DeleteTableVersionsEntry

# TODO update the JSON string below
json = "{}"
# create an instance of DeleteTableVersionsEntry from a JSON string
delete_table_versions_entry_instance = DeleteTableVersionsEntry.from_json(json)
# print the JSON string representation of the object
print(DeleteTableVersionsEntry.to_json())

# convert the object into a dict
delete_table_versions_entry_dict = delete_table_versions_entry_instance.to_dict()
# create an instance of DeleteTableVersionsEntry from a dict
delete_table_versions_entry_from_dict = DeleteTableVersionsEntry.from_dict(delete_table_versions_entry_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


