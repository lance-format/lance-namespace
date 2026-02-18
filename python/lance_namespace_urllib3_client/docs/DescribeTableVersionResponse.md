# DescribeTableVersionResponse

Response containing the table version information

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**version** | [**TableVersion**](TableVersion.md) | The table version information | 

## Example

```python
from lance_namespace_urllib3_client.models.describe_table_version_response import DescribeTableVersionResponse

# TODO update the JSON string below
json = "{}"
# create an instance of DescribeTableVersionResponse from a JSON string
describe_table_version_response_instance = DescribeTableVersionResponse.from_json(json)
# print the JSON string representation of the object
print(DescribeTableVersionResponse.to_json())

# convert the object into a dict
describe_table_version_response_dict = describe_table_version_response_instance.to_dict()
# create an instance of DescribeTableVersionResponse from a dict
describe_table_version_response_from_dict = DescribeTableVersionResponse.from_dict(describe_table_version_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


