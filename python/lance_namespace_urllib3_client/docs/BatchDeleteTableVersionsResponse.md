# BatchDeleteTableVersionsResponse

Response for deleting table version records

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**deleted_count** | **int** | Number of version records deleted | [optional] 
**transaction_id** | **str** | Optional transaction identifier | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.batch_delete_table_versions_response import BatchDeleteTableVersionsResponse

# TODO update the JSON string below
json = "{}"
# create an instance of BatchDeleteTableVersionsResponse from a JSON string
batch_delete_table_versions_response_instance = BatchDeleteTableVersionsResponse.from_json(json)
# print the JSON string representation of the object
print(BatchDeleteTableVersionsResponse.to_json())

# convert the object into a dict
batch_delete_table_versions_response_dict = batch_delete_table_versions_response_instance.to_dict()
# create an instance of BatchDeleteTableVersionsResponse from a dict
batch_delete_table_versions_response_from_dict = BatchDeleteTableVersionsResponse.from_dict(batch_delete_table_versions_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


