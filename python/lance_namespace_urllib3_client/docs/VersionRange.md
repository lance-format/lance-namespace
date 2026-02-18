# VersionRange

A range of versions to delete (start inclusive, end exclusive). Special values: - `start_version: 0` with `end_version: -1` means ALL versions 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**start_version** | **int** | Start version of the range (inclusive). Use 0 to start from the first version.  | 
**end_version** | **int** | End version of the range (exclusive). Use -1 to indicate all versions up to and including the latest.  | 

## Example

```python
from lance_namespace_urllib3_client.models.version_range import VersionRange

# TODO update the JSON string below
json = "{}"
# create an instance of VersionRange from a JSON string
version_range_instance = VersionRange.from_json(json)
# print the JSON string representation of the object
print(VersionRange.to_json())

# convert the object into a dict
version_range_dict = version_range_instance.to_dict()
# create an instance of VersionRange from a dict
version_range_from_dict = VersionRange.from_dict(version_range_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


