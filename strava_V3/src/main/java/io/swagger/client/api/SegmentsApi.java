/*
 * Strava API v3
 * 
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.api;

import io.swagger.client.ApiCallback;
import io.swagger.client.ApiClient;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.Configuration;
import io.swagger.client.Pair;
import io.swagger.client.ProgressRequestBody;
import io.swagger.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import io.swagger.client.model.DetailedSegment;
import io.swagger.client.model.ExplorerResponse;
import io.swagger.client.model.Fault;
import io.swagger.client.model.SummarySegment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SegmentsApi {
    private ApiClient apiClient;

    public SegmentsApi() {
        this(Configuration.getDefaultApiClient());
    }

    public SegmentsApi(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return apiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Build call for exploreSegments
     * @param bounds The latitude and longitude for two points describing a rectangular boundary for the search: [southwest corner latitutde, southwest corner longitude, northeast corner latitude, northeast corner longitude] (required)
     * @param activityType Desired activity type. (optional)
     * @param minCat The minimum climbing category. (optional)
     * @param maxCat The maximum climbing category. (optional)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call exploreSegmentsCall(List<Float> bounds, String activityType, Integer minCat, Integer maxCat, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/segments/explore";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (bounds != null)
        localVarCollectionQueryParams.addAll(apiClient.parameterToPairs("csv", "bounds", bounds));
        if (activityType != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("activity_type", activityType));
        if (minCat != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("min_cat", minCat));
        if (maxCat != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("max_cat", maxCat));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "strava_oauth" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call exploreSegmentsValidateBeforeCall(List<Float> bounds, String activityType, Integer minCat, Integer maxCat, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'bounds' is set
        if (bounds == null) {
            throw new ApiException("Missing the required parameter 'bounds' when calling exploreSegments(Async)");
        }
        

        com.squareup.okhttp.Call call = exploreSegmentsCall(bounds, activityType, minCat, maxCat, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Explore segments
     * Returns the top 10 segments matching a specified query.
     * @param bounds The latitude and longitude for two points describing a rectangular boundary for the search: [southwest corner latitutde, southwest corner longitude, northeast corner latitude, northeast corner longitude] (required)
     * @param activityType Desired activity type. (optional)
     * @param minCat The minimum climbing category. (optional)
     * @param maxCat The maximum climbing category. (optional)
     * @return ExplorerResponse
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ExplorerResponse exploreSegments(List<Float> bounds, String activityType, Integer minCat, Integer maxCat) throws ApiException {
        ApiResponse<ExplorerResponse> resp = exploreSegmentsWithHttpInfo(bounds, activityType, minCat, maxCat);
        return resp.getData();
    }

    /**
     * Explore segments
     * Returns the top 10 segments matching a specified query.
     * @param bounds The latitude and longitude for two points describing a rectangular boundary for the search: [southwest corner latitutde, southwest corner longitude, northeast corner latitude, northeast corner longitude] (required)
     * @param activityType Desired activity type. (optional)
     * @param minCat The minimum climbing category. (optional)
     * @param maxCat The maximum climbing category. (optional)
     * @return ApiResponse&lt;ExplorerResponse&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<ExplorerResponse> exploreSegmentsWithHttpInfo(List<Float> bounds, String activityType, Integer minCat, Integer maxCat) throws ApiException {
        com.squareup.okhttp.Call call = exploreSegmentsValidateBeforeCall(bounds, activityType, minCat, maxCat, null, null);
        Type localVarReturnType = new TypeToken<ExplorerResponse>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Explore segments (asynchronously)
     * Returns the top 10 segments matching a specified query.
     * @param bounds The latitude and longitude for two points describing a rectangular boundary for the search: [southwest corner latitutde, southwest corner longitude, northeast corner latitude, northeast corner longitude] (required)
     * @param activityType Desired activity type. (optional)
     * @param minCat The minimum climbing category. (optional)
     * @param maxCat The maximum climbing category. (optional)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call exploreSegmentsAsync(List<Float> bounds, String activityType, Integer minCat, Integer maxCat, final ApiCallback<ExplorerResponse> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = exploreSegmentsValidateBeforeCall(bounds, activityType, minCat, maxCat, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<ExplorerResponse>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getLoggedInAthleteStarredSegments
     * @param page Page number. Defaults to 1. (optional)
     * @param perPage Number of items per page. Defaults to 30. (optional, default to 30)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getLoggedInAthleteStarredSegmentsCall(Integer page, Integer perPage, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/segments/starred";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        if (page != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("page", page));
        if (perPage != null)
        localVarQueryParams.addAll(apiClient.parameterToPair("per_page", perPage));

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "strava_oauth" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getLoggedInAthleteStarredSegmentsValidateBeforeCall(Integer page, Integer perPage, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        

        com.squareup.okhttp.Call call = getLoggedInAthleteStarredSegmentsCall(page, perPage, progressListener, progressRequestListener);
        return call;

    }

    /**
     * List Starred Segments
     * List of the authenticated athlete&#39;s starred segments. Private segments are filtered out unless requested by a token with read_all scope.
     * @param page Page number. Defaults to 1. (optional)
     * @param perPage Number of items per page. Defaults to 30. (optional, default to 30)
     * @return List&lt;SummarySegment&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public List<SummarySegment> getLoggedInAthleteStarredSegments(Integer page, Integer perPage) throws ApiException {
        ApiResponse<List<SummarySegment>> resp = getLoggedInAthleteStarredSegmentsWithHttpInfo(page, perPage);
        return resp.getData();
    }

    /**
     * List Starred Segments
     * List of the authenticated athlete&#39;s starred segments. Private segments are filtered out unless requested by a token with read_all scope.
     * @param page Page number. Defaults to 1. (optional)
     * @param perPage Number of items per page. Defaults to 30. (optional, default to 30)
     * @return ApiResponse&lt;List&lt;SummarySegment&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<List<SummarySegment>> getLoggedInAthleteStarredSegmentsWithHttpInfo(Integer page, Integer perPage) throws ApiException {
        com.squareup.okhttp.Call call = getLoggedInAthleteStarredSegmentsValidateBeforeCall(page, perPage, null, null);
        Type localVarReturnType = new TypeToken<List<SummarySegment>>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * List Starred Segments (asynchronously)
     * List of the authenticated athlete&#39;s starred segments. Private segments are filtered out unless requested by a token with read_all scope.
     * @param page Page number. Defaults to 1. (optional)
     * @param perPage Number of items per page. Defaults to 30. (optional, default to 30)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getLoggedInAthleteStarredSegmentsAsync(Integer page, Integer perPage, final ApiCallback<List<SummarySegment>> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getLoggedInAthleteStarredSegmentsValidateBeforeCall(page, perPage, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<List<SummarySegment>>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for getSegmentById
     * @param id The identifier of the segment. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call getSegmentByIdCall(Long id, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/segments/{id}"
            .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "strava_oauth" };
        return apiClient.buildCall(localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call getSegmentByIdValidateBeforeCall(Long id, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling getSegmentById(Async)");
        }
        

        com.squareup.okhttp.Call call = getSegmentByIdCall(id, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Get Segment
     * Returns the specified segment. read_all scope required in order to retrieve athlete-specific segment information, or to retrieve private segments.
     * @param id The identifier of the segment. (required)
     * @return DetailedSegment
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DetailedSegment getSegmentById(Long id) throws ApiException {
        ApiResponse<DetailedSegment> resp = getSegmentByIdWithHttpInfo(id);
        return resp.getData();
    }

    /**
     * Get Segment
     * Returns the specified segment. read_all scope required in order to retrieve athlete-specific segment information, or to retrieve private segments.
     * @param id The identifier of the segment. (required)
     * @return ApiResponse&lt;DetailedSegment&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DetailedSegment> getSegmentByIdWithHttpInfo(Long id) throws ApiException {
        com.squareup.okhttp.Call call = getSegmentByIdValidateBeforeCall(id, null, null);
        Type localVarReturnType = new TypeToken<DetailedSegment>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Get Segment (asynchronously)
     * Returns the specified segment. read_all scope required in order to retrieve athlete-specific segment information, or to retrieve private segments.
     * @param id The identifier of the segment. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call getSegmentByIdAsync(Long id, final ApiCallback<DetailedSegment> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = getSegmentByIdValidateBeforeCall(id, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DetailedSegment>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
    /**
     * Build call for starSegment
     * @param id The identifier of the segment to star. (required)
     * @param starred If true, star the segment; if false, unstar the segment. (required)
     * @param progressListener Progress listener
     * @param progressRequestListener Progress request listener
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     */
    public com.squareup.okhttp.Call starSegmentCall(Long id, Boolean starred, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/segments/{id}/starred"
            .replaceAll("\\{" + "id" + "\\}", apiClient.escapeString(id.toString()));

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();

        Map<String, String> localVarHeaderParams = new HashMap<String, String>();

        Map<String, Object> localVarFormParams = new HashMap<String, Object>();
        if (starred != null)
        localVarFormParams.put("starred", starred);

        final String[] localVarAccepts = {
            "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) localVarHeaderParams.put("Accept", localVarAccept);

        final String[] localVarContentTypes = {
            
        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);
        localVarHeaderParams.put("Content-Type", localVarContentType);

        if(progressListener != null) {
            apiClient.getHttpClient().networkInterceptors().add(new com.squareup.okhttp.Interceptor() {
                @Override
                public com.squareup.okhttp.Response intercept(com.squareup.okhttp.Interceptor.Chain chain) throws IOException {
                    com.squareup.okhttp.Response originalResponse = chain.proceed(chain.request());
                    return originalResponse.newBuilder()
                    .body(new ProgressResponseBody(originalResponse.body(), progressListener))
                    .build();
                }
            });
        }

        String[] localVarAuthNames = new String[] { "strava_oauth" };
        return apiClient.buildCall(localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAuthNames, progressRequestListener);
    }

    @SuppressWarnings("rawtypes")
    private com.squareup.okhttp.Call starSegmentValidateBeforeCall(Long id, Boolean starred, final ProgressResponseBody.ProgressListener progressListener, final ProgressRequestBody.ProgressRequestListener progressRequestListener) throws ApiException {
        
        // verify the required parameter 'id' is set
        if (id == null) {
            throw new ApiException("Missing the required parameter 'id' when calling starSegment(Async)");
        }
        
        // verify the required parameter 'starred' is set
        if (starred == null) {
            throw new ApiException("Missing the required parameter 'starred' when calling starSegment(Async)");
        }
        

        com.squareup.okhttp.Call call = starSegmentCall(id, starred, progressListener, progressRequestListener);
        return call;

    }

    /**
     * Star Segment
     * Stars/Unstars the given segment for the authenticated athlete. Requires profile:write scope.
     * @param id The identifier of the segment to star. (required)
     * @param starred If true, star the segment; if false, unstar the segment. (required)
     * @return DetailedSegment
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public DetailedSegment starSegment(Long id, Boolean starred) throws ApiException {
        ApiResponse<DetailedSegment> resp = starSegmentWithHttpInfo(id, starred);
        return resp.getData();
    }

    /**
     * Star Segment
     * Stars/Unstars the given segment for the authenticated athlete. Requires profile:write scope.
     * @param id The identifier of the segment to star. (required)
     * @param starred If true, star the segment; if false, unstar the segment. (required)
     * @return ApiResponse&lt;DetailedSegment&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     */
    public ApiResponse<DetailedSegment> starSegmentWithHttpInfo(Long id, Boolean starred) throws ApiException {
        com.squareup.okhttp.Call call = starSegmentValidateBeforeCall(id, starred, null, null);
        Type localVarReturnType = new TypeToken<DetailedSegment>(){}.getType();
        return apiClient.execute(call, localVarReturnType);
    }

    /**
     * Star Segment (asynchronously)
     * Stars/Unstars the given segment for the authenticated athlete. Requires profile:write scope.
     * @param id The identifier of the segment to star. (required)
     * @param starred If true, star the segment; if false, unstar the segment. (required)
     * @param callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     */
    public com.squareup.okhttp.Call starSegmentAsync(Long id, Boolean starred, final ApiCallback<DetailedSegment> callback) throws ApiException {

        ProgressResponseBody.ProgressListener progressListener = null;
        ProgressRequestBody.ProgressRequestListener progressRequestListener = null;

        if (callback != null) {
            progressListener = new ProgressResponseBody.ProgressListener() {
                @Override
                public void update(long bytesRead, long contentLength, boolean done) {
                    callback.onDownloadProgress(bytesRead, contentLength, done);
                }
            };

            progressRequestListener = new ProgressRequestBody.ProgressRequestListener() {
                @Override
                public void onRequestProgress(long bytesWritten, long contentLength, boolean done) {
                    callback.onUploadProgress(bytesWritten, contentLength, done);
                }
            };
        }

        com.squareup.okhttp.Call call = starSegmentValidateBeforeCall(id, starred, progressListener, progressRequestListener);
        Type localVarReturnType = new TypeToken<DetailedSegment>(){}.getType();
        apiClient.executeAsync(call, localVarReturnType, callback);
        return call;
    }
}
