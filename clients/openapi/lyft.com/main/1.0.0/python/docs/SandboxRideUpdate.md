# SandboxRideUpdate

Response when a sandbox ride is propagated through ride status

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ride_id** | **str** | The ID of the ride | [optional] 
**status** | [**RideStatusEnum**](RideStatusEnum.md) |  | [optional] 

## Example

```python
from openapi_client.models.sandbox_ride_update import SandboxRideUpdate

# TODO update the JSON string below
json = "{}"
# create an instance of SandboxRideUpdate from a JSON string
sandbox_ride_update_instance = SandboxRideUpdate.from_json(json)
# print the JSON string representation of the object
print(SandboxRideUpdate.to_json())

# convert the object into a dict
sandbox_ride_update_dict = sandbox_ride_update_instance.to_dict()
# create an instance of SandboxRideUpdate from a dict
sandbox_ride_update_from_dict = SandboxRideUpdate.from_dict(sandbox_ride_update_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


