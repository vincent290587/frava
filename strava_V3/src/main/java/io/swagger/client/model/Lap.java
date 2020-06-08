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
import java.io.IOException;
import org.threeten.bp.OffsetDateTime;

/**
 * Lap
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-31T19:43:52.141+02:00")
public class Lap {
  @SerializedName("id")
  private Long id = null;

  @SerializedName("activity")
  private MetaActivity activity = null;

  @SerializedName("athlete")
  private MetaAthlete athlete = null;

  @SerializedName("average_cadence")
  private Float averageCadence = null;

  @SerializedName("average_speed")
  private Float averageSpeed = null;

  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("elapsed_time")
  private Integer elapsedTime = null;

  @SerializedName("start_index")
  private Integer startIndex = null;

  @SerializedName("end_index")
  private Integer endIndex = null;

  @SerializedName("lap_index")
  private Integer lapIndex = null;

  @SerializedName("max_speed")
  private Float maxSpeed = null;

  @SerializedName("moving_time")
  private Integer movingTime = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("pace_zone")
  private Integer paceZone = null;

  @SerializedName("split")
  private Integer split = null;

  @SerializedName("start_date")
  private OffsetDateTime startDate = null;

  @SerializedName("start_date_local")
  private OffsetDateTime startDateLocal = null;

  @SerializedName("total_elevation_gain")
  private Float totalElevationGain = null;

  public Lap id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * The unique identifier of this lap
   * @return id
  **/
  @ApiModelProperty(value = "The unique identifier of this lap")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Lap activity(MetaActivity activity) {
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

  public Lap athlete(MetaAthlete athlete) {
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

  public Lap averageCadence(Float averageCadence) {
    this.averageCadence = averageCadence;
    return this;
  }

   /**
   * The lap&#39;s average cadence
   * @return averageCadence
  **/
  @ApiModelProperty(value = "The lap's average cadence")
  public Float getAverageCadence() {
    return averageCadence;
  }

  public void setAverageCadence(Float averageCadence) {
    this.averageCadence = averageCadence;
  }

  public Lap averageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
    return this;
  }

   /**
   * The lap&#39;s average speed
   * @return averageSpeed
  **/
  @ApiModelProperty(value = "The lap's average speed")
  public Float getAverageSpeed() {
    return averageSpeed;
  }

  public void setAverageSpeed(Float averageSpeed) {
    this.averageSpeed = averageSpeed;
  }

  public Lap distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The lap&#39;s distance, in meters
   * @return distance
  **/
  @ApiModelProperty(value = "The lap's distance, in meters")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public Lap elapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
    return this;
  }

   /**
   * The lap&#39;s elapsed time, in seconds
   * @return elapsedTime
  **/
  @ApiModelProperty(value = "The lap's elapsed time, in seconds")
  public Integer getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public Lap startIndex(Integer startIndex) {
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

  public Lap endIndex(Integer endIndex) {
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

  public Lap lapIndex(Integer lapIndex) {
    this.lapIndex = lapIndex;
    return this;
  }

   /**
   * The index of this lap in the activity it belongs to
   * @return lapIndex
  **/
  @ApiModelProperty(value = "The index of this lap in the activity it belongs to")
  public Integer getLapIndex() {
    return lapIndex;
  }

  public void setLapIndex(Integer lapIndex) {
    this.lapIndex = lapIndex;
  }

  public Lap maxSpeed(Float maxSpeed) {
    this.maxSpeed = maxSpeed;
    return this;
  }

   /**
   * The maximum speed of this lat, in meters per second
   * @return maxSpeed
  **/
  @ApiModelProperty(value = "The maximum speed of this lat, in meters per second")
  public Float getMaxSpeed() {
    return maxSpeed;
  }

  public void setMaxSpeed(Float maxSpeed) {
    this.maxSpeed = maxSpeed;
  }

  public Lap movingTime(Integer movingTime) {
    this.movingTime = movingTime;
    return this;
  }

   /**
   * The lap&#39;s moving time, in seconds
   * @return movingTime
  **/
  @ApiModelProperty(value = "The lap's moving time, in seconds")
  public Integer getMovingTime() {
    return movingTime;
  }

  public void setMovingTime(Integer movingTime) {
    this.movingTime = movingTime;
  }

  public Lap name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The name of the lap
   * @return name
  **/
  @ApiModelProperty(value = "The name of the lap")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Lap paceZone(Integer paceZone) {
    this.paceZone = paceZone;
    return this;
  }

   /**
   * The athlete&#39;s pace zone during this lap
   * @return paceZone
  **/
  @ApiModelProperty(value = "The athlete's pace zone during this lap")
  public Integer getPaceZone() {
    return paceZone;
  }

  public void setPaceZone(Integer paceZone) {
    this.paceZone = paceZone;
  }

  public Lap split(Integer split) {
    this.split = split;
    return this;
  }

   /**
   * Get split
   * @return split
  **/
  @ApiModelProperty(value = "")
  public Integer getSplit() {
    return split;
  }

  public void setSplit(Integer split) {
    this.split = split;
  }

  public Lap startDate(OffsetDateTime startDate) {
    this.startDate = startDate;
    return this;
  }

   /**
   * The time at which the lap was started.
   * @return startDate
  **/
  @ApiModelProperty(value = "The time at which the lap was started.")
  public OffsetDateTime getStartDate() {
    return startDate;
  }

  public void setStartDate(OffsetDateTime startDate) {
    this.startDate = startDate;
  }

  public Lap startDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
    return this;
  }

   /**
   * The time at which the lap was started in the local timezone.
   * @return startDateLocal
  **/
  @ApiModelProperty(value = "The time at which the lap was started in the local timezone.")
  public OffsetDateTime getStartDateLocal() {
    return startDateLocal;
  }

  public void setStartDateLocal(OffsetDateTime startDateLocal) {
    this.startDateLocal = startDateLocal;
  }

  public Lap totalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
    return this;
  }

   /**
   * The elevation gain of this lap, in meters
   * @return totalElevationGain
  **/
  @ApiModelProperty(value = "The elevation gain of this lap, in meters")
  public Float getTotalElevationGain() {
    return totalElevationGain;
  }

  public void setTotalElevationGain(Float totalElevationGain) {
    this.totalElevationGain = totalElevationGain;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Lap lap = (Lap) o;
    return Objects.equals(this.id, lap.id) &&
        Objects.equals(this.activity, lap.activity) &&
        Objects.equals(this.athlete, lap.athlete) &&
        Objects.equals(this.averageCadence, lap.averageCadence) &&
        Objects.equals(this.averageSpeed, lap.averageSpeed) &&
        Objects.equals(this.distance, lap.distance) &&
        Objects.equals(this.elapsedTime, lap.elapsedTime) &&
        Objects.equals(this.startIndex, lap.startIndex) &&
        Objects.equals(this.endIndex, lap.endIndex) &&
        Objects.equals(this.lapIndex, lap.lapIndex) &&
        Objects.equals(this.maxSpeed, lap.maxSpeed) &&
        Objects.equals(this.movingTime, lap.movingTime) &&
        Objects.equals(this.name, lap.name) &&
        Objects.equals(this.paceZone, lap.paceZone) &&
        Objects.equals(this.split, lap.split) &&
        Objects.equals(this.startDate, lap.startDate) &&
        Objects.equals(this.startDateLocal, lap.startDateLocal) &&
        Objects.equals(this.totalElevationGain, lap.totalElevationGain);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, activity, athlete, averageCadence, averageSpeed, distance, elapsedTime, startIndex, endIndex, lapIndex, maxSpeed, movingTime, name, paceZone, split, startDate, startDateLocal, totalElevationGain);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Lap {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    activity: ").append(toIndentedString(activity)).append("\n");
    sb.append("    athlete: ").append(toIndentedString(athlete)).append("\n");
    sb.append("    averageCadence: ").append(toIndentedString(averageCadence)).append("\n");
    sb.append("    averageSpeed: ").append(toIndentedString(averageSpeed)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    elapsedTime: ").append(toIndentedString(elapsedTime)).append("\n");
    sb.append("    startIndex: ").append(toIndentedString(startIndex)).append("\n");
    sb.append("    endIndex: ").append(toIndentedString(endIndex)).append("\n");
    sb.append("    lapIndex: ").append(toIndentedString(lapIndex)).append("\n");
    sb.append("    maxSpeed: ").append(toIndentedString(maxSpeed)).append("\n");
    sb.append("    movingTime: ").append(toIndentedString(movingTime)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    paceZone: ").append(toIndentedString(paceZone)).append("\n");
    sb.append("    split: ").append(toIndentedString(split)).append("\n");
    sb.append("    startDate: ").append(toIndentedString(startDate)).append("\n");
    sb.append("    startDateLocal: ").append(toIndentedString(startDateLocal)).append("\n");
    sb.append("    totalElevationGain: ").append(toIndentedString(totalElevationGain)).append("\n");
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

