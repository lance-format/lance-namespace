# CreateTableVersionRequest

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | Option<[**models::Identity**](Identity.md)> |  | [optional]
**context** | Option<**std::collections::HashMap<String, String>**> | Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention `x-lance-ctx-<key>: <value>`. For example, a context entry `{\"trace_id\": \"abc123\"}` would be sent as the header `x-lance-ctx-trace_id: abc123`.  | [optional]
**id** | Option<**Vec<String>**> | The table identifier | [optional]
**version** | **i64** | Version number to create | 
**manifest_path** | **String** | Path to the manifest file for this version | 
**manifest_size** | Option<**i64**> | Size of the manifest file in bytes | [optional]
**e_tag** | Option<**String**> | Optional ETag for the manifest file | [optional]
**metadata** | Option<**std::collections::HashMap<String, String>**> | Optional metadata for the version | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


