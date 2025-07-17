# CostEstimate

A non-guaranteed estimate of price

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cost_token** | **str** | A token that confirms the user has accepted current Prime Time and/or fixed price charges. See &#39;Request a Lyft&#39; for more details | [optional] 
**currency** | **str** | The ISO 4217 currency code for the amount (e.g. &#39;USD&#39;) | [optional] 
**display_name** | **str** | A human readable description of the ride type | [optional] 
**estimated_cost_cents_max** | **int** | Estimated upper bound for trip cost, in minor units (cents). Estimates are not guaranteed, and only provide a reasonable range based on current conditions.  | [optional] 
**estimated_cost_cents_min** | **int** | Estimated lower bound for trip cost, in minor units (cents). Estimates are not guaranteed, and only provide a reasonable range based on current conditions.  | [optional] 
**estimated_distance_miles** | **float** | Estimated distance for this trip  | [optional] 
**estimated_duration_seconds** | **int** | Estimated time to get from the start location to the end.  | [optional] 
**is_valid_estimate** | **bool** | The validity of the cost estimate returned | [optional] 
**primetime_confirmation_token** | **str** | This token is needed when requesting rides. (Deprecated) | [optional] 
**primetime_percentage** | **str** | Current Prime Time Percentage. Prime Time adds a percentage to ride costs, prior to other applicable fees. When ride requests greatly outnumber available drivers, our system will automatically turn on Prime Time. If Prime Time is inactive, the value returned will be &#39;0%&#39;. Note: The returned estimate already has Prime Time factored in. The value is returned here for reference and to allow users to confirm/accept Prime Time prior to initiating a ride.  | [optional] 
**ride_type** | [**RideTypeEnum**](RideTypeEnum.md) |  | [optional] 

## Example

```python
from openapi_client.models.cost_estimate import CostEstimate

# TODO update the JSON string below
json = "{}"
# create an instance of CostEstimate from a JSON string
cost_estimate_instance = CostEstimate.from_json(json)
# print the JSON string representation of the object
print(CostEstimate.to_json())

# convert the object into a dict
cost_estimate_dict = cost_estimate_instance.to_dict()
# create an instance of CostEstimate from a dict
cost_estimate_from_dict = CostEstimate.from_dict(cost_estimate_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


