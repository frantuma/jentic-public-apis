# CancellationCost


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **int** | Total price of the ride | 
**currency** | **str** | The ISO 4217 currency code for the amount (e.g. USD) | 
**description** | **str** | The description for the cost | 
**token** | **str** | Token used to confirm the fee when cancelling a request | [optional] 
**token_duration** | **int** | How long, in seconds, before the token expires | [optional] 

## Example

```python
from openapi_client.models.cancellation_cost import CancellationCost

# TODO update the JSON string below
json = "{}"
# create an instance of CancellationCost from a JSON string
cancellation_cost_instance = CancellationCost.from_json(json)
# print the JSON string representation of the object
print(CancellationCost.to_json())

# convert the object into a dict
cancellation_cost_dict = cancellation_cost_instance.to_dict()
# create an instance of CancellationCost from a dict
cancellation_cost_from_dict = CancellationCost.from_dict(cancellation_cost_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


