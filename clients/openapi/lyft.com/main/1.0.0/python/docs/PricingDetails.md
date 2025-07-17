# PricingDetails


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**base_charge** | **int** | The base charge of the trip | [optional] 
**cancel_penalty_amount** | **int** | The charge amount if cancel penalty is involved | [optional] 
**cost_minimum** | **int** | The minimum charge for the trip | [optional] 
**cost_per_mile** | **int** | The cost per mile | [optional] 
**cost_per_minute** | **int** | The cost per minute | [optional] 
**currency** | **str** | The ISO 4217 currency code for the amount (e.g. USD) | [optional] 
**trust_and_service** | **int** | Service fee | [optional] 

## Example

```python
from openapi_client.models.pricing_details import PricingDetails

# TODO update the JSON string below
json = "{}"
# create an instance of PricingDetails from a JSON string
pricing_details_instance = PricingDetails.from_json(json)
# print the JSON string representation of the object
print(PricingDetails.to_json())

# convert the object into a dict
pricing_details_dict = pricing_details_instance.to_dict()
# create an instance of PricingDetails from a dict
pricing_details_from_dict = PricingDetails.from_dict(pricing_details_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


