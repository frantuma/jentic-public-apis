# RideRequest

Minimal set of ride details

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**destination** | [**Location**](Location.md) | The *requested* location for passenger drop off | [optional] 
**origin** | [**Location**](Location.md) | The *requested* location for passenger pickup | [optional] 
**passenger** | [**PassengerDetail**](PassengerDetail.md) |  | [optional] 
**ride_id** | **str** | The ID of the requested ride | [optional] 
**status** | [**RideStatusEnum**](RideStatusEnum.md) |  | [optional] 

## Example

```python
from openapi_client.models.ride_request import RideRequest

# TODO update the JSON string below
json = "{}"
# create an instance of RideRequest from a JSON string
ride_request_instance = RideRequest.from_json(json)
# print the JSON string representation of the object
print(RideRequest.to_json())

# convert the object into a dict
ride_request_dict = ride_request_instance.to_dict()
# create an instance of RideRequest from a dict
ride_request_from_dict = RideRequest.from_dict(ride_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


