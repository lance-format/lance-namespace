# BatchDeleteTableVersionsRequest

Request to delete table version records. Supports deleting ranges of versions for efficient bulk cleanup. This request supports two modes: - Single-table (legacy): Use `id` + `ranges` to delete versions from one table. - Multi-table (transactional): Use `entries` to atomically delete versions   across multiple tables in a single operation. When `entries` is provided, `id` and `ranges` are ignored. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | [**Identity**](Identity.md) |  | [optional] 
**context** | **Dict[str, str]** | Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention &#x60;x-lance-ctx-&lt;key&gt;: &lt;value&gt;&#x60;. For example, a context entry &#x60;{\&quot;trace_id\&quot;: \&quot;abc123\&quot;}&#x60; would be sent as the header &#x60;x-lance-ctx-trace_id: abc123&#x60;.  | [optional] 
**id** | **List[str]** | The table identifier (single-table mode, legacy). Ignored when &#x60;entries&#x60; is provided.  | [optional] 
**ranges** | [**List[VersionRange]**](VersionRange.md) | List of version ranges to delete (single-table mode, legacy). Ignored when &#x60;entries&#x60; is provided. Each range specifies start (inclusive) and end (exclusive) versions.  | [optional] 
**entries** | [**List[DeleteTableVersionsEntry]**](DeleteTableVersionsEntry.md) | List of per-table delete entries for multi-table transactional deletion. When provided, the operation atomically deletes versions across all specified tables.  | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.batch_delete_table_versions_request import BatchDeleteTableVersionsRequest

# TODO update the JSON string below
json = "{}"
# create an instance of BatchDeleteTableVersionsRequest from a JSON string
batch_delete_table_versions_request_instance = BatchDeleteTableVersionsRequest.from_json(json)
# print the JSON string representation of the object
print(BatchDeleteTableVersionsRequest.to_json())

# convert the object into a dict
batch_delete_table_versions_request_dict = batch_delete_table_versions_request_instance.to_dict()
# create an instance of BatchDeleteTableVersionsRequest from a dict
batch_delete_table_versions_request_from_dict = BatchDeleteTableVersionsRequest.from_dict(batch_delete_table_versions_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


