# AnalyzeTableQueryPlanRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | [**Identity**](Identity.md) |  | [optional] 
**context** | **Dict[str, str]** | Arbitrary context as key-value pairs. How to use the context is custom to the specific implementation.  On a request, it carries caller-provided context to the implementation. On a response, it carries implementation-provided context back to the caller.  REST NAMESPACE ONLY Context entries are mapped to and from HTTP headers using the &#x60;header.&#x60; prefix: - On a request, any entry whose key starts with &#x60;header.&#x60; is sent as an HTTP   request header with the prefix stripped. For example, the entry   &#x60;{\&quot;header.Authorization\&quot;: \&quot;Bearer abc\&quot;}&#x60; is sent as the request header   &#x60;Authorization: Bearer abc&#x60;. - On a response, every HTTP response header is returned as an entry whose key is the   header name prefixed with &#x60;header.&#x60;. For example, the response header   &#x60;x-request-id: abc123&#x60; is returned as the entry &#x60;{\&quot;header.x-request-id\&quot;: \&quot;abc123\&quot;}&#x60;.  | [optional] 
**id** | **List[str]** |  | [optional] 
**branch** | **str** | Branch to target. When not specified, the main branch is used.  | [optional] 
**bypass_vector_index** | **bool** | Whether to bypass vector index | [optional] 
**columns** | [**QueryTableRequestColumns**](QueryTableRequestColumns.md) |  | [optional] 
**distance_type** | **str** | Distance metric to use | [optional] 
**ef** | **int** | Search effort parameter for HNSW index | [optional] 
**fast_search** | **bool** | Whether to use fast search | [optional] 
**filter** | **str** | Optional SQL filter expression. Field references in the expression must use Lance field path syntax: nested fields use dot-separated segments, literal dots require backtick-quoted segments, and backticks inside quoted segments are doubled.  | [optional] 
**full_text_query** | [**QueryTableRequestFullTextQuery**](QueryTableRequestFullTextQuery.md) |  | [optional] 
**k** | **int** | Number of results to return | 
**lower_bound** | **float** | Lower bound for search | [optional] 
**nprobes** | **int** | Number of probes for IVF index | [optional] 
**offset** | **int** | Number of results to skip | [optional] 
**order_by** | [**List[QueryTableOrderBy]**](QueryTableOrderBy.md) | Optional scan result ordering, matching Lance Scanner order_by. Entries are applied in list order, with later entries breaking ties. Omission or an empty list preserves the query mode&#39;s default ordering; null is not allowed. Sort fields need not appear in the output projection. Sorting precedes the final offset and result limit. Scalar queries sort all matching rows before pagination. Vector and full-text queries retain their search, filtering, and internal candidate limits; ordering does not expand those candidates or guarantee a global field-based top-k. This parameter does not add support for otherwise unsupported query combinations, including hybrid search. Implementations must reject unsupported combinations rather than silently ignore ordering. Missing fields return TableColumnNotFound; invalid parameters or unsortable types return InvalidInput. Equal sort keys do not guarantee a stable relative order or stable pagination. | [optional] 
**prefilter** | **bool** | Whether to apply filtering before vector search | [optional] 
**refine_factor** | **int** | Refine factor for search | [optional] 
**upper_bound** | **float** | Upper bound for search | [optional] 
**vector** | [**QueryTableRequestVector**](QueryTableRequestVector.md) |  | [optional] 
**vector_column** | **str** | Lance field path of the vector field to search. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments. Use canonical full paths for display and errors; leaf names alone only identify top-level fields; invalid or unresolved paths should return InvalidInput or TableColumnNotFound. | [optional] 
**version** | **int** | Table version to query | [optional] 
**with_row_id** | **bool** | If true, return the row id as a column called &#x60;_rowid&#x60; | [optional] 

## Example

```python
from lance_namespace_urllib3_client.models.analyze_table_query_plan_request import AnalyzeTableQueryPlanRequest

# TODO update the JSON string below
json = "{}"
# create an instance of AnalyzeTableQueryPlanRequest from a JSON string
analyze_table_query_plan_request_instance = AnalyzeTableQueryPlanRequest.from_json(json)
# print the JSON string representation of the object
print(AnalyzeTableQueryPlanRequest.to_json())

# convert the object into a dict
analyze_table_query_plan_request_dict = analyze_table_query_plan_request_instance.to_dict()
# create an instance of AnalyzeTableQueryPlanRequest from a dict
analyze_table_query_plan_request_from_dict = AnalyzeTableQueryPlanRequest.from_dict(analyze_table_query_plan_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


