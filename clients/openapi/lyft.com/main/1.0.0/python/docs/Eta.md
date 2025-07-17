# Eta

Estimated Time of Arrival

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**display_name** | **str** | A human readable description of the ride type | [optional] 
**eta_seconds** | **int** | Estimated seconds for a driver to arrive | [optional] 
**eta_seconds_max** | **int** | Estimated upper bound of seconds for a driver to arrive | [optional] 
**is_valid_estimate** | **bool** | The validity of the ETA estimate returned | [optional] 
**ride_type** | [**RideTypeEnum**](RideTypeEnum.md) |  | [optional] 

## Example

```python
from openapi_client.models.eta import Eta

# TODO update the JSON string below
json = "{}"
# create an instance of Eta from a JSON string
eta_instance = Eta.from_json(json)
# print the JSON string representation of the object
print(Eta.to_json())

# convert the object into a dict
eta_dict = eta_instance.to_dict()
# create an instance of Eta from a dict
eta_from_dict = Eta.from_dict(eta_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


