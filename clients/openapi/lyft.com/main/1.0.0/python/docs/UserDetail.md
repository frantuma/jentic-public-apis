# UserDetail


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**first_name** | **str** | The passenger&#39;s first name | 
**image_url** | **str** | The passenger&#39;s profile image | 
**rating** | **str** | The passenger&#39;s rating | 

## Example

```python
from openapi_client.models.user_detail import UserDetail

# TODO update the JSON string below
json = "{}"
# create an instance of UserDetail from a JSON string
user_detail_instance = UserDetail.from_json(json)
# print the JSON string representation of the object
print(UserDetail.to_json())

# convert the object into a dict
user_detail_dict = user_detail_instance.to_dict()
# create an instance of UserDetail from a dict
user_detail_from_dict = UserDetail.from_dict(user_detail_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


