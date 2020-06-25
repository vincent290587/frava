/*
 * Strava API v3
 * The [Swagger Playground](https://developers.strava.com/playground) is the easiest way to familiarize yourself with the Strava API by submitting HTTP requests and observing the responses before you write any client code. It will show what a response will look like with different endpoints depending on the authorization scope you receive from your athletes. To use the Playground, go to https://www.strava.com/settings/api and change your “Authorization Callback Domain” to developers.strava.com. Please note, we only support Swagger 2.0. There is a known issue where you can only select one scope at a time. For more information, please check the section “client code” at https://developers.strava.com/docs.
 *
 * OpenAPI spec version: 3.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package io.swagger.client.model;

import java.util.Objects;
import java.util.Arrays;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.client.model.LatLng;
import io.swagger.client.model.PolylineMap;
import io.swagger.client.model.SummarySegment;
import io.swagger.client.model.SummarySegmentEffort;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * DetailedSegment
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-25T14:36:57.813+02:00")
public class DetailedSegment {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("name")
  private String name = null;

  /**
   * Gets or Sets activityType
   */
  @JsonAdapter(ActivityTypeEnum.Adapter.class)
  public enum ActivityTypeEnum {
    RIDE("Ride"),
    
    RUN("Run");

    private String value;

    ActivityTypeEnum(String value) {
      this.value = value;
    }

    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    public static ActivityTypeEnum fromValue(String text) {
      for (ActivityTypeEnum b : ActivityTypeEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }

    public static class Adapter extends TypeAdapter<ActivityTypeEnum> {
      @Override
      public void write(final JsonWriter jsonWriter, final ActivityTypeEnum enumeration) throws IOException {
        jsonWriter.value(enumeration.getValue());
      }

      @Override
      public ActivityTypeEnum read(final JsonReader jsonReader) throws IOException {
        String value = jsonReader.nextString();
        return ActivityTypeEnum.fromValue(String.valueOf(value));
      }
    }
  }

  @SerializedName("activity_type")
  private ActivityTypeEnum activityType = null;

  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("average_grade")
  private Float averageGrade = null;

  @SerializedName("maximum_grade")
  private Float maximumGrade = null;

  @SerializedName("elevation_high")
  private Float elevationHigh = null;

  @SerializedName("elevation_low")
  private Float elevationLow = null;

  @SerializedName("start_latlng")
  private LatLng startLatlng = null;

  @SerializedName("end_latlng")
  private LatLng endLatlng = null;

  @SerializedName("climb_category")
  private Integer climbCategory = null;

  @SerializedName("city")
  private String city = null;

  @SerializedName("state")
  private String state = null;

  @SerializedName("country")
  private String country = null;

  @SerializedName("private")
  private Boolean _private = null;

  @SerializedName("athlete_pr_effort")
  private SummarySegmentEffort athletePrEffort = null;

  @SerializedName("created_at")
  private OffsetDateTime createdAt = null;

  @SerializedName("updated_at")
  private OffsetDateTime updatedAt = null;

  @SerializedName("total_elevation_gain")
  private Float totalElevationGain = null;

  @SerializedName("map")
  private PolylineMap map = null;

  @SerializedName("effort_count")
  private Integer effortCount = null;

  @SerializedName("athlete_count")
  private Integer athleteCount = null;

  @SerializedName("hazardous")
  private Boolean hazardous = null;

  @SerializedName("star_count")
  private Integer starCount = null;

  public DetailedSegment id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * The unique identifier of this segment
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier of this segment")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DetailedSegment name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of this segment
   * @return name
  **/
  @ApiModelProperty(value = "The name of this segment")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DetailedSegment activityType(ActivityTypeEnum activityType) {
    this.activityType = activityType;
    return this;
  }

   /**
   * Get activityType
   * @return activityType
  **/
  @ApiModelProperty(value = "")
  public ActivityTypeEnum getActivityType() {
    return activityType;
  }

  public void setActivityType(ActivityTypeEnum activityType) {
    this.activityType = activityType;
  }

  public DetailedSegment distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The segment&#39;s distance, in meters
   * @return distance
  **/
  @ApiModelProperty(value = "The segment's distance, in meters")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public DetailedSegment averageGrade(Float averageGrade) {
    this.averageGrade = averageGrade;
    return this;
  }

   /**
   * The segment&#39;s average grade, in percents
   * @return averageGrade
  **/
  @ApiModelProperty(value = "The segment's average grade, in percents")
  public Float getAverageGrade() {
    return averageGrade;
  }

  public void setAverageGrade(Float averageGrade) {
    this.averageGrade = averageGrade;
  }

  public DetailedSegment maximumGrade(Float maximumGrade) {
    this.maximumGrade = maximumGrade;
    return this;
  }

   /**
   * The segments&#39;s maximum grade, in percents
   * @return maximumGrade
  **/
  @ApiModelProperty(value = "The segments's maximum grade, in percents")
  public Float getMaximumGrade() {
    return maximumGrade;
  }

  public void setMaximumGrade(Float maximumGrade) {
    this.maximumGrade = maximumGrade;
  }

  public DetailedSegment elevationHigh(Float elevationHigh) {
    this.elevationHigh = elevationHigh;
    return this;
  }

   /**
   * The segments&#39;s highest elevation, in meters
   * @return elevationHigh
  **/
  @ApiModelProperty(value = "The segments's highest elevation, in meters")
  public Float getElevationHigh() {
    return elevationHigh;
  }

  public void setElevationHigh(Float elevationHigh) {
    this.elevationHigh = elevationHigh;
  }

  public DetailedSegment elevationLow(Float elevationLow) {
    this.elevationLow = elevationLow;
    return this;
  }

   /**
   * The segments&#39;s lowest elevation, in meters
   * @return elevationLow
  **/
  @ApiModelProperty(value = "The segments's lowest elevation, in meters")
  public Float getElevationLow() {
    return elevationLow;
  }

  public void setElevationLow(Float elevationLow) {
    this.elevationLow = elevationLow;
  }

  public DetailedSegment startLatlng(LatLng startLatlng) {
    this.startLatlng = startLatlng;
    return this;
  }

   /**
   * Get startLatlng
   * @return startLatlng
  **/
  @ApiModelProperty(value = "")
  public LatLng getStartLatlng() {
    return startLatlng;
  }

  public void setStartLatlng(LatLng startLatlng) {
    this.startLatlng = startLatlng;
  }

  public DetailedSegment endLatlng(LatLng endLatlng) {
    this.endLatlng = endLatlng;
    return this;
  }

   /**
   * Get endLatlng
   * @return endLatlng
  **/
  @ApiModelProperty(value = "")
  public LatLng getEndLatlng() {
    return endLatlng;
  }

  public void setEndLatlng(LatLng endLatlng) {
    this.endLatlng = endLatlng;
  }

  public DetailedSegment climbCategory(Integer climbCategory) {
    this.climbCategory = climbCategory;
    return this;
  }

   /**
   * The category of the climb [0, 5]. Higher is harder ie. 5 is Hors catégorie, 0 is uncategorized in climb_category.
   * @return climbCategory
  **/
  @ApiModelProperty(value = "The category of the climb [0, 5]. Higher is harder ie. 5 is Hors catégorie, 0 is uncategorized in climb_category.")
  public Integer getClimbCategory() {
    return climbCategory;
  }

  public void setClimbCategory(Integer climbCategory) {
    this.climbCategory = climbCategory;
  }

  public DetailedSegment city(String city) {
    this.city = city;
    return this;
  }

   /**
   * The segments&#39;s city.
   * @return city
  **/
  @ApiModelProperty(value = "The segments's city.")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public DetailedSegment state(String state) {
    this.state = state;
    return this;
  }

   /**
   * The segments&#39;s state or geographical region.
   * @return state
  **/
  @ApiModelProperty(value = "The segments's state or geographical region.")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public DetailedSegment country(String country) {
    this.country = country;
    return this;
  }

   /**
   * The segment&#39;s country.
   * @return country
  **/
  @ApiModelProperty(value = "The segment's country.")
  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public DetailedSegment _private(Boolean _private) {
    this._private = _private;
    return this;
  }

   /**
   * Whether this segment is private.
   * @return _private
  **/
  @ApiModelProperty(value = "Whether this segment is private.")
  public Boolean isPrivate() {
    return _private;
  }

  public void setPrivate(Boolean _private) {
    this._private = _private;
  }

  public DetailedSegment athletePrEffort(SummarySegmentEffort athletePrEffort) {
    this.athletePrEffort = athletePrEffort;
    return this;
  }

   /**
   * Get athletePrEffort
   * @return athletePrEffort
  **/
  @ApiModelProperty(value = "")
  public SummarySegmentEffort getAthletePrEffort() {
    return athletePrEffort;
  }

  public void setAthletePrEffort(SummarySegmentEffort athletePrEffort) {
    this.athletePrEffort = athletePrEffort;
  }

  public DetailedSegment createdAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
    return this;
  }

   /**
   * The time at which the segment was created.
   * @return createdAt
  **/
  @ApiModelProperty(value = "The time at which the segment was created.")
  public OffsetDateTime getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(OffsetDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public DetailedSegment updatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
    return this;
  }

   /**
   * The time at which the segment was last updated.
   * @return updatedAt
  **/
  @ApiModelProperty(value = "The time at which the segment was last updated.")
  public OffsetDateTime getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(OffsetDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public DetailedSegment totalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
    return this;
  }

   /**
   * The segment&#39;s total elevation gain.
   * @return totalElevationGain
  **/
  @ApiModelProperty(value = "The segment's total elevation gain.")
  public Float getTotalElevationGain() {
    return totalElevationGain;
  }

  public void setTotalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
  }

  public DetailedSegment map(PolylineMap map) {
    this.map = map;
    return this;
  }

   /**
   * Get map
   * @return map
  **/
  @ApiModelProperty(value = "")
  public PolylineMap getMap() {
    return map;
  }

  public void setMap(PolylineMap map) {
    this.map = map;
  }

  public DetailedSegment effortCount(Integer effortCount) {
    this.effortCount = effortCount;
    return this;
  }

   /**
   * The total number of efforts for this segment
   * @return effortCount
  **/
  @ApiModelProperty(value = "The total number of efforts for this segment")
  public Integer getEffortCount() {
    return effortCount;
  }

  public void setEffortCount(Integer effortCount) {
    this.effortCount = effortCount;
  }

  public DetailedSegment athleteCount(Integer athleteCount) {
    this.athleteCount = athleteCount;
    return this;
  }

   /**
   * The number of unique athletes who have an effort for this segment
   * @return athleteCount
  **/
  @ApiModelProperty(value = "The number of unique athletes who have an effort for this segment")
  public Integer getAthleteCount() {
    return athleteCount;
  }

  public void setAthleteCount(Integer athleteCount) {
    this.athleteCount = athleteCount;
  }

  public DetailedSegment hazardous(Boolean hazardous) {
    this.hazardous = hazardous;
    return this;
  }

   /**
   * Whether this segment is considered hazardous
   * @return hazardous
  **/
  @ApiModelProperty(value = "Whether this segment is considered hazardous")
  public Boolean isHazardous() {
    return hazardous;
  }

  public void setHazardous(Boolean hazardous) {
    this.hazardous = hazardous;
  }

  public DetailedSegment starCount(Integer starCount) {
    this.starCount = starCount;
    return this;
  }

   /**
   * The number of stars for this segment
   * @return starCount
  **/
  @ApiModelProperty(value = "The number of stars for this segment")
  public Integer getStarCount() {
    return starCount;
  }

  public void setStarCount(Integer starCount) {
    this.starCount = starCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailedSegment detailedSegment = (DetailedSegment) o;
    return Objects.equals(this.id, detailedSegment.id) &&
        Objects.equals(this.name, detailedSegment.name) &&
        Objects.equals(this.activityType, detailedSegment.activityType) &&
        Objects.equals(this.distance, detailedSegment.distance) &&
        Objects.equals(this.averageGrade, detailedSegment.averageGrade) &&
        Objects.equals(this.maximumGrade, detailedSegment.maximumGrade) &&
        Objects.equals(this.elevationHigh, detailedSegment.elevationHigh) &&
        Objects.equals(this.elevationLow, detailedSegment.elevationLow) &&
        Objects.equals(this.startLatlng, detailedSegment.startLatlng) &&
        Objects.equals(this.endLatlng, detailedSegment.endLatlng) &&
        Objects.equals(this.climbCategory, detailedSegment.climbCategory) &&
        Objects.equals(this.city, detailedSegment.city) &&
        Objects.equals(this.state, detailedSegment.state) &&
        Objects.equals(this.country, detailedSegment.country) &&
        Objects.equals(this._private, detailedSegment._private) &&
        Objects.equals(this.athletePrEffort, detailedSegment.athletePrEffort) &&
        Objects.equals(this.createdAt, detailedSegment.createdAt) &&
        Objects.equals(this.updatedAt, detailedSegment.updatedAt) &&
        Objects.equals(this.totalElevationGain, detailedSegment.totalElevationGain) &&
        Objects.equals(this.map, detailedSegment.map) &&
        Objects.equals(this.effortCount, detailedSegment.effortCount) &&
        Objects.equals(this.athleteCount, detailedSegment.athleteCount) &&
        Objects.equals(this.hazardous, detailedSegment.hazardous) &&
        Objects.equals(this.starCount, detailedSegment.starCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, activityType, distance, averageGrade, maximumGrade, elevationHigh, elevationLow, startLatlng, endLatlng, climbCategory, city, state, country, _private, athletePrEffort, createdAt, updatedAt, totalElevationGain, map, effortCount, athleteCount, hazardous, starCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailedSegment {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    activityType: ").append(toIndentedString(activityType)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    averageGrade: ").append(toIndentedString(averageGrade)).append("\n");
    sb.append("    maximumGrade: ").append(toIndentedString(maximumGrade)).append("\n");
    sb.append("    elevationHigh: ").append(toIndentedString(elevationHigh)).append("\n");
    sb.append("    elevationLow: ").append(toIndentedString(elevationLow)).append("\n");
    sb.append("    startLatlng: ").append(toIndentedString(startLatlng)).append("\n");
    sb.append("    endLatlng: ").append(toIndentedString(endLatlng)).append("\n");
    sb.append("    climbCategory: ").append(toIndentedString(climbCategory)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    country: ").append(toIndentedString(country)).append("\n");
    sb.append("    _private: ").append(toIndentedString(_private)).append("\n");
    sb.append("    athletePrEffort: ").append(toIndentedString(athletePrEffort)).append("\n");
    sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
    sb.append("    updatedAt: ").append(toIndentedString(updatedAt)).append("\n");
    sb.append("    totalElevationGain: ").append(toIndentedString(totalElevationGain)).append("\n");
    sb.append("    map: ").append(toIndentedString(map)).append("\n");
    sb.append("    effortCount: ").append(toIndentedString(effortCount)).append("\n");
    sb.append("    athleteCount: ").append(toIndentedString(athleteCount)).append("\n");
    sb.append("    hazardous: ").append(toIndentedString(hazardous)).append("\n");
    sb.append("    starCount: ").append(toIndentedString(starCount)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

