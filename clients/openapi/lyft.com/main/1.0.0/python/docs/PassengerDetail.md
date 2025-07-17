# PassengerDetail


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**first_name** | **str** | The passenger&#39;s first name | 
**image_url** | **str** | The passenger&#39;s profile image | 
**rating** | **str** | The passenger&#39;s rating | 
**last_name** | **str** | The passenger&#39;s last name | [optional] 
**user_id** | **str** | The passenger&#39;s lyft user id | [optional] 

## Example

```python
from openapi_client.models.passenger_detail import PassengerDetail

# TODO update the JSON string below
json = "{}"
# create an instance of PassengerDetail from a JSON string
passenger_detail_instance = PassengerDetail.from_json(json)
# print the JSON string representation of the object
print(PassengerDetail.to_json())

# convert the object into a dict
passenger_detail_dict = passenger_detail_instance.to_dict()
# create an instance of PassengerDetail from a dict
passenger_detail_from_dict = PassengerDetail.from_dict(passenger_detail_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


