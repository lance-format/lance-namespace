# CommitTableOperation

A single operation within a batch commit. Uses a discriminator on the `type` field to determine the operation kind. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**location** | **str** | Optional storage location for the table | [optional] 
**properties** | **Dict[str, str]** | Optional table properties | [optional] 
**version** | **int** | Version number to create | 
**manifest_path** | **str** | Path to the manifest file for this version | 
**manifest_size** | **int** | Size of the manifest file in bytes | [optional] 
**e_tag** | **str** | Optional ETag for the manifest file | [optional] 
**metadata** | **Dict[str, str]** | Optional metadata for the version | [optional] 
**naming_scheme** | **str** | The naming scheme used for manifest files in the &#x60;_versions/&#x60; directory.  Known values: - &#x60;V1&#x60;: &#x60;_versions/{version}.manifest&#x60; - Simple version-based naming - &#x60;V2&#x60;: &#x60;_versions/{inverted_version}.manifest&#x60; - Zero-padded, reversed version number   (uses &#x60;u64::MAX - version&#x60;) for O(1) lookup of latest version on object stores  V2 is preferred for new tables as it enables efficient latest-version discovery without needing to list all versions.  | [optional] 
**ranges** | [**List[VersionRange]**](VersionRange.md) | List of version ranges to delete | 

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


