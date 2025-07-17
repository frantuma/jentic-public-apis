# RideRequestError

Details about why a request failed, such as missing or invalid parameters

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**cost_token** | **str** | A token that confirms the user has accepted current Prime Time and/or fixed price charges | [optional] 
**error** | **str** | A \&quot;slug\&quot; that serves as the error code (eg. \&quot;bad_parameter\&quot;) | 
**error_description** | **str** | A user-friendly description of the error (appropriate to show to an end-user) | [optional] 
**error_detail** | [**List[ErrorDetail]**](ErrorDetail.md) |  | [optional] 
**error_uri** | **str** | When a user must go through another flow before requesting a ride, this URI specifies which flow to use (e.g. an account challenge flow in a web view) | [optional] 
**primetime_confirmation_token** | **str** | A token that confirms the user has accepted current Prime Time charges (Deprecated) | [optional] 
**primetime_multiplier** | **float** | Current Prime Time multiplier (eg. if primetime_percentage is 100%, primetime_multiplier will be 2.0) | [optional] 
**primetime_percentage** | **str** | Current Prime Time percentage | [optional] 
**token_duration** | **str** | Validity of the token in seconds | [optional] 

## Example

```python
from openapi_client.models.ride_request_error import RideRequestError

# TODO update the JSON string below
json = "{}"
# create an instance of RideRequestError from a JSON string
ride_request_error_instance = RideRequestError.from_json(json)
# print the JSON string representation of the object
print(RideRequestError.to_json())

# convert the object into a dict
ride_request_error_dict = ride_request_error_instance.to_dict()
# create an instance of RideRequestError from a dict
ride_request_error_from_dict = RideRequestError.from_dict(ride_request_error_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


