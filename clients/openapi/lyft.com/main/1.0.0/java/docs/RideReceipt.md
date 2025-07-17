

# RideReceipt

Receipt information of a processed ride.

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**charges** | [**List&lt;Charge&gt;**](Charge.md) | The break down of charge method |  [optional] |
|**lineItems** | [**List&lt;LineItem&gt;**](LineItem.md) | The break down of line items |  [optional] |
|**price** | [**Cost**](Cost.md) | The total price for the current ride |  [optional] |
|**requestedAt** | **OffsetDateTime** | The ride requested timestamp in date and time |  [optional] |
|**rideId** | **String** | The unique ID of this ride |  [optional] |
|**rideProfile** | **RideProfileEnum** | Indicates whether the ride was requested from the business profile or personal profile of the user.  |  [optional] |



