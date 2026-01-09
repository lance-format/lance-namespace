# PartitionTransform

Well-known partition transform

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**type** | **str** | Transform type (identity, year, month, day, hour, bucket, multi_bucket, truncate) | 
**num_buckets** | **int** | Number of buckets for bucket transforms | [optional] 
**width** | **int** | Truncation width for truncate transforms | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.partition_transform import PartitionTransform

# TODO update the JSON string below
json = "{}"
# create an instance of PartitionTransform from a JSON string
partition_transform_instance = PartitionTransform.from_json(json)
# print the JSON string representation of the object
print(PartitionTransform.to_json())

# convert the object into a dict
partition_transform_dict = partition_transform_instance.to_dict()
# create an instance of PartitionTransform from a dict
partition_transform_from_dict = PartitionTransform.from_dict(partition_transform_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


