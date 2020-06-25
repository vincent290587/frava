# RunningRacesApi

All URIs are relative to *https://www.strava.com/api/v3*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getRunningRaceById**](RunningRacesApi.md#getRunningRaceById) | **GET** /running_races/{id} | Get Running Race
[**getRunningRaces**](RunningRacesApi.md#getRunningRaces) | **GET** /running_races | List Running Races


<a name="getRunningRaceById"></a>
# **getRunningRaceById**
> RunningRace getRunningRaceById(id)

Get Running Race

Returns a running race for a given identifier.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.RunningRacesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: strava_oauth
OAuth strava_oauth = (OAuth) defaultClient.getAuthentication("strava_oauth");
strava_oauth.setAccessToken("YOUR ACCESS TOKEN");

RunningRacesApi apiInstance = new RunningRacesApi();
Long id = 789L; // Long | The identifier of the running race.
try {
    RunningRace result = apiInstance.getRunningRaceById(id);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RunningRacesApi#getRunningRaceById");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **id** | **Long**| The identifier of the running race. |

### Return type

[**RunningRace**](RunningRace.md)

### Authorization

[strava_oauth](../README.md#strava_oauth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="getRunningRaces"></a>
# **getRunningRaces**
> List&lt;RunningRace&gt; getRunningRaces(year)

List Running Races

Returns a list running races based on a set of search criteria.

### Example
```java
// Import classes:
//import io.swagger.client.ApiClient;
//import io.swagger.client.ApiException;
//import io.swagger.client.Configuration;
//import io.swagger.client.auth.*;
//import io.swagger.client.api.RunningRacesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: strava_oauth
OAuth strava_oauth = (OAuth) defaultClient.getAuthentication("strava_oauth");
strava_oauth.setAccessToken("YOUR ACCESS TOKEN");

RunningRacesApi apiInstance = new RunningRacesApi();
Integer year = 56; // Integer | Filters the list by a given year.
try {
    List<RunningRace> result = apiInstance.getRunningRaces(year);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RunningRacesApi#getRunningRaces");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **year** | **Integer**| Filters the list by a given year. | [optional]

### Return type

[**List&lt;RunningRace&gt;**](RunningRace.md)

### Authorization

[strava_oauth](../README.md#strava_oauth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

