
# DetailedSegment

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | The unique identifier of this segment |  [optional]
**name** | **String** | The name of this segment |  [optional]
**activityType** | [**ActivityTypeEnum**](#ActivityTypeEnum) |  |  [optional]
**distance** | **Float** | The segment&#39;s distance, in meters |  [optional]
**averageGrade** | **Float** | The segment&#39;s average grade, in percents |  [optional]
**maximumGrade** | **Float** | The segments&#39;s maximum grade, in percents |  [optional]
**elevationHigh** | **Float** | The segments&#39;s highest elevation, in meters |  [optional]
**elevationLow** | **Float** | The segments&#39;s lowest elevation, in meters |  [optional]
**startLatlng** | [**LatLng**](LatLng.md) |  |  [optional]
**endLatlng** | [**LatLng**](LatLng.md) |  |  [optional]
**climbCategory** | **Integer** | The category of the climb [0, 5]. Higher is harder ie. 5 is Hors catégorie, 0 is uncategorized in climb_category. |  [optional]
**city** | **String** | The segments&#39;s city. |  [optional]
**state** | **String** | The segments&#39;s state or geographical region. |  [optional]
**country** | **String** | The segment&#39;s country. |  [optional]
**_private** | **Boolean** | Whether this segment is private. |  [optional]
**athletePrEffort** | [**SummarySegmentEffort**](SummarySegmentEffort.md) |  |  [optional]
**createdAt** | [**OffsetDateTime**](OffsetDateTime.md) | The time at which the segment was created. |  [optional]
**updatedAt** | [**OffsetDateTime**](OffsetDateTime.md) | The time at which the segment was last updated. |  [optional]
**totalElevationGain** | **Float** | The segment&#39;s total elevation gain. |  [optional]
**map** | [**PolylineMap**](PolylineMap.md) |  |  [optional]
**effortCount** | **Integer** | The total number of efforts for this segment |  [optional]
**athleteCount** | **Integer** | The number of unique athletes who have an effort for this segment |  [optional]
**hazardous** | **Boolean** | Whether this segment is considered hazardous |  [optional]
**starCount** | **Integer** | The number of stars for this segment |  [optional]


<a name="ActivityTypeEnum"></a>
## Enum: ActivityTypeEnum
Name | Value
---- | -----
RIDE | &quot;Ride&quot;
RUN | &quot;Run&quot;



