# RideTypesResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ride_types** | [**List[RideType]**](RideType.md) |  | [optional] 

## Example

```python
from openapi_client.models.ride_types_response import RideTypesResponse

# TODO update the JSON string below
json = "{}"
# create an instance of RideTypesResponse from a JSON string
ride_types_response_instance = RideTypesResponse.from_json(json)
# print the JSON string representation of the object
print(RideTypesResponse.to_json())

# convert the object into a dict
ride_types_response_dict = ride_types_response_instance.to_dict()
# create an instance of RideTypesResponse from a dict
ride_types_response_from_dict = RideTypesResponse.from_dict(ride_types_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


