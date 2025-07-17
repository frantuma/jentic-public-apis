# ApiError

Details about why a request failed, such as missing or invalid parameters

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**error** | **str** | A \&quot;slug\&quot; that serves as the error code (eg. \&quot;bad_parameter\&quot;) | [optional] 
**error_description** | **str** | A user-friendly description of the error (appropriate to show to an end-user) | [optional] 
**error_detail** | [**List[ErrorDetail]**](ErrorDetail.md) |  | [optional] 

## Example

```python
from openapi_client.models.api_error import ApiError

# TODO update the JSON string below
json = "{}"
# create an instance of ApiError from a JSON string
api_error_instance = ApiError.from_json(json)
# print the JSON string representation of the object
print(ApiError.to_json())

# convert the object into a dict
api_error_dict = api_error_instance.to_dict()
# create an instance of ApiError from a dict
api_error_from_dict = ApiError.from_dict(api_error_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


