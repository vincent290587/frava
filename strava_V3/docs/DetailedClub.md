
# DetailedClub

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**id** | **Long** | The club&#39;s unique identifier. |  [optional]
**resourceState** | **Integer** | Resource state, indicates level of detail. Possible values: 1 -&gt; \&quot;meta\&quot;, 2 -&gt; \&quot;summary\&quot;, 3 -&gt; \&quot;detail\&quot; |  [optional]
**name** | **String** | The club&#39;s name. |  [optional]
**profileMedium** | **String** | URL to a 60x60 pixel profile picture. |  [optional]
**coverPhoto** | **String** | URL to a ~1185x580 pixel cover photo. |  [optional]
**coverPhotoSmall** | **String** | URL to a ~360x176  pixel cover photo. |  [optional]
**sportType** | [**SportTypeEnum**](#SportTypeEnum) |  |  [optional]
**city** | **String** | The club&#39;s city. |  [optional]
**state** | **String** | The club&#39;s state or geographical region. |  [optional]
**country** | **String** | The club&#39;s country. |  [optional]
**_private** | **Boolean** | Whether the club is private. |  [optional]
**memberCount** | **Integer** | The club&#39;s member count. |  [optional]
**featured** | **Boolean** | Whether the club is featured or not. |  [optional]
**verified** | **Boolean** | Whether the club is verified or not. |  [optional]
**url** | **String** | The club&#39;s vanity URL. |  [optional]
**membership** | [**MembershipEnum**](#MembershipEnum) | The membership status of the logged-in athlete. |  [optional]
**admin** | **Boolean** | Whether the currently logged-in athlete is an administrator of this club. |  [optional]
**owner** | **Boolean** | Whether the currently logged-in athlete is the owner of this club. |  [optional]
**followingCount** | **Integer** | The number of athletes in the club that the logged-in athlete follows. |  [optional]


<a name="SportTypeEnum"></a>
## Enum: SportTypeEnum
Name | Value
---- | -----
CYCLING | &quot;cycling&quot;
RUNNING | &quot;running&quot;
TRIATHLON | &quot;triathlon&quot;
OTHER | &quot;other&quot;


<a name="MembershipEnum"></a>
## Enum: MembershipEnum
Name | Value
---- | -----
MEMBER | &quot;member&quot;
PENDING | &quot;pending&quot;



