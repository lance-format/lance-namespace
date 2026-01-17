# UpdateTableResponse

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | Option<**String**> | Optional transaction identifier | [optional]
**updated_rows** | **i64** | Number of rows updated | 
**version** | **i64** | The commit version associated with the operation | 
**properties** | Option<**std::collections::HashMap<String, String>**> | If the implementation does not support table properties, it should return null for this field. Otherwise, it should return the properties.  | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


