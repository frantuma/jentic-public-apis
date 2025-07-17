# RidesResponse


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**ride_history** | [**List[RideDetail]**](RideDetail.md) |  | [optional] 

## Example

```python
from openapi_client.models.rides_response import RidesResponse

# TODO update the JSON string below
json = "{}"
# create an instance of RidesResponse from a JSON string
rides_response_instance = RidesResponse.from_json(json)
# print the JSON string representation of the object
print(RidesResponse.to_json())

# convert the object into a dict
rides_response_dict = rides_response_instance.to_dict()
# create an instance of RidesResponse from a dict
rides_response_from_dict = RidesResponse.from_dict(rides_response_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


