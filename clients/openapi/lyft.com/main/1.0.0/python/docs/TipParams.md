# TipParams


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**amount** | **int** | A tip for the driver in cents. To be charged to the user&#39;s default charge account. | [optional] 
**currency** | **str** | The currency in which you want to tip. e.g. USD | [optional] 

## Example

```python
from openapi_client.models.tip_params import TipParams

# TODO update the JSON string below
json = "{}"
# create an instance of TipParams from a JSON string
tip_params_instance = TipParams.from_json(json)
# print the JSON string representation of the object
print(TipParams.to_json())

# convert the object into a dict
tip_params_dict = tip_params_instance.to_dict()
# create an instance of TipParams from a dict
tip_params_from_dict = TipParams.from_dict(tip_params_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


