# NearbyDriversByRideType


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**drivers** | [**List[NearbyDriver]**](NearbyDriver.md) | list of nearby drivers group by ride type sorted by eta | [optional] 
**ride_type** | **str** | driver&#39;s ride type. if driver is eligable for several ride types, he will be duplicated. | [optional] 

## Example

```python
from openapi_client.models.nearby_drivers_by_ride_type import NearbyDriversByRideType

# TODO update the JSON string below
json = "{}"
# create an instance of NearbyDriversByRideType from a JSON string
nearby_drivers_by_ride_type_instance = NearbyDriversByRideType.from_json(json)
# print the JSON string representation of the object
print(NearbyDriversByRideType.to_json())

# convert the object into a dict
nearby_drivers_by_ride_type_dict = nearby_drivers_by_ride_type_instance.to_dict()
# create an instance of NearbyDriversByRideType from a dict
nearby_drivers_by_ride_type_from_dict = NearbyDriversByRideType.from_dict(nearby_drivers_by_ride_type_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


