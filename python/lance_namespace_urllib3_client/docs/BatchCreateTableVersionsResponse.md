# BatchCreateTableVersionsResponse

Response for batch creating table versions. Contains the created versions for each table in the same order as the request. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | **str** | Optional transaction identifier | [optional] 
**versions** | [**List[TableVersion]**](TableVersion.md) | List of created table versions in the same order as the request entries | 

## Example

```python
from lance_namespace_urllib3_client.models.batch_create_table_versions_response import BatchCreateTableVersionsResponse

# TODO update the JSON string below
json = "{}"
# create an instance of BatchCreateTableVersionsResponse from a JSON string
batch_create_table_versions_response_instance = BatchCreateTableVersionsResponse.from_json(json)
# print the JSON string representation of the object
print(BatchCreateTableVersionsResponse.to_json())

# convert the object into a dict
batch_create_table_versions_response_dict = batch_create_table_versions_response_instance.to_dict()
# create an instance of BatchCreateTableVersionsResponse from a dict
batch_create_table_versions_response_from_dict = BatchCreateTableVersionsResponse.from_dict(batch_create_table_versions_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


