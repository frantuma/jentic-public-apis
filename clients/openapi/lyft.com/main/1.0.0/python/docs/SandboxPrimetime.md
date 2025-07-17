# SandboxPrimetime


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**lat** | **float** | The latitude component of a location | 
**lng** | **float** | The longitude component of a location | 
**primetime_percentage** | **str** | The Prime Time to be applied as a string, e.g., &#39;25%&#39; | 

## Example

```python
from openapi_client.models.sandbox_primetime import SandboxPrimetime

# TODO update the JSON string below
json = "{}"
# create an instance of SandboxPrimetime from a JSON string
sandbox_primetime_instance = SandboxPrimetime.from_json(json)
# print the JSON string representation of the object
print(SandboxPrimetime.to_json())

# convert the object into a dict
sandbox_primetime_dict = sandbox_primetime_instance.to_dict()
# create an instance of SandboxPrimetime from a dict
sandbox_primetime_from_dict = SandboxPrimetime.from_dict(sandbox_primetime_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


