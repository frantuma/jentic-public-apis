# CancellationCostError


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **int** | Total price of the ride | 
**currency** | **str** | The ISO 4217 currency code for the amount (e.g. USD) | 
**description** | **str** | The description for the cost | 
**token** | **str** | Token used to confirm the fee when cancelling a request | [optional] 
**token_duration** | **int** | How long, in seconds, before the token expires | [optional] 
**error** | **str** | A \&quot;slug\&quot; that serves as the error code (eg. \&quot;bad_parameter\&quot;) | [optional] 
**error_description** | **str** | A user-friendly description of the error (appropriate to show to an end-user) | [optional] 
**error_detail** | [**List[ErrorDetail]**](ErrorDetail.md) |  | [optional] 

## Example

```python
from openapi_client.models.cancellation_cost_error import CancellationCostError

# TODO update the JSON string below
json = "{}"
# create an instance of CancellationCostError from a JSON string
cancellation_cost_error_instance = CancellationCostError.from_json(json)
# print the JSON string representation of the object
print(CancellationCostError.to_json())

# convert the object into a dict
cancellation_cost_error_dict = cancellation_cost_error_instance.to_dict()
# create an instance of CancellationCostError from a dict
cancellation_cost_error_from_dict = CancellationCostError.from_dict(cancellation_cost_error_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


