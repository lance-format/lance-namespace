

# DeleteTableVersionsEntry

An entry for deleting table versions in a batch operation. Each entry specifies a table and the version ranges to delete. 

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **List&lt;String&gt;** | The table identifier |  |
|**ranges** | [**List&lt;VersionRange&gt;**](VersionRange.md) | List of version ranges to delete. Each range specifies start (inclusive) and end (exclusive) versions.  |  |



