# CancellationRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cancel_confirmation_token** | **str** | Token affirming the user accepts the cancellation fee. Required if a cancellation fee is in effect. | [optional] 

## Example

```python
from openapi_client.models.cancellation_request import CancellationRequest

# TODO update the JSON string below
json = "{}"
# create an instance of CancellationRequest from a JSON string
cancellation_request_instance = CancellationRequest.from_json(json)
# print the JSON string representation of the object
print(CancellationRequest.to_json())

# convert the object into a dict
cancellation_request_dict = cancellation_request_instance.to_dict()
# create an instance of CancellationRequest from a dict
cancellation_request_from_dict = CancellationRequest.from_dict(cancellation_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


