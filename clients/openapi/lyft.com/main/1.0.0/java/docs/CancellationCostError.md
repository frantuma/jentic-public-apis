

# CancellationCostError


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**amount** | **Integer** | Total price of the ride |  |
|**currency** | **String** | The ISO 4217 currency code for the amount (e.g. USD) |  |
|**description** | **String** | The description for the cost |  |
|**token** | **String** | Token used to confirm the fee when cancelling a request |  [optional] |
|**tokenDuration** | **Integer** | How long, in seconds, before the token expires |  [optional] |
|**error** | **String** | A \&quot;slug\&quot; that serves as the error code (eg. \&quot;bad_parameter\&quot;) |  [optional] |
|**errorDescription** | **String** | A user-friendly description of the error (appropriate to show to an end-user) |  [optional] |
|**errorDetail** | [**List&lt;ErrorDetail&gt;**](ErrorDetail.md) |  |  [optional] |



