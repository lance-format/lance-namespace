# RefreshMaterializedViewRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | [**Identity**](Identity.md) |  | [optional] 
**id** | **List[str]** | Table identifier path (namespace + table name) | [optional] 
**src_version** | **int** | Optional source version to refresh from | [optional] 
**max_rows_per_fragment** | **int** | Optional maximum rows per fragment | [optional] 
**concurrency** | **int** | Optional concurrency override | [optional] 
**intra_applier_concurrency** | **int** | Optional intra-applier concurrency override | [optional] 
**cluster** | **str** | Optional cluster name | [optional] 
**manifest** | **str** | Optional manifest name | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.refresh_materialized_view_request import RefreshMaterializedViewRequest

# TODO update the JSON string below
json = "{}"
# create an instance of RefreshMaterializedViewRequest from a JSON string
refresh_materialized_view_request_instance = RefreshMaterializedViewRequest.from_json(json)
# print the JSON string representation of the object
print(RefreshMaterializedViewRequest.to_json())

# convert the object into a dict
refresh_materialized_view_request_dict = refresh_materialized_view_request_instance.to_dict()
# create an instance of RefreshMaterializedViewRequest from a dict
refresh_materialized_view_request_from_dict = RefreshMaterializedViewRequest.from_dict(refresh_materialized_view_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


