# TableVersion


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**version** | **int** | Version number | 
**manifest_path** | **str** | Path to the manifest file for this version. | 
**manifest_size** | **int** | Size of the manifest file in bytes | [optional] 
**e_tag** | **str** | Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores.  | [optional] 
**timestamp_millis** | **int** | Timestamp when the version was created, in milliseconds since epoch (Unix time) | [optional] 
**metadata** | **Dict[str, str]** | Optional key-value pairs of metadata | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.table_version import TableVersion

# TODO update the JSON string below
json = "{}"
# create an instance of TableVersion from a JSON string
table_version_instance = TableVersion.from_json(json)
# print the JSON string representation of the object
print(TableVersion.to_json())

# convert the object into a dict
table_version_dict = table_version_instance.to_dict()
# create an instance of TableVersion from a dict
table_version_from_dict = TableVersion.from_dict(table_version_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


