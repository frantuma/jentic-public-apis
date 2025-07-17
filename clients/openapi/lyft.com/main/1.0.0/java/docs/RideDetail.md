

# RideDetail

Detail information about a ride

## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**beaconColor** | **String** | Hex color code of the driver AMP device. |  [optional] |
|**canCancel** | [**List&lt;CanCancelEnum&gt;**](#List&lt;CanCancelEnum&gt;) |  |  [optional] |
|**canceledBy** | **String** | The role of user who canceled the ride (if applicable) |  [optional] |
|**cancellationPrice** | [**CancellationCost**](CancellationCost.md) | The cost of cancellation if there would be a penalty |  [optional] |
|**destination** | [**RideLocation**](RideLocation.md) | The *requested* location for passenger drop off |  [optional] |
|**distanceMiles** | **Float** | The distance, in miles, that this ride traveled. This field is only present after drop-off |  [optional] |
|**driver** | [**DriverDetail**](DriverDetail.md) |  |  [optional] |
|**dropoff** | [**PickupDropoffLocation**](PickupDropoffLocation.md) | The *actual* location of passenger drop off |  [optional] |
|**durationSeconds** | **Integer** | Duration of the ride in seconds from pickup to drop-off. This field is only present after drop-off. |  [optional] |
|**feedback** | **String** | The written feedback the user left for this ride |  [optional] |
|**generatedAt** | **OffsetDateTime** | The request timestamp in date and time |  [optional] |
|**lineItems** | [**List&lt;LineItem&gt;**](LineItem.md) | The break down of cost |  [optional] |
|**location** | [**CurrentRideLocation**](CurrentRideLocation.md) | The *current* location info of the ride |  [optional] |
|**origin** | [**RideLocation**](RideLocation.md) | The *requested* location for passenger pickup |  [optional] |
|**passenger** | [**PassengerDetail**](PassengerDetail.md) |  |  [optional] |
|**pickup** | [**PickupDropoffLocation**](PickupDropoffLocation.md) | The *actual* location of passenger pickup |  [optional] |
|**price** | [**Cost**](Cost.md) | The total price for the current ride |  [optional] |
|**pricingDetailsUrl** | **String** | The web view showing the pricing structure for the geographic area where the ride was taken  |  [optional] |
|**primetimePercentage** | **String** | The Prime Time percentage applied to the base price |  [optional] |
|**rating** | **Integer** | The rating the user left for this ride, from 1 to 5 |  [optional] |
|**requestedAt** | **OffsetDateTime** | The ride requested timestamp in date and time |  [optional] |
|**rideId** | **String** | The unique ID of this ride |  [optional] |
|**rideProfile** | **RideProfileEnum** | Indicates whether the ride was requested from the business profile or personal profile of the user.  |  [optional] |
|**rideType** | **RideTypeEnumWithOther** |  |  [optional] |
|**routeUrl** | **String** | The web view showing the passenger, driver, and route for this ride. This field will only be present for rides created through this API, or that have been shared through the \&quot;Share my Route\&quot; feature  |  [optional] |
|**status** | **RideStatusEnum** |  |  [optional] |
|**vehicle** | [**VehicleDetail**](VehicleDetail.md) |  |  [optional] |



## Enum: List&lt;CanCancelEnum&gt;

| Name | Value |
|---- | -----|
| DRIVER | &quot;driver&quot; |
| PASSENGER | &quot;passenger&quot; |
| DISPATCHER | &quot;dispatcher&quot; |



