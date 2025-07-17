

# RideRequestError

Details about why a request failed, such as missing or invalid parameters

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**costToken** | **String** | A token that confirms the user has accepted current Prime Time and/or fixed price charges |  [optional] |
|**error** | **String** | A \&quot;slug\&quot; that serves as the error code (eg. \&quot;bad_parameter\&quot;) |  |
|**errorDescription** | **String** | A user-friendly description of the error (appropriate to show to an end-user) |  [optional] |
|**errorDetail** | [**List&lt;ErrorDetail&gt;**](ErrorDetail.md) |  |  [optional] |
|**errorUri** | **String** | When a user must go through another flow before requesting a ride, this URI specifies which flow to use (e.g. an account challenge flow in a web view) |  [optional] |
|**primetimeConfirmationToken** | **String** | A token that confirms the user has accepted current Prime Time charges (Deprecated) |  [optional] |
|**primetimeMultiplier** | **Float** | Current Prime Time multiplier (eg. if primetime_percentage is 100%, primetime_multiplier will be 2.0) |  [optional] |
|**primetimePercentage** | **String** | Current Prime Time percentage |  [optional] |
|**tokenDuration** | **String** | Validity of the token in seconds |  [optional] |



