# BatchCommitTablesResponse

Response for a batch commit of table operations. Contains the results of each operation in the same order as the request. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**transaction_id** | **str** | Optional transaction identifier for the batch commit | [optional] 
**results** | [**List[CommitTableResult]**](CommitTableResult.md) | Results for each operation, in the same order as the request operations. Each result contains the outcome of the corresponding operation.  | 

## Example

```python
from lance_namespace_urllib3_client.models.batch_commit_tables_response import BatchCommitTablesResponse

# TODO update the JSON string below
json = "{}"
# create an instance of BatchCommitTablesResponse from a JSON string
batch_commit_tables_response_instance = BatchCommitTablesResponse.from_json(json)
# print the JSON string representation of the object
print(BatchCommitTablesResponse.to_json())

# convert the object into a dict
batch_commit_tables_response_dict = batch_commit_tables_response_instance.to_dict()
# create an instance of BatchCommitTablesResponse from a dict
batch_commit_tables_response_from_dict = BatchCommitTablesResponse.from_dict(batch_commit_tables_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


