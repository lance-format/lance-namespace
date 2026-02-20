# TableVersion

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**version** | **i64** | Version number | 
**manifest_path** | **String** | Path to the manifest file for this version. | 
**manifest_size** | Option<**i64**> | Size of the manifest file in bytes | [optional]
**e_tag** | Option<**String**> | Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores.  | [optional]
**timestamp_millis** | Option<**i64**> | Timestamp when the version was created, in milliseconds since epoch (Unix time) | [optional]
**metadata** | Option<**std::collections::HashMap<String, String>**> | Optional key-value pairs of metadata | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


