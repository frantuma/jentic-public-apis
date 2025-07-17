# CostEstimateResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cost_estimates** | [**List[CostEstimate]**](CostEstimate.md) |  | [optional] 

## Example

```python
from openapi_client.models.cost_estimate_response import CostEstimateResponse

# TODO update the JSON string below
json = "{}"
# create an instance of CostEstimateResponse from a JSON string
cost_estimate_response_instance = CostEstimateResponse.from_json(json)
# print the JSON string representation of the object
print(CostEstimateResponse.to_json())

# convert the object into a dict
cost_estimate_response_dict = cost_estimate_response_instance.to_dict()
# create an instance of CostEstimateResponse from a dict
cost_estimate_response_from_dict = CostEstimateResponse.from_dict(cost_estimate_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


