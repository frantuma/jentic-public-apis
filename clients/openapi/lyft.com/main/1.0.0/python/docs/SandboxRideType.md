# SandboxRideType


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 
**ride_types** | [**List[RideTypeEnum]**](RideTypeEnum.md) |  | 

## Example

```python
from openapi_client.models.sandbox_ride_type import SandboxRideType

# TODO update the JSON string below
json = "{}"
# create an instance of SandboxRideType from a JSON string
sandbox_ride_type_instance = SandboxRideType.from_json(json)
# print the JSON string representation of the object
print(SandboxRideType.to_json())

# convert the object into a dict
sandbox_ride_type_dict = sandbox_ride_type_instance.to_dict()
# create an instance of SandboxRideType from a dict
sandbox_ride_type_from_dict = SandboxRideType.from_dict(sandbox_ride_type_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


