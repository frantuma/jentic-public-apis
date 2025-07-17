# EtaEstimateResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**eta_estimates** | [**List[Eta]**](Eta.md) |  | [optional] 

## Example

```python
from openapi_client.models.eta_estimate_response import EtaEstimateResponse

# TODO update the JSON string below
json = "{}"
# create an instance of EtaEstimateResponse from a JSON string
eta_estimate_response_instance = EtaEstimateResponse.from_json(json)
# print the JSON string representation of the object
print(EtaEstimateResponse.to_json())

# convert the object into a dict
eta_estimate_response_dict = eta_estimate_response_instance.to_dict()
# create an instance of EtaEstimateResponse from a dict
eta_estimate_response_from_dict = EtaEstimateResponse.from_dict(eta_estimate_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


