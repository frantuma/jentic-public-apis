# RideType


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**display_name** | **str** | A human readable description of the ride type | [optional] 
**image_url** | **str** | The URL of an image representing this ride type | [optional] 
**pricing_details** | [**PricingDetails**](PricingDetails.md) |  | [optional] 
**ride_type** | [**RideTypeEnum**](RideTypeEnum.md) |  | [optional] 
**scheduled_pricing_details** | [**PricingDetails**](PricingDetails.md) |  | [optional] 
**seats** | **int** | The maximum number of seats available for rides requested with this ride type | [optional] 

## Example

```python
from openapi_client.models.ride_type import RideType

# TODO update the JSON string below
json = "{}"
# create an instance of RideType from a JSON string
ride_type_instance = RideType.from_json(json)
# print the JSON string representation of the object
print(RideType.to_json())

# convert the object into a dict
ride_type_dict = ride_type_instance.to_dict()
# create an instance of RideType from a dict
ride_type_from_dict = RideType.from_dict(ride_type_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


