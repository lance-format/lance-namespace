# AlterTableBackfillColumnsResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**job_id** | **str** | The job ID for tracking the backfill job | 

## Example

```python
from lance_namespace_urllib3_client.models.alter_table_backfill_columns_response import AlterTableBackfillColumnsResponse

# TODO update the JSON string below
json = "{}"
# create an instance of AlterTableBackfillColumnsResponse from a JSON string
alter_table_backfill_columns_response_instance = AlterTableBackfillColumnsResponse.from_json(json)
# print the JSON string representation of the object
print(AlterTableBackfillColumnsResponse.to_json())

# convert the object into a dict
alter_table_backfill_columns_response_dict = alter_table_backfill_columns_response_instance.to_dict()
# create an instance of AlterTableBackfillColumnsResponse from a dict
alter_table_backfill_columns_response_from_dict = AlterTableBackfillColumnsResponse.from_dict(alter_table_backfill_columns_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


