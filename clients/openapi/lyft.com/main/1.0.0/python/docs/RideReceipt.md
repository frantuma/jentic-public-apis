# RideReceipt

Receipt information of a processed ride.

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**charges** | [**List[Charge]**](Charge.md) | The break down of charge method | [optional] 
**line_items** | [**List[LineItem]**](LineItem.md) | The break down of line items | [optional] 
**price** | [**Cost**](Cost.md) | The total price for the current ride | [optional] 
**requested_at** | **datetime** | The ride requested timestamp in date and time | [optional] 
**ride_id** | **str** | The unique ID of this ride | [optional] 
**ride_profile** | [**RideProfileEnum**](RideProfileEnum.md) | Indicates whether the ride was requested from the business profile or personal profile of the user.  | [optional] 

## Example

```python
from openapi_client.models.ride_receipt import RideReceipt

# TODO update the JSON string below
json = "{}"
# create an instance of RideReceipt from a JSON string
ride_receipt_instance = RideReceipt.from_json(json)
# print the JSON string representation of the object
print(RideReceipt.to_json())

# convert the object into a dict
ride_receipt_dict = ride_receipt_instance.to_dict()
# create an instance of RideReceipt from a dict
ride_receipt_from_dict = RideReceipt.from_dict(ride_receipt_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


