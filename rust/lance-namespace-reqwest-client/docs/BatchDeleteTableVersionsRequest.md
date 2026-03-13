# BatchDeleteTableVersionsRequest

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | Option<[**models::Identity**](Identity.md)> |  | [optional]
**context** | Option<**std::collections::HashMap<String, String>**> | Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention `x-lance-ctx-<key>: <value>`. For example, a context entry `{\"trace_id\": \"abc123\"}` would be sent as the header `x-lance-ctx-trace_id: abc123`.  | [optional]
**id** | Option<**Vec<String>**> | The table identifier (single-table mode, legacy). Ignored when `entries` is provided.  | [optional]
**ranges** | Option<[**Vec<models::VersionRange>**](VersionRange.md)> | List of version ranges to delete (single-table mode, legacy). Ignored when `entries` is provided. Each range specifies start (inclusive) and end (exclusive) versions.  | [optional]
**entries** | Option<[**Vec<models::DeleteTableVersionsEntry>**](DeleteTableVersionsEntry.md)> | List of per-table delete entries for multi-table transactional deletion. When provided, the operation atomically deletes versions across all specified tables.  | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


