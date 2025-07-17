# PublicApi

All URIs are relative to *https://api.lyft.com/v1*

| Method | HTTP request | Description |
|------------- | ------------- | -------------|
| [**getCost**](PublicApi.md#getCost) | **GET** /cost | Cost estimates |
| [**getDrivers**](PublicApi.md#getDrivers) | **GET** /drivers | Available drivers nearby |
| [**getETA**](PublicApi.md#getETA) | **GET** /eta | Pickup ETAs |
| [**getRideTypes**](PublicApi.md#getRideTypes) | **GET** /ridetypes | Types of rides |


<a id="getCost"></a>
# **getCost**
> CostEstimateResponse getCost(startLat, startLng, rideType, endLat, endLng)

Cost estimates

Estimate the cost of taking a Lyft between two points. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: Client_Authentication
    OAuth Client_Authentication = (OAuth) defaultClient.getAuthentication("Client_Authentication");
    Client_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    PublicApi apiInstance = new PublicApi(defaultClient);
    Double startLat = 3.4D; // Double | Latitude of the starting location
    Double startLng = 3.4D; // Double | Longitude of the starting location
    String rideType = "lyft"; // String | ID of a ride type
    Double endLat = 3.4D; // Double | Latitude of the ending location
    Double endLng = 3.4D; // Double | Longitude of the ending location
    try {
      CostEstimateResponse result = apiInstance.getCost(startLat, startLng, rideType, endLat, endLng);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#getCost");
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
| **startLat** | **Double**| Latitude of the starting location | |
| **startLng** | **Double**| Longitude of the starting location | |
| **rideType** | **String**| ID of a ride type | [optional] [enum: lyft, lyft_line, lyft_plus, lyft_premier, lyft_lux, lyft_luxsuv] |
| **endLat** | **Double**| Latitude of the ending location | [optional] |
| **endLng** | **Double**| Longitude of the ending location | [optional] |

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
| **200** | An object with an array of cost estimates by ride type |  -  |
| **400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: start location is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at this start location  |  -  |

<a id="getDrivers"></a>
# **getDrivers**
> NearbyDriversResponse getDrivers(lat, lng)

Available drivers nearby

The drivers endpoint returns a list of nearby drivers&#39; lat and lng at a given location. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: Client_Authentication
    OAuth Client_Authentication = (OAuth) defaultClient.getAuthentication("Client_Authentication");
    Client_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    PublicApi apiInstance = new PublicApi(defaultClient);
    Double lat = 3.4D; // Double | Latitude of a location
    Double lng = 3.4D; // Double | Longitude of a location
    try {
      NearbyDriversResponse result = apiInstance.getDrivers(lat, lng);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#getDrivers");
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
| **lat** | **Double**| Latitude of a location | |
| **lng** | **Double**| Longitude of a location | |

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
| **200** | An object with an array of available drivers sorted by eta for the given location |  -  |
| **400** | A validation error occurred |  -  |

<a id="getETA"></a>
# **getETA**
> EtaEstimateResponse getETA(lat, lng, destinationLat, destinationLng, rideType)

Pickup ETAs

The ETA endpoint lets you know how quickly a Lyft driver can come get you 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: Client_Authentication
    OAuth Client_Authentication = (OAuth) defaultClient.getAuthentication("Client_Authentication");
    Client_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    PublicApi apiInstance = new PublicApi(defaultClient);
    Double lat = 3.4D; // Double | Latitude of a location
    Double lng = 3.4D; // Double | Longitude of a location
    Double destinationLat = 3.4D; // Double | Latitude of destination location
    Double destinationLng = 3.4D; // Double | Longitude of destination location
    String rideType = "lyft"; // String | ID of a ride type
    try {
      EtaEstimateResponse result = apiInstance.getETA(lat, lng, destinationLat, destinationLng, rideType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#getETA");
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
| **lat** | **Double**| Latitude of a location | |
| **lng** | **Double**| Longitude of a location | |
| **destinationLat** | **Double**| Latitude of destination location | [optional] |
| **destinationLng** | **Double**| Longitude of destination location | [optional] |
| **rideType** | **String**| ID of a ride type | [optional] [enum: lyft, lyft_line, lyft_plus, lyft_premier, lyft_lux, lyft_luxsuv] |

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
| **200** | An object with an array of ETAs by ride type for the given location |  -  |
| **400** | The &#39;error&#39; field will be one of the following:  * &#x60;bad_parameter&#x60;: A validation error occurred  * &#x60;no_service_in_area&#x60;: location is not within a Lyft service area  * &#x60;ridetype_unavailable_in_region&#x60;: ridetype not supported at this location  |  -  |

<a id="getRideTypes"></a>
# **getRideTypes**
> RideTypesResponse getRideTypes(lat, lng, rideType)

Types of rides

The ride types endpoint returns information about what kinds of Lyft rides you can request at a given location. 

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.PublicApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("https://api.lyft.com/v1");
    
    // Configure OAuth2 access token for authorization: User_Authentication
    OAuth User_Authentication = (OAuth) defaultClient.getAuthentication("User_Authentication");
    User_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    // Configure OAuth2 access token for authorization: Client_Authentication
    OAuth Client_Authentication = (OAuth) defaultClient.getAuthentication("Client_Authentication");
    Client_Authentication.setAccessToken("YOUR ACCESS TOKEN");

    PublicApi apiInstance = new PublicApi(defaultClient);
    Double lat = 3.4D; // Double | Latitude of a location
    Double lng = 3.4D; // Double | Longitude of a location
    String rideType = "lyft"; // String | ID of a ride type
    try {
      RideTypesResponse result = apiInstance.getRideTypes(lat, lng, rideType);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling PublicApi#getRideTypes");
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
| **lat** | **Double**| Latitude of a location | |
| **lng** | **Double**| Longitude of a location | |
| **rideType** | **String**| ID of a ride type | [optional] [enum: lyft, lyft_line, lyft_plus, lyft_premier, lyft_lux, lyft_luxsuv] |

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
| **200** | An object with an array of available Ride Types for the given location |  -  |
| **400** | A validation error occurred |  -  |

