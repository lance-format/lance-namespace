# \TransactionApi

All URIs are relative to *http://localhost:2333*

Method | HTTP request | Description
------------- | ------------- | -------------
[**alter_transaction**](TransactionApi.md#alter_transaction) | **POST** /v1/transaction/{id}/alter | Alter information of a transaction.
[**batch_commit_tables**](TransactionApi.md#batch_commit_tables) | **POST** /v1/table/batch-commit | Atomically commit a batch of mixed table operations
[**describe_transaction**](TransactionApi.md#describe_transaction) | **POST** /v1/transaction/{id}/describe | Describe information about a transaction



## alter_transaction

> models::AlterTransactionResponse alter_transaction(id, alter_transaction_request, delimiter)
Alter information of a transaction.

Alter a transaction with a list of actions such as setting status or properties. The server should either succeed and apply all actions, or fail and apply no action. 

### Parameters


Name | Type | Description  | Required | Notes
------------- | ------------- | ------------- | ------------- | -------------
**id** | **String** | `string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/$/list` performs a `ListNamespace` on the root namespace.  | [required] |
**alter_transaction_request** | [**AlterTransactionRequest**](AlterTransactionRequest.md) |  | [required] |
**delimiter** | Option<**String**> | An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `$` delimiter must be used.  |  |

### Return type

[**models::AlterTransactionResponse**](AlterTransactionResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2), [ApiKeyAuth](../README.md#ApiKeyAuth), [BearerAuth](../README.md#BearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


## batch_commit_tables

> models::BatchCommitTablesResponse batch_commit_tables(batch_commit_tables_request, delimiter)
Atomically commit a batch of mixed table operations

Atomically commit a batch of table operations. This is a generalized version of `BatchCreateTableVersions` that supports mixed operation types within a single atomic transaction at the metadata layer.  Supported operation types: - `DeclareTable`: Declare (reserve) a new table - `CreateTableVersion`: Create a new version entry for a table - `DeleteTableVersions`: Delete version ranges from a table - `DeregisterTable`: Deregister (soft-delete) a table  All operations are committed atomically: either all succeed or none are applied. 

### Parameters


Name | Type | Description  | Required | Notes
------------- | ------------- | ------------- | ------------- | -------------
**batch_commit_tables_request** | [**BatchCommitTablesRequest**](BatchCommitTablesRequest.md) |  | [required] |
**delimiter** | Option<**String**> | An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `$` delimiter must be used.  |  |

### Return type

[**models::BatchCommitTablesResponse**](BatchCommitTablesResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2), [ApiKeyAuth](../README.md#ApiKeyAuth), [BearerAuth](../README.md#BearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)


## describe_transaction

> models::DescribeTransactionResponse describe_transaction(id, describe_transaction_request, delimiter)
Describe information about a transaction

Return a detailed information for a given transaction 

### Parameters


Name | Type | Description  | Required | Notes
------------- | ------------- | ------------- | ------------- | -------------
**id** | **String** | `string identifier` of an object in a namespace, following the Lance Namespace spec. When the value is equal to the delimiter, it represents the root namespace. For example, `v1/namespace/$/list` performs a `ListNamespace` on the root namespace.  | [required] |
**describe_transaction_request** | [**DescribeTransactionRequest**](DescribeTransactionRequest.md) |  | [required] |
**delimiter** | Option<**String**> | An optional delimiter of the `string identifier`, following the Lance Namespace spec. When not specified, the `$` delimiter must be used.  |  |

### Return type

[**models::DescribeTransactionResponse**](DescribeTransactionResponse.md)

### Authorization

[OAuth2](../README.md#OAuth2), [ApiKeyAuth](../README.md#ApiKeyAuth), [BearerAuth](../README.md#BearerAuth)

### HTTP request headers

- **Content-Type**: application/json
- **Accept**: application/json

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

