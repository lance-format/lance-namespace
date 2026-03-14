# CommitTableOperation

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**declare_table** | Option<[**models::DeclareTableRequest**](DeclareTableRequest.md)> | Declare (reserve) a new table in the namespace | [optional]
**create_table_version** | Option<[**models::CreateTableVersionRequest**](CreateTableVersionRequest.md)> | Create a new version entry for a table | [optional]
**delete_table_versions** | Option<[**models::BatchDeleteTableVersionsRequest**](BatchDeleteTableVersionsRequest.md)> | Delete version ranges from a table | [optional]
**deregister_table** | Option<[**models::DeregisterTableRequest**](DeregisterTableRequest.md)> | Deregister (soft-delete) a table | [optional]

[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


