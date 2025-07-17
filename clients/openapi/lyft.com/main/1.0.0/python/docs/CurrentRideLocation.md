# CurrentRideLocation


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 
**bearing** | **float** | Bearing of the driver&#39;s car in degrees | [optional] 

## Example

```python
from openapi_client.models.current_ride_location import CurrentRideLocation

# TODO update the JSON string below
json = "{}"
# create an instance of CurrentRideLocation from a JSON string
current_ride_location_instance = CurrentRideLocation.from_json(json)
# print the JSON string representation of the object
print(CurrentRideLocation.to_json())

# convert the object into a dict
current_ride_location_dict = current_ride_location_instance.to_dict()
# create an instance of CurrentRideLocation from a dict
current_ride_location_from_dict = CurrentRideLocation.from_dict(current_ride_location_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


