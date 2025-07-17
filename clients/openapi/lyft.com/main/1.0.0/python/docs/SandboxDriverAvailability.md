# SandboxDriverAvailability


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**driver_availability** | **bool** | The availability of driver in a region | 
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 

## Example

```python
from openapi_client.models.sandbox_driver_availability import SandboxDriverAvailability

# TODO update the JSON string below
json = "{}"
# create an instance of SandboxDriverAvailability from a JSON string
sandbox_driver_availability_instance = SandboxDriverAvailability.from_json(json)
# print the JSON string representation of the object
print(SandboxDriverAvailability.to_json())

# convert the object into a dict
sandbox_driver_availability_dict = sandbox_driver_availability_instance.to_dict()
# create an instance of SandboxDriverAvailability from a dict
sandbox_driver_availability_from_dict = SandboxDriverAvailability.from_dict(sandbox_driver_availability_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


