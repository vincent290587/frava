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
import io.swagger.client.model.MetaActivity;
import io.swagger.client.model.MetaAthlete;
import io.swagger.client.model.SummarySegment;
import io.swagger.client.model.SummarySegmentEffort;
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * DetailedSegmentEffort
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-25T14:36:57.813+02:00")
public class DetailedSegmentEffort {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("elapsed_time")
  private Integer elapsedTime = null;

  @SerializedName("start_date")
  private OffsetDateTime startDate = null;

  @SerializedName("start_date_local")
  private OffsetDateTime startDateLocal = null;

  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("is_kom")
  private Boolean isKom = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("activity")
  private MetaActivity activity = null;

  @SerializedName("athlete")
  private MetaAthlete athlete = null;

  @SerializedName("moving_time")
  private Integer movingTime = null;

  @SerializedName("start_index")
  private Integer startIndex = null;

  @SerializedName("end_index")
  private Integer endIndex = null;

  @SerializedName("average_cadence")
  private Float averageCadence = null;

  @SerializedName("average_watts")
  private Float averageWatts = null;

  @SerializedName("device_watts")
  private Boolean deviceWatts = null;

  @SerializedName("average_heartrate")
  private Float averageHeartrate = null;

  @SerializedName("max_heartrate")
  private Float maxHeartrate = null;

  @SerializedName("segment")
  private SummarySegment segment = null;

  @SerializedName("kom_rank")
  private Integer komRank = null;

  @SerializedName("pr_rank")
  private Integer prRank = null;

  @SerializedName("hidden")
  private Boolean hidden = null;

  public DetailedSegmentEffort id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * The unique identifier of this effort
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier of this effort")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public DetailedSegmentEffort elapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
    return this;
  }

   /**
   * The effort&#39;s elapsed time
   * @return elapsedTime
  **/
  @ApiModelProperty(value = "The effort's elapsed time")
  public Integer getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public DetailedSegmentEffort startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * The time at which the effort was started.
   * @return startDate
  **/
  @ApiModelProperty(value = "The time at which the effort was started.")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public DetailedSegmentEffort startDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
    return this;
  }

   /**
   * The time at which the effort was started in the local timezone.
   * @return startDateLocal
  **/
  @ApiModelProperty(value = "The time at which the effort was started in the local timezone.")
  public OffsetDateTime getStartDateLocal() {
    return startDateLocal;
  }

  public void setStartDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
  }

  public DetailedSegmentEffort distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The effort&#39;s distance in meters
   * @return distance
  **/
  @ApiModelProperty(value = "The effort's distance in meters")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public DetailedSegmentEffort isKom(Boolean isKom) {
    this.isKom = isKom;
    return this;
  }

   /**
   * Whether this effort is the current best on the leaderboard
   * @return isKom
  **/
  @ApiModelProperty(value = "Whether this effort is the current best on the leaderboard")
  public Boolean isIsKom() {
    return isKom;
  }

  public void setIsKom(Boolean isKom) {
    this.isKom = isKom;
  }

  public DetailedSegmentEffort name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the segment on which this effort was performed
   * @return name
  **/
  @ApiModelProperty(value = "The name of the segment on which this effort was performed")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DetailedSegmentEffort activity(MetaActivity activity) {
    this.activity = activity;
    return this;
  }

   /**
   * Get activity
   * @return activity
  **/
  @ApiModelProperty(value = "")
  public MetaActivity getActivity() {
    return activity;
  }

  public void setActivity(MetaActivity activity) {
    this.activity = activity;
  }

  public DetailedSegmentEffort athlete(MetaAthlete athlete) {
    this.athlete = athlete;
    return this;
  }

   /**
   * Get athlete
   * @return athlete
  **/
  @ApiModelProperty(value = "")
  public MetaAthlete getAthlete() {
    return athlete;
  }

  public void setAthlete(MetaAthlete athlete) {
    this.athlete = athlete;
  }

  public DetailedSegmentEffort movingTime(Integer movingTime) {
    this.movingTime = movingTime;
    return this;
  }

   /**
   * The effort&#39;s moving time
   * @return movingTime
  **/
  @ApiModelProperty(value = "The effort's moving time")
  public Integer getMovingTime() {
    return movingTime;
  }

  public void setMovingTime(Integer movingTime) {
    this.movingTime = movingTime;
  }

  public DetailedSegmentEffort startIndex(Integer startIndex) {
    this.startIndex = startIndex;
    return this;
  }

   /**
   * The start index of this effort in its activity&#39;s stream
   * @return startIndex
  **/
  @ApiModelProperty(value = "The start index of this effort in its activity's stream")
  public Integer getStartIndex() {
    return startIndex;
  }

  public void setStartIndex(Integer startIndex) {
    this.startIndex = startIndex;
  }

  public DetailedSegmentEffort endIndex(Integer endIndex) {
    this.endIndex = endIndex;
    return this;
  }

   /**
   * The end index of this effort in its activity&#39;s stream
   * @return endIndex
  **/
  @ApiModelProperty(value = "The end index of this effort in its activity's stream")
  public Integer getEndIndex() {
    return endIndex;
  }

  public void setEndIndex(Integer endIndex) {
    this.endIndex = endIndex;
  }

  public DetailedSegmentEffort averageCadence(Float averageCadence) {
    this.averageCadence = averageCadence;
    return this;
  }

   /**
   * The effort&#39;s average cadence
   * @return averageCadence
  **/
  @ApiModelProperty(value = "The effort's average cadence")
  public Float getAverageCadence() {
    return averageCadence;
  }

  public void setAverageCadence(Float averageCadence) {
    this.averageCadence = averageCadence;
  }

  public DetailedSegmentEffort averageWatts(Float averageWatts) {
    this.averageWatts = averageWatts;
    return this;
  }

   /**
   * The average wattage of this effort
   * @return averageWatts
  **/
  @ApiModelProperty(value = "The average wattage of this effort")
  public Float getAverageWatts() {
    return averageWatts;
  }

  public void setAverageWatts(Float averageWatts) {
    this.averageWatts = averageWatts;
  }

  public DetailedSegmentEffort deviceWatts(Boolean deviceWatts) {
    this.deviceWatts = deviceWatts;
    return this;
  }

   /**
   * For riding efforts, whether the wattage was reported by a dedicated recording device
   * @return deviceWatts
  **/
  @ApiModelProperty(value = "For riding efforts, whether the wattage was reported by a dedicated recording device")
  public Boolean isDeviceWatts() {
    return deviceWatts;
  }

  public void setDeviceWatts(Boolean deviceWatts) {
    this.deviceWatts = deviceWatts;
  }

  public DetailedSegmentEffort averageHeartrate(Float averageHeartrate) {
    this.averageHeartrate = averageHeartrate;
    return this;
  }

   /**
   * The heart heart rate of the athlete during this effort
   * @return averageHeartrate
  **/
  @ApiModelProperty(value = "The heart heart rate of the athlete during this effort")
  public Float getAverageHeartrate() {
    return averageHeartrate;
  }

  public void setAverageHeartrate(Float averageHeartrate) {
    this.averageHeartrate = averageHeartrate;
  }

  public DetailedSegmentEffort maxHeartrate(Float maxHeartrate) {
    this.maxHeartrate = maxHeartrate;
    return this;
  }

   /**
   * The maximum heart rate of the athlete during this effort
   * @return maxHeartrate
  **/
  @ApiModelProperty(value = "The maximum heart rate of the athlete during this effort")
  public Float getMaxHeartrate() {
    return maxHeartrate;
  }

  public void setMaxHeartrate(Float maxHeartrate) {
    this.maxHeartrate = maxHeartrate;
  }

  public DetailedSegmentEffort segment(SummarySegment segment) {
    this.segment = segment;
    return this;
  }

   /**
   * Get segment
   * @return segment
  **/
  @ApiModelProperty(value = "")
  public SummarySegment getSegment() {
    return segment;
  }

  public void setSegment(SummarySegment segment) {
    this.segment = segment;
  }

  public DetailedSegmentEffort komRank(Integer komRank) {
    this.komRank = komRank;
    return this;
  }

   /**
   * The rank of the effort on the global leaderboard if it belongs in the top 10 at the time of upload
   * minimum: 1
   * maximum: 10
   * @return komRank
  **/
  @ApiModelProperty(value = "The rank of the effort on the global leaderboard if it belongs in the top 10 at the time of upload")
  public Integer getKomRank() {
    return komRank;
  }

  public void setKomRank(Integer komRank) {
    this.komRank = komRank;
  }

  public DetailedSegmentEffort prRank(Integer prRank) {
    this.prRank = prRank;
    return this;
  }

   /**
   * The rank of the effort on the athlete&#39;s leaderboard if it belongs in the top 3 at the time of upload
   * minimum: 1
   * maximum: 3
   * @return prRank
  **/
  @ApiModelProperty(value = "The rank of the effort on the athlete's leaderboard if it belongs in the top 3 at the time of upload")
  public Integer getPrRank() {
    return prRank;
  }

  public void setPrRank(Integer prRank) {
    this.prRank = prRank;
  }

  public DetailedSegmentEffort hidden(Boolean hidden) {
    this.hidden = hidden;
    return this;
  }

   /**
   * Whether this effort should be hidden when viewed within an activity
   * @return hidden
  **/
  @ApiModelProperty(value = "Whether this effort should be hidden when viewed within an activity")
  public Boolean isHidden() {
    return hidden;
  }

  public void setHidden(Boolean hidden) {
    this.hidden = hidden;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DetailedSegmentEffort detailedSegmentEffort = (DetailedSegmentEffort) o;
    return Objects.equals(this.id, detailedSegmentEffort.id) &&
        Objects.equals(this.elapsedTime, detailedSegmentEffort.elapsedTime) &&
        Objects.equals(this.startDate, detailedSegmentEffort.startDate) &&
        Objects.equals(this.startDateLocal, detailedSegmentEffort.startDateLocal) &&
        Objects.equals(this.distance, detailedSegmentEffort.distance) &&
        Objects.equals(this.isKom, detailedSegmentEffort.isKom) &&
        Objects.equals(this.name, detailedSegmentEffort.name) &&
        Objects.equals(this.activity, detailedSegmentEffort.activity) &&
        Objects.equals(this.athlete, detailedSegmentEffort.athlete) &&
        Objects.equals(this.movingTime, detailedSegmentEffort.movingTime) &&
        Objects.equals(this.startIndex, detailedSegmentEffort.startIndex) &&
        Objects.equals(this.endIndex, detailedSegmentEffort.endIndex) &&
        Objects.equals(this.averageCadence, detailedSegmentEffort.averageCadence) &&
        Objects.equals(this.averageWatts, detailedSegmentEffort.averageWatts) &&
        Objects.equals(this.deviceWatts, detailedSegmentEffort.deviceWatts) &&
        Objects.equals(this.averageHeartrate, detailedSegmentEffort.averageHeartrate) &&
        Objects.equals(this.maxHeartrate, detailedSegmentEffort.maxHeartrate) &&
        Objects.equals(this.segment, detailedSegmentEffort.segment) &&
        Objects.equals(this.komRank, detailedSegmentEffort.komRank) &&
        Objects.equals(this.prRank, detailedSegmentEffort.prRank) &&
        Objects.equals(this.hidden, detailedSegmentEffort.hidden);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, elapsedTime, startDate, startDateLocal, distance, isKom, name, activity, athlete, movingTime, startIndex, endIndex, averageCadence, averageWatts, deviceWatts, averageHeartrate, maxHeartrate, segment, komRank, prRank, hidden);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DetailedSegmentEffort {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    elapsedTime: ").append(toIndentedString(elapsedTime)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    startDateLocal: ").append(toIndentedString(startDateLocal)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    isKom: ").append(toIndentedString(isKom)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
    sb.append("    athlete: ").append(toIndentedString(athlete)).append("\n");
    sb.append("    movingTime: ").append(toIndentedString(movingTime)).append("\n");
    sb.append("    startIndex: ").append(toIndentedString(startIndex)).append("\n");
    sb.append("    endIndex: ").append(toIndentedString(endIndex)).append("\n");
    sb.append("    averageCadence: ").append(toIndentedString(averageCadence)).append("\n");
    sb.append("    averageWatts: ").append(toIndentedString(averageWatts)).append("\n");
    sb.append("    deviceWatts: ").append(toIndentedString(deviceWatts)).append("\n");
    sb.append("    averageHeartrate: ").append(toIndentedString(averageHeartrate)).append("\n");
    sb.append("    maxHeartrate: ").append(toIndentedString(maxHeartrate)).append("\n");
    sb.append("    segment: ").append(toIndentedString(segment)).append("\n");
    sb.append("    komRank: ").append(toIndentedString(komRank)).append("\n");
    sb.append("    prRank: ").append(toIndentedString(prRank)).append("\n");
    sb.append("    hidden: ").append(toIndentedString(hidden)).append("\n");
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

