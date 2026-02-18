

# TableVersion


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**version** | **Long** | Version number |  |
|**manifestPath** | **String** | Path to the manifest file for this version. When not provided, the client should resolve the manifest path based on the Lance table format&#39;s manifest naming scheme and the manifest naming scheme the table is currently using.  |  [optional] |
|**manifestSize** | **Long** | Size of the manifest file in bytes |  [optional] |
|**eTag** | **String** | Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores.  |  [optional] |
|**timestamp** | **OffsetDateTime** | Timestamp when the version was created |  [optional] |
|**metadata** | **Map&lt;String, String&gt;** | Optional key-value pairs of metadata |  [optional] |



