# SandboxRideStatus


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**status** | [**RideStatusEnum**](RideStatusEnum.md) |  | 

## Example

```python
from openapi_client.models.sandbox_ride_status import SandboxRideStatus

# TODO update the JSON string below
json = "{}"
# create an instance of SandboxRideStatus from a JSON string
sandbox_ride_status_instance = SandboxRideStatus.from_json(json)
# print the JSON string representation of the object
print(SandboxRideStatus.to_json())

# convert the object into a dict
sandbox_ride_status_dict = sandbox_ride_status_instance.to_dict()
# create an instance of SandboxRideStatus from a dict
sandbox_ride_status_from_dict = SandboxRideStatus.from_dict(sandbox_ride_status_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


