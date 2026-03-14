# BatchCommitTablesRequest

Request to atomically commit a batch of table operations. This replaces `BatchCreateTableVersionsRequest` with a more general interface that supports mixed operations (DeclareTable, CreateTableVersion, DeleteTableVersions, DeregisterTable) within a single atomic transaction at the metadata layer.  All operations are committed atomically: either all succeed or none are applied. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | [**Identity**](Identity.md) |  | [optional] 
**context** | **Dict[str, str]** | Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention &#x60;x-lance-ctx-&lt;key&gt;: &lt;value&gt;&#x60;. For example, a context entry &#x60;{\&quot;trace_id\&quot;: \&quot;abc123\&quot;}&#x60; would be sent as the header &#x60;x-lance-ctx-trace_id: abc123&#x60;.  | [optional] 
**operations** | [**List[CommitTableOperation]**](CommitTableOperation.md) | List of operations to commit atomically. Supported operation types: DeclareTable, CreateTableVersion, DeleteTableVersions, DeregisterTable.  | 

## Example

```python
from lance_namespace_urllib3_client.models.batch_commit_tables_request import BatchCommitTablesRequest

# TODO update the JSON string below
json = "{}"
# create an instance of BatchCommitTablesRequest from a JSON string
batch_commit_tables_request_instance = BatchCommitTablesRequest.from_json(json)
# print the JSON string representation of the object
print(BatchCommitTablesRequest.to_json())

# convert the object into a dict
batch_commit_tables_request_dict = batch_commit_tables_request_instance.to_dict()
# create an instance of BatchCommitTablesRequest from a dict
batch_commit_tables_request_from_dict = BatchCommitTablesRequest.from_dict(batch_commit_tables_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


