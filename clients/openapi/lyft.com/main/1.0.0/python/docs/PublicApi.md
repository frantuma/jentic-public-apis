# openapi_client.PublicApi

All URIs are relative to *https://api.lyft.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_cost**](PublicApi.md#get_cost) | **GET** /cost | Cost estimates
[**get_drivers**](PublicApi.md#get_drivers) | **GET** /drivers | Available drivers nearby
[**get_eta**](PublicApi.md#get_eta) | **GET** /eta | Pickup ETAs
[**get_ride_types**](PublicApi.md#get_ride_types) | **GET** /ridetypes | Types of rides


# **get_cost**
> CostEstimateResponse get_cost(start_lat, start_lng, ride_type=ride_type, end_lat=end_lat, end_lng=end_lng)

Cost estimates

Estimate the cost of taking a Lyft between two points.


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.cost_estimate_response import CostEstimateResponse
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://api.lyft.com/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://api.lyft.com/v1"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

configuration.access_token = os.environ["ACCESS_TOKEN"]

configuration.access_token = os.environ["ACCESS_TOKEN"]

# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.PublicApi(api_client)
    start_lat = 3.4 # float | Latitude of the starting location
    start_lng = 3.4 # float | Longitude of the starting location
    ride_type = 'ride_type_example' # str | ID of a ride type (optional)
    end_lat = 3.4 # float | Latitude of the ending location (optional)
    end_lng = 3.4 # float | Longitude of the ending location (optional)

    try:
        # Cost estimates
        api_response = api_instance.get_cost(start_lat, start_lng, ride_type=ride_type, end_lat=end_lat, end_lng=end_lng)
        print("The response of PublicApi->get_cost:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling PublicApi->get_cost: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start_lat** | **float**| Latitude of the starting location | 
 **start_lng** | **float**| Longitude of the starting location | 
 **ride_type** | **str**| ID of a ride type | [optional] 
 **end_lat** | **float**| Latitude of the ending location | [optional] 
 **end_lng** | **float**| Longitude of the ending location | [optional] 

### Return type

[**CostEstimateResponse**](CostEstimateResponse.md)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with an array of cost estimates by ride type |  -  |
**400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: start location is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at this start location  |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_drivers**
> NearbyDriversResponse get_drivers(lat, lng)

Available drivers nearby

The drivers endpoint returns a list of nearby drivers' lat and lng at a given location.


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.nearby_drivers_response import NearbyDriversResponse
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://api.lyft.com/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://api.lyft.com/v1"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

configuration.access_token = os.environ["ACCESS_TOKEN"]

configuration.access_token = os.environ["ACCESS_TOKEN"]

# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.PublicApi(api_client)
    lat = 3.4 # float | Latitude of a location
    lng = 3.4 # float | Longitude of a location

    try:
        # Available drivers nearby
        api_response = api_instance.get_drivers(lat, lng)
        print("The response of PublicApi->get_drivers:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling PublicApi->get_drivers: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lat** | **float**| Latitude of a location | 
 **lng** | **float**| Longitude of a location | 

### Return type

[**NearbyDriversResponse**](NearbyDriversResponse.md)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with an array of available drivers sorted by eta for the given location |  -  |
**400** | A validation error occurred |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_eta**
> EtaEstimateResponse get_eta(lat, lng, destination_lat=destination_lat, destination_lng=destination_lng, ride_type=ride_type)

Pickup ETAs

The ETA endpoint lets you know how quickly a Lyft driver can come get you


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.eta_estimate_response import EtaEstimateResponse
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://api.lyft.com/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://api.lyft.com/v1"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

configuration.access_token = os.environ["ACCESS_TOKEN"]

configuration.access_token = os.environ["ACCESS_TOKEN"]

# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.PublicApi(api_client)
    lat = 3.4 # float | Latitude of a location
    lng = 3.4 # float | Longitude of a location
    destination_lat = 3.4 # float | Latitude of destination location (optional)
    destination_lng = 3.4 # float | Longitude of destination location (optional)
    ride_type = 'ride_type_example' # str | ID of a ride type (optional)

    try:
        # Pickup ETAs
        api_response = api_instance.get_eta(lat, lng, destination_lat=destination_lat, destination_lng=destination_lng, ride_type=ride_type)
        print("The response of PublicApi->get_eta:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling PublicApi->get_eta: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lat** | **float**| Latitude of a location | 
 **lng** | **float**| Longitude of a location | 
 **destination_lat** | **float**| Latitude of destination location | [optional] 
 **destination_lng** | **float**| Longitude of destination location | [optional] 
 **ride_type** | **str**| ID of a ride type | [optional] 

### Return type

[**EtaEstimateResponse**](EtaEstimateResponse.md)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with an array of ETAs by ride type for the given location |  -  |
**400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: location is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at this location  |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_ride_types**
> RideTypesResponse get_ride_types(lat, lng, ride_type=ride_type)

Types of rides

The ride types endpoint returns information about what kinds of Lyft rides you can request at a given location.


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.ride_types_response import RideTypesResponse
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://api.lyft.com/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://api.lyft.com/v1"
)

# The client must configure the authentication and authorization parameters
# in accordance with the API server security policy.
# Examples for each auth method are provided below, use the example that
# satisfies your auth use case.

configuration.access_token = os.environ["ACCESS_TOKEN"]

configuration.access_token = os.environ["ACCESS_TOKEN"]

# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.PublicApi(api_client)
    lat = 3.4 # float | Latitude of a location
    lng = 3.4 # float | Longitude of a location
    ride_type = 'ride_type_example' # str | ID of a ride type (optional)

    try:
        # Types of rides
        api_response = api_instance.get_ride_types(lat, lng, ride_type=ride_type)
        print("The response of PublicApi->get_ride_types:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling PublicApi->get_ride_types: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **lat** | **float**| Latitude of a location | 
 **lng** | **float**| Longitude of a location | 
 **ride_type** | **str**| ID of a ride type | [optional] 

### Return type

[**RideTypesResponse**](RideTypesResponse.md)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with an array of available Ride Types for the given location |  -  |
**400** | A validation error occurred |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

