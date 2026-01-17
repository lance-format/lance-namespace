# DropNamespaceResponse

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**properties** | Option<**std::collections::HashMap<String, String>**> | If the implementation does not support namespace properties, it should return null for this field. Otherwise it should return the properties.  | [optional]
**transaction_id** | Option<**Vec<String>**> | If present, indicating the operation is long running and should be tracked using DescribeTransaction  | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


