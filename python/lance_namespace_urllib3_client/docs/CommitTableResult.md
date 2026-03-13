# CommitTableResult

Result of a single operation within a batch commit. Each result corresponds to one operation in the request, in the same order. 

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**location** | **str** | The storage location of the deregistered table | [optional] 
**version** | [**TableVersion**](TableVersion.md) | The created table version details | 
**deleted_count** | **int** | Number of version records deleted | 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result import CommitTableResult

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResult from a JSON string
commit_table_result_instance = CommitTableResult.from_json(json)
# print the JSON string representation of the object
print(CommitTableResult.to_json())

# convert the object into a dict
commit_table_result_dict = commit_table_result_instance.to_dict()
# create an instance of CommitTableResult from a dict
commit_table_result_from_dict = CommitTableResult.from_dict(commit_table_result_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


