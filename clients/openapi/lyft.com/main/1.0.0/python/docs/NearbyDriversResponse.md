# NearbyDriversResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**nearby_drivers** | [**List[NearbyDriversByRideType]**](NearbyDriversByRideType.md) |  | [optional] 

## Example

```python
from openapi_client.models.nearby_drivers_response import NearbyDriversResponse

# TODO update the JSON string below
json = "{}"
# create an instance of NearbyDriversResponse from a JSON string
nearby_drivers_response_instance = NearbyDriversResponse.from_json(json)
# print the JSON string representation of the object
print(NearbyDriversResponse.to_json())

# convert the object into a dict
nearby_drivers_response_dict = nearby_drivers_response_instance.to_dict()
# create an instance of NearbyDriversResponse from a dict
nearby_drivers_response_from_dict = NearbyDriversResponse.from_dict(nearby_drivers_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


