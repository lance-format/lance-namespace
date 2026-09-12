# QueryTableRequest

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**identity** | Option<[**models::Identity**](Identity.md)> |  | [optional]
**context** | Option<**std::collections::HashMap<String, String>**> | Arbitrary context as key-value pairs. How to use the context is custom to the specific implementation.  On a request, it carries caller-provided context to the implementation. On a response, it carries implementation-provided context back to the caller.  REST NAMESPACE ONLY Context entries are mapped to and from HTTP headers using the `header.` prefix: - On a request, any entry whose key starts with `header.` is sent as an HTTP   request header with the prefix stripped. For example, the entry   `{\"header.Authorization\": \"Bearer abc\"}` is sent as the request header   `Authorization: Bearer abc`. - On a response, every HTTP response header is returned as an entry whose key is the   header name prefixed with `header.`. For example, the response header   `x-request-id: abc123` is returned as the entry `{\"header.x-request-id\": \"abc123\"}`.  | [optional]
**id** | Option<**Vec<String>**> |  | [optional]
**branch** | Option<**String**> | Branch to target. When not specified, the main branch is used.  | [optional]
**bypass_vector_index** | Option<**bool**> | Whether to bypass vector index | [optional]
**columns** | Option<[**models::QueryTableRequestColumns**](QueryTableRequest_columns.md)> |  | [optional]
**distance_type** | Option<**String**> | Distance metric to use | [optional]
**ef** | Option<**i32**> | Search effort parameter for HNSW index | [optional]
**fast_search** | Option<**bool**> | Whether to use fast search | [optional]
**filter** | Option<**String**> | Optional SQL filter expression. Field references in the expression must use Lance field path syntax: nested fields use dot-separated segments, literal dots require backtick-quoted segments, and backticks inside quoted segments are doubled.  | [optional]
**full_text_query** | Option<[**models::QueryTableRequestFullTextQuery**](QueryTableRequest_full_text_query.md)> |  | [optional]
**k** | **i32** | Number of results to return | 
**lower_bound** | Option<**f32**> | Lower bound for search | [optional]
**nprobes** | Option<**i32**> | Number of probes for IVF index | [optional]
**offset** | Option<**i32**> | Number of results to skip | [optional]
**order_by** | Option<[**Vec<models::QueryTableOrderBy>**](QueryTableOrderBy.md)> | Optional scan result ordering, matching Lance Scanner order_by. Entries are applied in list order, with later entries breaking ties. Omission or an empty list preserves the query mode's default ordering; null is not allowed. Sort fields need not appear in the output projection. Sorting precedes the final offset and result limit. Scalar queries sort all matching rows before pagination. Vector and full-text queries retain their search, filtering, and internal candidate limits; ordering does not expand those candidates or guarantee a global field-based top-k. This parameter does not add support for otherwise unsupported query combinations, including hybrid search. Implementations must reject unsupported combinations rather than silently ignore ordering. Missing fields return TableColumnNotFound; invalid parameters or unsortable types return InvalidInput. Equal sort keys do not guarantee a stable relative order or stable pagination. | [optional]
**prefilter** | Option<**bool**> | Whether to apply filtering before vector search | [optional]
**refine_factor** | Option<**i32**> | Refine factor for search | [optional]
**upper_bound** | Option<**f32**> | Upper bound for search | [optional]
**vector** | Option<[**models::QueryTableRequestVector**](QueryTableRequest_vector.md)> |  | [optional]
**vector_column** | Option<**String**> | Lance field path of the vector field to search. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments. Use canonical full paths for display and errors; leaf names alone only identify top-level fields; invalid or unresolved paths should return InvalidInput or TableColumnNotFound. | [optional]
**version** | Option<**i64**> | Table version to query | [optional]
**with_row_id** | Option<**bool**> | If true, return the row id as a column called `_rowid` | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


