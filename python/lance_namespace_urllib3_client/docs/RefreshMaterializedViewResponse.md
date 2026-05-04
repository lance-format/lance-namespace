# RefreshMaterializedViewResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**job_id** | **str** | The job ID for tracking the refresh job | 

## Example

```python
from lance_namespace_urllib3_client.models.refresh_materialized_view_response import RefreshMaterializedViewResponse

# TODO update the JSON string below
json = "{}"
# create an instance of RefreshMaterializedViewResponse from a JSON string
refresh_materialized_view_response_instance = RefreshMaterializedViewResponse.from_json(json)
# print the JSON string representation of the object
print(RefreshMaterializedViewResponse.to_json())

# convert the object into a dict
refresh_materialized_view_response_dict = refresh_materialized_view_response_instance.to_dict()
# create an instance of RefreshMaterializedViewResponse from a dict
refresh_materialized_view_response_from_dict = RefreshMaterializedViewResponse.from_dict(refresh_materialized_view_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


