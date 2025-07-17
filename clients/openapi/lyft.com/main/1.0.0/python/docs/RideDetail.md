# RideDetail

Detail information about a ride

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**beacon_color** | **str** | Hex color code of the driver AMP device. | [optional] 
**can_cancel** | **List[str]** |  | [optional] 
**canceled_by** | **str** | The role of user who canceled the ride (if applicable) | [optional] 
**cancellation_price** | [**CancellationCost**](CancellationCost.md) | The cost of cancellation if there would be a penalty | [optional] 
**destination** | [**RideLocation**](RideLocation.md) | The *requested* location for passenger drop off | [optional] 
**distance_miles** | **float** | The distance, in miles, that this ride traveled. This field is only present after drop-off | [optional] 
**driver** | [**DriverDetail**](DriverDetail.md) |  | [optional] 
**dropoff** | [**PickupDropoffLocation**](PickupDropoffLocation.md) | The *actual* location of passenger drop off | [optional] 
**duration_seconds** | **int** | Duration of the ride in seconds from pickup to drop-off. This field is only present after drop-off. | [optional] 
**feedback** | **str** | The written feedback the user left for this ride | [optional] 
**generated_at** | **datetime** | The request timestamp in date and time | [optional] 
**line_items** | [**List[LineItem]**](LineItem.md) | The break down of cost | [optional] 
**location** | [**CurrentRideLocation**](CurrentRideLocation.md) | The *current* location info of the ride | [optional] 
**origin** | [**RideLocation**](RideLocation.md) | The *requested* location for passenger pickup | [optional] 
**passenger** | [**PassengerDetail**](PassengerDetail.md) |  | [optional] 
**pickup** | [**PickupDropoffLocation**](PickupDropoffLocation.md) | The *actual* location of passenger pickup | [optional] 
**price** | [**Cost**](Cost.md) | The total price for the current ride | [optional] 
**pricing_details_url** | **str** | The web view showing the pricing structure for the geographic area where the ride was taken  | [optional] 
**primetime_percentage** | **str** | The Prime Time percentage applied to the base price | [optional] 
**rating** | **int** | The rating the user left for this ride, from 1 to 5 | [optional] 
**requested_at** | **datetime** | The ride requested timestamp in date and time | [optional] 
**ride_id** | **str** | The unique ID of this ride | [optional] 
**ride_profile** | [**RideProfileEnum**](RideProfileEnum.md) | Indicates whether the ride was requested from the business profile or personal profile of the user.  | [optional] 
**ride_type** | [**RideTypeEnumWithOther**](RideTypeEnumWithOther.md) |  | [optional] 
**route_url** | **str** | The web view showing the passenger, driver, and route for this ride. This field will only be present for rides created through this API, or that have been shared through the \&quot;Share my Route\&quot; feature  | [optional] 
**status** | [**RideStatusEnum**](RideStatusEnum.md) |  | [optional] 
**vehicle** | [**VehicleDetail**](VehicleDetail.md) |  | [optional] 

## Example

```python
from openapi_client.models.ride_detail import RideDetail

# TODO update the JSON string below
json = "{}"
# create an instance of RideDetail from a JSON string
ride_detail_instance = RideDetail.from_json(json)
# print the JSON string representation of the object
print(RideDetail.to_json())

# convert the object into a dict
ride_detail_dict = ride_detail_instance.to_dict()
# create an instance of RideDetail from a dict
ride_detail_from_dict = RideDetail.from_dict(ride_detail_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


