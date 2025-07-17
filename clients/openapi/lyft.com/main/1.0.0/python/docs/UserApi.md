# openapi_client.UserApi

All URIs are relative to *https://api.lyft.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**cancel_ride**](UserApi.md#cancel_ride) | **POST** /rides/{id}/cancel | Cancel a ongoing requested ride
[**get_profile**](UserApi.md#get_profile) | **GET** /profile | The user&#39;s general info
[**get_ride**](UserApi.md#get_ride) | **GET** /rides/{id} | Get the ride detail of a given ride ID
[**get_ride_receipt**](UserApi.md#get_ride_receipt) | **GET** /rides/{id}/receipt | Get the receipt of the rides.
[**get_rides**](UserApi.md#get_rides) | **GET** /rides | List rides
[**new_ride**](UserApi.md#new_ride) | **POST** /rides | Request a Lyft
[**set_ride_destination**](UserApi.md#set_ride_destination) | **PUT** /rides/{id}/destination | Update the destination of the ride
[**set_ride_rating**](UserApi.md#set_ride_rating) | **PUT** /rides/{id}/rating | Add the passenger&#39;s rating, feedback, and tip


# **cancel_ride**
> cancel_ride(id, cancellation_request=cancellation_request)

Cancel a ongoing requested ride

Cancel a ongoing ride which was requested earlier by providing the ride id.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.cancellation_request import CancellationRequest
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
    api_instance = openapi_client.UserApi(api_client)
    id = 'id_example' # str | The ID of the ride
    cancellation_request = openapi_client.CancellationRequest() # CancellationRequest |  (optional)

    try:
        # Cancel a ongoing requested ride
        api_instance.cancel_ride(id, cancellation_request=cancellation_request)
    except Exception as e:
        print("Exception when calling UserApi->cancel_ride: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 
 **cancellation_request** | [**CancellationRequest**](CancellationRequest.md)|  | [optional] 

### Return type

void (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Successfully canceled the ride |  -  |
**400** | Cancellation token required  * &#x60;cancel_confirmation_required&#x60;: a cancelation fee applies which the user must accept  * &#x60;invalid_cancel_confirmation&#x60;: provided token was invalid or expired  |  -  |
**403** | User or client does not have permission to complete this request |  -  |
**404** | No ride found with provided ride ID |  -  |
**409** | You cannot cancel this ride |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_profile**
> Profile get_profile()

The user's general info

The v1 of this endpoint returns the user's ID, v2 will return more general info about the user. We require authentication for this endpoint, so we extract the user ID from the access token.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.profile import Profile
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
    api_instance = openapi_client.UserApi(api_client)

    try:
        # The user's general info
        api_response = api_instance.get_profile()
        print("The response of UserApi->get_profile:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->get_profile: %s\n" % e)
```



### Parameters

This endpoint does not need any parameter.

### Return type

[**Profile**](Profile.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | User ID was found and returned |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_ride**
> RideDetail get_ride(id)

Get the ride detail of a given ride ID

Get the status of a ride along with information about the driver, vehicle and price of a given ride ID


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.ride_detail import RideDetail
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
    api_instance = openapi_client.UserApi(api_client)
    id = 'id_example' # str | The ID of the ride

    try:
        # Get the ride detail of a given ride ID
        api_response = api_instance.get_ride(id)
        print("The response of UserApi->get_ride:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->get_ride: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 

### Return type

[**RideDetail**](RideDetail.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Detailed ride information including ride status, driver information, passenger information, vehicle information, location information and price  |  -  |
**403** | User or client does not have permission to complete this request |  -  |
**404** | No ride found with provided ride ID |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_ride_receipt**
> RideReceipt get_ride_receipt(id)

Get the receipt of the rides.

Get the receipt information of a processed ride by providing the ride id. Receipts will only be available to view once the payment has been processed. In the case of canceled ride, cancellation penalty is included if applicable.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.ride_receipt import RideReceipt
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
    api_instance = openapi_client.UserApi(api_client)
    id = 'id_example' # str | The ID of the ride

    try:
        # Get the receipt of the rides.
        api_response = api_instance.get_ride_receipt(id)
        print("The response of UserApi->get_ride_receipt:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->get_ride_receipt: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 

### Return type

[**RideReceipt**](RideReceipt.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Detailed ride receipt information including cancel penalty if applicable. |  -  |
**403** | User or client does not have permission to complete this request |  -  |
**404** | No ride receipt found with provided ride ID |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **get_rides**
> RidesResponse get_rides(start_time, end_time=end_time, limit=limit)

List rides

Get a list of past & current rides for this passenger.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.rides_response import RidesResponse
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
    api_instance = openapi_client.UserApi(api_client)
    start_time = '2013-10-20T19:20:30+01:00' # datetime | Restrict to rides starting after this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00 
    end_time = '2013-10-20T19:20:30+01:00' # datetime | Restrict to rides starting before this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00  (optional)
    limit = 10 # int | The maximum number of rides to return. The default limit is 10 if not specified. The maximum allowed value is 50, an integer greater that 50 will return at most 50 results.  (optional) (default to 10)

    try:
        # List rides
        api_response = api_instance.get_rides(start_time, end_time=end_time, limit=limit)
        print("The response of UserApi->get_rides:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->get_rides: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **start_time** | **datetime**| Restrict to rides starting after this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00  | 
 **end_time** | **datetime**| Restrict to rides starting before this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00  | [optional] 
 **limit** | **int**| The maximum number of rides to return. The default limit is 10 if not specified. The maximum allowed value is 50, an integer greater that 50 will return at most 50 results.  | [optional] [default to 10]

### Return type

[**RidesResponse**](RidesResponse.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | An object with an array of up to &#x60;limit&#x60; rides taken by the user between &#x60;start_time&#x60; and &#x60;end_time&#x60;.  |  -  |
**400** | A validation error occurred |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **new_ride**
> RideRequest new_ride(ride)

Request a Lyft

Request a Lyft come pick you up at the given location.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.ride import Ride
from openapi_client.models.ride_request import RideRequest
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
    api_instance = openapi_client.UserApi(api_client)
    ride = openapi_client.Ride() # Ride | Ride request information

    try:
        # Request a Lyft
        api_response = api_instance.new_ride(ride)
        print("The response of UserApi->new_ride:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->new_ride: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **ride** | [**Ride**](Ride.md)| Ride request information | 

### Return type

[**RideRequest**](RideRequest.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**201** | An object with high level ride information. Use &#x60;GET /v1/rides/{id}&#x60; for more details. |  -  |
**400** | The &#39;error&#39; field can be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: origin is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at origin  * &#x60;primetime_confirmation_required&#x60;: user must accept primetime. A primetime confirmation token and details will be included in the response  * &#x60;invalid_primetime_confirmation&#x60;: supplied token is invalid or expired  * &#x60;destination_prohibited&#x60;: Lyft is not allowed to drop off at that destination (e.g. some airports).  User-displayable details in the &#39;error_description&#39; field  * &#x60;client_error&#x60;: an uncategorized error. Details in the &#39;error_description&#39; field  |  -  |
**403** | User or client does not have permission to complete this request. Specific errors include:  * &#x60;user_payment_required&#x60;: The user does not have a valid payment method on file.  They must use the Lyft app to add or update one.  * &#x60;account_disabled&#x60;: The user&#39;s account has been suspended, and they must contact Lyft support.  * &#x60;user_in_driver_mode&#x60;: The user is logged in as a driver and can&#39;t request rides until they log out  * &#x60;verified_phone_required&#x60;: The user has not provided or verified their phone number.  They can add one in the Lyft app  |  -  |
**409** | The &#39;error&#39; field will be one of the following:  * &#x60;no_drivers_available&#x60;: No drivers are available right now  * &#x60;user_already_in_ride&#x60;: User cannot request a ride while in a ride  |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_ride_destination**
> Location set_ride_destination(id, location)

Update the destination of the ride

Add or update the ride's destination. Note that the ride must still be active (not droppedOff or canceled), and that destinations on Lyft Line rides can not be changed.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.location import Location
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
    api_instance = openapi_client.UserApi(api_client)
    id = 'id_example' # str | The ID of the ride
    location = openapi_client.Location() # Location | The coordinates and optional address of the destination

    try:
        # Update the destination of the ride
        api_response = api_instance.set_ride_destination(id, location)
        print("The response of UserApi->set_ride_destination:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling UserApi->set_ride_destination: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 
 **location** | [**Location**](Location.md)| The coordinates and optional address of the destination | 

### Return type

[**Location**](Location.md)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successfully updated the destination. Returns the new destination. |  -  |
**400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;invalid_destination&#x60;: Destination is generally invalid (eg. not in a Lyft service area)  * &#x60;destination_prohibited&#x60;: Lyft drop-offs are not permitted at this location (eg. some airports).  The &#39;error_description&#39; field will contain an explaination suitable to display to the user.  * &#x60;ride_is_lyft_line&#x60;: Cannot change the destination on Line rides  * &#x60;ride_is_finished&#x60;: Ride has already been completed  |  -  |
**403** | User or client does not have permission to complete this request (&#x60;ride_does_not_belong_to_user&#x60;)  |  -  |
**404** | No ride found with provided ride ID |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

# **set_ride_rating**
> set_ride_rating(id, rating_request)

Add the passenger's rating, feedback, and tip

Add the passenger's 1 to 5 star rating of the ride, optional written feedback, and optional tip amount in minor units and currency. The ride must already be dropped off, and ratings must be given within 24 hours of drop off. For purposes of display, 5 is considered the default rating. When this endpoint is successfully called, payment processing will begin.


### Example

* OAuth Authentication (User_Authentication):

```python
import openapi_client
from openapi_client.models.rating_request import RatingRequest
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
    api_instance = openapi_client.UserApi(api_client)
    id = 'id_example' # str | The ID of the ride
    rating_request = openapi_client.RatingRequest() # RatingRequest | The rating and optional feedback

    try:
        # Add the passenger's rating, feedback, and tip
        api_instance.set_ride_rating(id, rating_request)
    except Exception as e:
        print("Exception when calling UserApi->set_ride_rating: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **str**| The ID of the ride | 
 **rating_request** | [**RatingRequest**](RatingRequest.md)| The rating and optional feedback | 

### Return type

void (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**204** | Successfully added the rating |  -  |
**400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;user_cannot_rate_this_ride&#x60;: Rides can only be rated once, within 24 hours of drop-off, and before  the user starts another ride  * &#x60;tip_too_large&#x60;: tip amount is too large for this ride  * &#x60;tip_currency_not_accepted&#x60;: That tip currency is not accepted  |  -  |
**409** | The &#39;error&#39; field will be:  * &#x60;ride_not_finished&#x60;: Ride is still in progress (not yet in the droppedOff status)  |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

