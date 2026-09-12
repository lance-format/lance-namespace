# QueryTableOrderBy

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**column_name** | **String** | Source Lance field path to sort by, independent of output aliases. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments. | 
**ascending** | Option<**bool**> | Whether to sort in ascending order. | [optional][default to true]
**nulls_first** | Option<**bool**> | Whether null values precede non-null values, independent of direction. | [optional][default to false]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


