# Ride

Represents a requested, ongoing, or finished Lyft ride

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cost_token** | **str** | A token that confirms the user has accepted current Prime Time and/or fixed price charges | [optional] 
**destination** | [**Location**](Location.md) | The *requested* location for passenger drop off | [optional] 
**origin** | [**Location**](Location.md) | The *requested* location for passenger pickup | 
**primetime_confirmation_token** | **str** | A token that confirms the user has accepted current primetime charges (Deprecated) | [optional] 
**ride_type** | [**RideTypeEnum**](RideTypeEnum.md) |  | 

## Example

```python
from openapi_client.models.ride import Ride

# TODO update the JSON string below
json = "{}"
# create an instance of Ride from a JSON string
ride_instance = Ride.from_json(json)
# print the JSON string representation of the object
print(Ride.to_json())

# convert the object into a dict
ride_dict = ride_instance.to_dict()
# create an instance of Ride from a dict
ride_from_dict = Ride.from_dict(ride_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


