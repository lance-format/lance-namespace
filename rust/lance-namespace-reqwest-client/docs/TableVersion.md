# TableVersion

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**version** | **i64** | Version number | 
**manifest_path** | Option<**String**> | Path to the manifest file for this version. When not provided, the client should resolve the manifest path based on the Lance table format's manifest naming scheme and the manifest naming scheme the table is currently using.  | [optional]
**manifest_size** | Option<**i64**> | Size of the manifest file in bytes | [optional]
**e_tag** | Option<**String**> | Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores.  | [optional]
**timestamp** | Option<**String**> | Timestamp when the version was created | [optional]
**metadata** | Option<**std::collections::HashMap<String, String>**> | Optional key-value pairs of metadata | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


