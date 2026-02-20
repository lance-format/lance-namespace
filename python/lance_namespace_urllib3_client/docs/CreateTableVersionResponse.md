# CreateTableVersionResponse

Response for creating a table version

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | **str** | Optional transaction identifier | [optional] 
**version** | [**TableVersion**](TableVersion.md) |  | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.create_table_version_response import CreateTableVersionResponse

# TODO update the JSON string below
json = "{}"
# create an instance of CreateTableVersionResponse from a JSON string
create_table_version_response_instance = CreateTableVersionResponse.from_json(json)
# print the JSON string representation of the object
print(CreateTableVersionResponse.to_json())

# convert the object into a dict
create_table_version_response_dict = create_table_version_response_instance.to_dict()
# create an instance of CreateTableVersionResponse from a dict
create_table_version_response_from_dict = CreateTableVersionResponse.from_dict(create_table_version_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


