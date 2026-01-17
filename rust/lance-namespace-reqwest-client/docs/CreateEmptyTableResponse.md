# CreateEmptyTableResponse

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | Option<**String**> | Optional transaction identifier | [optional]
**location** | Option<**String**> |  | [optional]
**storage_options** | Option<**std::collections::HashMap<String, String>**> | Configuration options to be used to access storage. The available options depend on the type of storage in use. These will be passed directly to Lance to initialize storage access.  | [optional]
**properties** | Option<**std::collections::HashMap<String, String>**> | If the implementation does not support table properties, it should return null for this field. Otherwise it should return the properties.  | [optional][default to {}]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


