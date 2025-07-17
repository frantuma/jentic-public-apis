# UserApi

All URIs are relative to *https://api.lyft.com/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**cancelRide**](UserApi.md#cancelRide) | **POST** /rides/{id}/cancel | Cancel a ongoing requested ride |
| [**getProfile**](UserApi.md#getProfile) | **GET** /profile | The user&#39;s general info |
| [**getRide**](UserApi.md#getRide) | **GET** /rides/{id} | Get the ride detail of a given ride ID |
| [**getRideReceipt**](UserApi.md#getRideReceipt) | **GET** /rides/{id}/receipt | Get the receipt of the rides. |
| [**getRides**](UserApi.md#getRides) | **GET** /rides | List rides |
| [**newRide**](UserApi.md#newRide) | **POST** /rides | Request a Lyft |
| [**setRideDestination**](UserApi.md#setRideDestination) | **PUT** /rides/{id}/destination | Update the destination of the ride |
| [**setRideRating**](UserApi.md#setRideRating) | **PUT** /rides/{id}/rating | Add the passenger&#39;s rating, feedback, and tip |


<a id="cancelRide"></a>
# **cancelRide**
> cancelRide(id, cancellationRequest)

Cancel a ongoing requested ride

Cancel a ongoing ride which was requested earlier by providing the ride id. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String id = "id_example"; // String | The ID of the ride
    CancellationRequest cancellationRequest = new CancellationRequest(); // CancellationRequest | 
    try {
      apiInstance.cancelRide(id, cancellationRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#cancelRide");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The ID of the ride | |
| **cancellationRequest** | [**CancellationRequest**](CancellationRequest.md)|  | [optional] |

### Return type

null (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Successfully canceled the ride |  -  |
| **400** | Cancellation token required  * &#x60;cancel_confirmation_required&#x60;: a cancelation fee applies which the user must accept  * &#x60;invalid_cancel_confirmation&#x60;: provided token was invalid or expired  |  -  |
| **403** | User or client does not have permission to complete this request |  -  |
| **404** | No ride found with provided ride ID |  -  |
| **409** | You cannot cancel this ride |  -  |

<a id="getProfile"></a>
# **getProfile**
> Profile getProfile()

The user&#39;s general info

The v1 of this endpoint returns the user&#39;s ID, v2 will return more general info about the user. We require authentication for this endpoint, so we extract the user ID from the access token. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    try {
      Profile result = apiInstance.getProfile();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getProfile");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
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
| **200** | User ID was found and returned |  -  |

<a id="getRide"></a>
# **getRide**
> RideDetail getRide(id)

Get the ride detail of a given ride ID

Get the status of a ride along with information about the driver, vehicle and price of a given ride ID 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String id = "id_example"; // String | The ID of the ride
    try {
      RideDetail result = apiInstance.getRide(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getRide");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The ID of the ride | |

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
| **200** | Detailed ride information including ride status, driver information, passenger information, vehicle information, location information and price  |  -  |
| **403** | User or client does not have permission to complete this request |  -  |
| **404** | No ride found with provided ride ID |  -  |

<a id="getRideReceipt"></a>
# **getRideReceipt**
> RideReceipt getRideReceipt(id)

Get the receipt of the rides.

Get the receipt information of a processed ride by providing the ride id. Receipts will only be available to view once the payment has been processed. In the case of canceled ride, cancellation penalty is included if applicable. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String id = "id_example"; // String | The ID of the ride
    try {
      RideReceipt result = apiInstance.getRideReceipt(id);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getRideReceipt");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The ID of the ride | |

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
| **200** | Detailed ride receipt information including cancel penalty if applicable. |  -  |
| **403** | User or client does not have permission to complete this request |  -  |
| **404** | No ride receipt found with provided ride ID |  -  |

<a id="getRides"></a>
# **getRides**
> RidesResponse getRides(startTime, endTime, limit)

List rides

Get a list of past &amp; current rides for this passenger. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    OffsetDateTime startTime = OffsetDateTime.now(); // OffsetDateTime | Restrict to rides starting after this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00 
    OffsetDateTime endTime = OffsetDateTime.now(); // OffsetDateTime | Restrict to rides starting before this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00 
    Integer limit = 10; // Integer | The maximum number of rides to return. The default limit is 10 if not specified. The maximum allowed value is 50, an integer greater that 50 will return at most 50 results. 
    try {
      RidesResponse result = apiInstance.getRides(startTime, endTime, limit);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#getRides");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **startTime** | **OffsetDateTime**| Restrict to rides starting after this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00  | |
| **endTime** | **OffsetDateTime**| Restrict to rides starting before this point in time. The earliest supported date is 2015-01-01T00:00:00+00:00  | [optional] |
| **limit** | **Integer**| The maximum number of rides to return. The default limit is 10 if not specified. The maximum allowed value is 50, an integer greater that 50 will return at most 50 results.  | [optional] [default to 10] |

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
| **200** | An object with an array of up to &#x60;limit&#x60; rides taken by the user between &#x60;start_time&#x60; and &#x60;end_time&#x60;.  |  -  |
| **400** | A validation error occurred |  -  |

<a id="newRide"></a>
# **newRide**
> RideRequest newRide(ride)

Request a Lyft

Request a Lyft come pick you up at the given location. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    Ride ride = new Ride(); // Ride | Ride request information
    try {
      RideRequest result = apiInstance.newRide(ride);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#newRide");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **ride** | [**Ride**](Ride.md)| Ride request information | |

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
| **201** | An object with high level ride information. Use &#x60;GET /v1/rides/{id}&#x60; for more details. |  -  |
| **400** | The &#39;error&#39; field can be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: origin is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at origin  * &#x60;primetime_confirmation_required&#x60;: user must accept primetime. A primetime confirmation token and details will be included in the response  * &#x60;invalid_primetime_confirmation&#x60;: supplied token is invalid or expired  * &#x60;destination_prohibited&#x60;: Lyft is not allowed to drop off at that destination (e.g. some airports).  User-displayable details in the &#39;error_description&#39; field  * &#x60;client_error&#x60;: an uncategorized error. Details in the &#39;error_description&#39; field  |  -  |
| **403** | User or client does not have permission to complete this request. Specific errors include:  * &#x60;user_payment_required&#x60;: The user does not have a valid payment method on file.  They must use the Lyft app to add or update one.  * &#x60;account_disabled&#x60;: The user&#39;s account has been suspended, and they must contact Lyft support.  * &#x60;user_in_driver_mode&#x60;: The user is logged in as a driver and can&#39;t request rides until they log out  * &#x60;verified_phone_required&#x60;: The user has not provided or verified their phone number.  They can add one in the Lyft app  |  -  |
| **409** | The &#39;error&#39; field will be one of the following:  * &#x60;no_drivers_available&#x60;: No drivers are available right now  * &#x60;user_already_in_ride&#x60;: User cannot request a ride while in a ride  |  -  |

<a id="setRideDestination"></a>
# **setRideDestination**
> Location setRideDestination(id, location)

Update the destination of the ride

Add or update the ride&#39;s destination. Note that the ride must still be active (not droppedOff or canceled), and that destinations on Lyft Line rides can not be changed. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String id = "id_example"; // String | The ID of the ride
    Location location = new Location(); // Location | The coordinates and optional address of the destination
    try {
      Location result = apiInstance.setRideDestination(id, location);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#setRideDestination");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The ID of the ride | |
| **location** | [**Location**](Location.md)| The coordinates and optional address of the destination | |

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
| **200** | Successfully updated the destination. Returns the new destination. |  -  |
| **400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;invalid_destination&#x60;: Destination is generally invalid (eg. not in a Lyft service area)  * &#x60;destination_prohibited&#x60;: Lyft drop-offs are not permitted at this location (eg. some airports).  The &#39;error_description&#39; field will contain an explaination suitable to display to the user.  * &#x60;ride_is_lyft_line&#x60;: Cannot change the destination on Line rides  * &#x60;ride_is_finished&#x60;: Ride has already been completed  |  -  |
| **403** | User or client does not have permission to complete this request (&#x60;ride_does_not_belong_to_user&#x60;)  |  -  |
| **404** | No ride found with provided ride ID |  -  |

<a id="setRideRating"></a>
# **setRideRating**
> setRideRating(id, ratingRequest)

Add the passenger&#39;s rating, feedback, and tip

Add the passenger&#39;s 1 to 5 star rating of the ride, optional written feedback, and optional tip amount in minor units and currency. The ride must already be dropped off, and ratings must be given within 24 hours of drop off. For purposes of display, 5 is considered the default rating. When this endpoint is successfully called, payment processing will begin. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.UserApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    UserApi apiInstance = new UserApi(defaultClient);
    String id = "id_example"; // String | The ID of the ride
    RatingRequest ratingRequest = new RatingRequest(); // RatingRequest | The rating and optional feedback
    try {
      apiInstance.setRideRating(id, ratingRequest);
    } catch (ApiException e) {
      System.err.println("Exception when calling UserApi#setRideRating");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

| Name | Type | Description  | Notes |
|------------- | ------------- | ------------- | -------------|
| **id** | **String**| The ID of the ride | |
| **ratingRequest** | [**RatingRequest**](RatingRequest.md)| The rating and optional feedback | |

### Return type

null (empty response body)

### Authorization

[User_Authentication](../README.md#User_Authentication)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
| **204** | Successfully added the rating |  -  |
| **400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;user_cannot_rate_this_ride&#x60;: Rides can only be rated once, within 24 hours of drop-off, and before  the user starts another ride  * &#x60;tip_too_large&#x60;: tip amount is too large for this ride  * &#x60;tip_currency_not_accepted&#x60;: That tip currency is not accepted  |  -  |
| **409** | The &#39;error&#39; field will be:  * &#x60;ride_not_finished&#x60;: Ride is still in progress (not yet in the droppedOff status)  |  -  |

