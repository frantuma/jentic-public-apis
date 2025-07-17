# PickupDropoffLocation


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 
**address** | **str** | A human readable address at/near the given location | [optional] 
**time** | **datetime** | Server time when the location object is created | [optional] 

## Example

```python
from openapi_client.models.pickup_dropoff_location import PickupDropoffLocation

# TODO update the JSON string below
json = "{}"
# create an instance of PickupDropoffLocation from a JSON string
pickup_dropoff_location_instance = PickupDropoffLocation.from_json(json)
# print the JSON string representation of the object
print(PickupDropoffLocation.to_json())

# convert the object into a dict
pickup_dropoff_location_dict = pickup_dropoff_location_instance.to_dict()
# create an instance of PickupDropoffLocation from a dict
pickup_dropoff_location_from_dict = PickupDropoffLocation.from_dict(pickup_dropoff_location_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


