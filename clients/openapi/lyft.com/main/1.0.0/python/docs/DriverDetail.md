# DriverDetail


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**first_name** | **str** | The driver&#39;s first name | 
**image_url** | **str** | The driver&#39;s image url | 
**phone_number** | **str** | The driver&#39;s contact phone number. Must be E.164 formatted.  | 
**rating** | **str** | The driver&#39;s rating based in 0-5 scale | 
**user_id** | **str** | The driver&#39;s id | 

## Example

```python
from openapi_client.models.driver_detail import DriverDetail

# TODO update the JSON string below
json = "{}"
# create an instance of DriverDetail from a JSON string
driver_detail_instance = DriverDetail.from_json(json)
# print the JSON string representation of the object
print(DriverDetail.to_json())

# convert the object into a dict
driver_detail_dict = driver_detail_instance.to_dict()
# create an instance of DriverDetail from a dict
driver_detail_from_dict = DriverDetail.from_dict(driver_detail_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


