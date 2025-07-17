# Tip


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **int** | A tip for the driver in cents. To be charged to the user&#39;s default charge account. | [optional] 
**currency** | **str** | The currency in which you want to tip. e.g. USD | [optional] 

## Example

```python
from openapi_client.models.tip import Tip

# TODO update the JSON string below
json = "{}"
# create an instance of Tip from a JSON string
tip_instance = Tip.from_json(json)
# print the JSON string representation of the object
print(Tip.to_json())

# convert the object into a dict
tip_dict = tip_instance.to_dict()
# create an instance of Tip from a dict
tip_from_dict = Tip.from_dict(tip_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


