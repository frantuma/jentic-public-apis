# VehicleDetail


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**color** | **str** | The vehicle&#39;s color | 
**image_url** | **str** | The vehicle&#39;s image url | 
**license_plate** | **str** | The vehicle&#39;s license plate | 
**license_plate_state** | **str** | The vehicle&#39;s license plate state | 
**make** | **str** | The vehicle&#39;s maker | 
**model** | **str** | The vehicle&#39;s model | 
**year** | **int** | The vehicle&#39;s model year | 

## Example

```python
from openapi_client.models.vehicle_detail import VehicleDetail

# TODO update the JSON string below
json = "{}"
# create an instance of VehicleDetail from a JSON string
vehicle_detail_instance = VehicleDetail.from_json(json)
# print the JSON string representation of the object
print(VehicleDetail.to_json())

# convert the object into a dict
vehicle_detail_dict = vehicle_detail_instance.to_dict()
# create an instance of VehicleDetail from a dict
vehicle_detail_from_dict = VehicleDetail.from_dict(vehicle_detail_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


