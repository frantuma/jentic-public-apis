# RatingRequest

Rating and optional feedback and tip

## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**feedback** | **str** | The passenger&#39;s written feedback about this ride | [optional] 
**rating** | **int** | The passenger&#39;s rating of this ride from 1 to 5 | 
**tip** | [**TipParams**](TipParams.md) | Tip amount in minor units and tip currency | [optional] 

## Example

```python
from openapi_client.models.rating_request import RatingRequest

# TODO update the JSON string below
json = "{}"
# create an instance of RatingRequest from a JSON string
rating_request_instance = RatingRequest.from_json(json)
# print the JSON string representation of the object
print(RatingRequest.to_json())

# convert the object into a dict
rating_request_dict = rating_request_instance.to_dict()
# create an instance of RatingRequest from a dict
rating_request_from_dict = RatingRequest.from_dict(rating_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


