# CommitTableResultDeclareTable

Result of a declare_table operation.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** |  | 
**id** | **List[str]** | The table identifier | 
**location** | **str** | The resolved storage location for the declared table | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.commit_table_result_declare_table import CommitTableResultDeclareTable

# TODO update the JSON string below
json = "{}"
# create an instance of CommitTableResultDeclareTable from a JSON string
commit_table_result_declare_table_instance = CommitTableResultDeclareTable.from_json(json)
# print the JSON string representation of the object
print(CommitTableResultDeclareTable.to_json())

# convert the object into a dict
commit_table_result_declare_table_dict = commit_table_result_declare_table_instance.to_dict()
# create an instance of CommitTableResultDeclareTable from a dict
commit_table_result_declare_table_from_dict = CommitTableResultDeclareTable.from_dict(commit_table_result_declare_table_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


