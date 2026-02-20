# DeclareTableResponse

Response for declaring a table. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | **str** | Optional transaction identifier | [optional] 
**location** | **str** |  | [optional] 
**storage_options** | **Dict[str, str]** | Configuration options to be used to access storage. The available options depend on the type of storage in use. These will be passed directly to Lance to initialize storage access.  | [optional] 
**properties** | **Dict[str, str]** | If the implementation does not support table properties, it should return null for this field. Otherwise it should return the properties.  | [optional] 
**managed_versioning** | **bool** | When true, the caller should use namespace table version operations (CreateTableVersion, BatchCreateTableVersions, DescribeTableVersion, ListTableVersions, BatchDeleteTableVersions) to manage table versions instead of relying on Lance&#39;s native version management.  | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.declare_table_response import DeclareTableResponse

# TODO update the JSON string below
json = "{}"
# create an instance of DeclareTableResponse from a JSON string
declare_table_response_instance = DeclareTableResponse.from_json(json)
# print the JSON string representation of the object
print(DeclareTableResponse.to_json())

# convert the object into a dict
declare_table_response_dict = declare_table_response_instance.to_dict()
# create an instance of DeclareTableResponse from a dict
declare_table_response_from_dict = DeclareTableResponse.from_dict(declare_table_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


