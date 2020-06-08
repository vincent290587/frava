
# Route

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**athlete** | [**SummaryAthlete**](SummaryAthlete.md) |  |  [optional]
**description** | **String** | The description of the route |  [optional]
**distance** | **Float** | The route&#39;s distance, in meters |  [optional]
**elevationGain** | **Float** | The route&#39;s elevation gain. |  [optional]
**id** | **Integer** | The unique identifier of this route |  [optional]
**map** | [**PolylineMap**](PolylineMap.md) |  |  [optional]
**name** | **String** | The name of this route |  [optional]
**_private** | **Boolean** | Whether this route is private |  [optional]
**starred** | **Boolean** | Whether this route is starred by the logged-in athlete |  [optional]
**timestamp** | **Integer** | An epoch timestamp of when the route was created |  [optional]
**type** | **Integer** | This route&#39;s type (1 for ride, 2 for runs) |  [optional]
**subType** | **Integer** | This route&#39;s sub-type (1 for road, 2 for mountain bike, 3 for cross, 4 for trail, 5 for mixed) |  [optional]
**segments** | [**List&lt;SummarySegment&gt;**](SummarySegment.md) | The segments traversed by this route |  [optional]



