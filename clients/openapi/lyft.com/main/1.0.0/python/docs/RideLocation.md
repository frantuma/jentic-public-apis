# RideLocation


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 
**address** | **str** | A human readable address at/near the given location | [optional] 
**eta_seconds** | **int** | Estimated seconds for a driver to pickup or reach destination based on ride status | [optional] 

## Example

```python
from openapi_client.models.ride_location import RideLocation

# TODO update the JSON string below
json = "{}"
# create an instance of RideLocation from a JSON string
ride_location_instance = RideLocation.from_json(json)
# print the JSON string representation of the object
print(RideLocation.to_json())

# convert the object into a dict
ride_location_dict = ride_location_instance.to_dict()
# create an instance of RideLocation from a dict
ride_location_from_dict = RideLocation.from_dict(ride_location_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


