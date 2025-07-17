# openapi_client.SandboxApi

All URIs are relative to *https://api.lyft.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**set_prime_time**](SandboxApi.md#set_prime_time) | **PUT** /sandbox/primetime | Preset Prime Time percentage
[**set_ride_status**](SandboxApi.md#set_ride_status) | **PUT** /sandbox/rides/{id} | Propagate ride through ride status
[**set_ride_type_availability**](SandboxApi.md#set_ride_type_availability) | **PUT** /sandbox/ridetypes/{ride_type} | Driver availability for processing ride request
[**set_ride_types**](SandboxApi.md#set_ride_types) | **PUT** /sandbox/ridetypes | Preset types of rides for sandbox


# **set_prime_time**
> set_prime_time(sandbox_primetime)

Preset Prime Time percentage

Preset a Prime Time percentage in the region surrounding the specified location. This Prime Time percentage will be applied when requesting cost, or when requesting a ride in sandbox mode.


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.sandbox_primetime import SandboxPrimetime
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
    api_instance = openapi_client.SandboxApi(api_client)
    sandbox_primetime = openapi_client.SandboxPrimetime() # SandboxPrimetime | Prime Time to be preset in the region surrounding the lat, lng

    try:
        # Preset Prime Time percentage
        api_instance.set_prime_time(sandbox_primetime)
    except Exception as e:
        print("Exception when calling SandboxApi->set_prime_time: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sandbox_primetime** | [**SandboxPrimetime**](SandboxPrimetime.md)| Prime Time to be preset in the region surrounding the lat, lng | 

### Return type

void (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Successfully applied Prime Time in sandbox |  -  |
**400** | Validation error occurred |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_ride_status**
> SandboxRideUpdate set_ride_status(id, sandbox_ride_status)

Propagate ride through ride status

Propagate a sandbox-ride through various ride status


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.sandbox_ride_status import SandboxRideStatus
from openapi_client.models.sandbox_ride_update import SandboxRideUpdate
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

# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.SandboxApi(api_client)
    id = 'id_example' # str | The ID of the ride
    sandbox_ride_status = openapi_client.SandboxRideStatus() # SandboxRideStatus | status to propagate the ride into

    try:
        # Propagate ride through ride status
        api_response = api_instance.set_ride_status(id, sandbox_ride_status)
        print("The response of SandboxApi->set_ride_status:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling SandboxApi->set_ride_status: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 
 **sandbox_ride_status** | [**SandboxRideStatus**](SandboxRideStatus.md)| status to propagate the ride into | 

### Return type

[**SandboxRideUpdate**](SandboxRideUpdate.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with the Ride ID and the new status |  -  |
**400** | Validation error occurred |  -  |
**403** | User or client does not have permission to complete this request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_ride_type_availability**
> set_ride_type_availability(ride_type, sandbox_driver_availability)

Driver availability for processing ride request

Set driver availability for the provided ride_type in the city/region surrounding the specified location


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.sandbox_driver_availability import SandboxDriverAvailability
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
    api_instance = openapi_client.SandboxApi(api_client)
    ride_type = 'ride_type_example' # str | 
    sandbox_driver_availability = openapi_client.SandboxDriverAvailability() # SandboxDriverAvailability | Driver availability to be preset in the region surrounding the lat, lng

    try:
        # Driver availability for processing ride request
        api_instance.set_ride_type_availability(ride_type, sandbox_driver_availability)
    except Exception as e:
        print("Exception when calling SandboxApi->set_ride_type_availability: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ride_type** | **str**|  | 
 **sandbox_driver_availability** | [**SandboxDriverAvailability**](SandboxDriverAvailability.md)| Driver availability to be preset in the region surrounding the lat, lng | 

### Return type

void (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Successfully set driver availability in sandbox |  -  |
**400** | Validation error occurred |  -  |
**404** | ride_type provided in the path is not supported in the current area |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_ride_types**
> SandboxRideType set_ride_types(sandbox_ride_type)

Preset types of rides for sandbox

The sandbox-ridetypes endpoint allows you to preset the ridetypes in the region surrounding the specified latitude and longitude to allow testing different scenarios


### Example

* OAuth Authentication (User_Authentication):
* OAuth Authentication (Client_Authentication):

```python
import openapi_client
from openapi_client.models.sandbox_ride_type import SandboxRideType
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
    api_instance = openapi_client.SandboxApi(api_client)
    sandbox_ride_type = openapi_client.SandboxRideType() # SandboxRideType | Ridetypes to be preset in the region surrounding the lat, lng

    try:
        # Preset types of rides for sandbox
        api_response = api_instance.set_ride_types(sandbox_ride_type)
        print("The response of SandboxApi->set_ride_types:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling SandboxApi->set_ride_types: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **sandbox_ride_type** | [**SandboxRideType**](SandboxRideType.md)| Ridetypes to be preset in the region surrounding the lat, lng | 

### Return type

[**SandboxRideType**](SandboxRideType.md)

### Authorization

[User_Authentication](../README.md#User_Authentication), [Client_Authentication](../README.md#Client_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object listing the ridetypes at a given location |  -  |
**400** | Validation error occurred |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

