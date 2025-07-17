# NearbyDriver


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**locations** | [**List[LatLng]**](LatLng.md) | the lastest recorded driver locations up to 5 sorted in chronological order. | [optional] 

## Example

```python
from openapi_client.models.nearby_driver import NearbyDriver

# TODO update the JSON string below
json = "{}"
# create an instance of NearbyDriver from a JSON string
nearby_driver_instance = NearbyDriver.from_json(json)
# print the JSON string representation of the object
print(NearbyDriver.to_json())

# convert the object into a dict
nearby_driver_dict = nearby_driver_instance.to_dict()
# create an instance of NearbyDriver from a dict
nearby_driver_from_dict = NearbyDriver.from_dict(nearby_driver_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


