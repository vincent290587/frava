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
import java.io.IOException;

/**
 * A roll-up of metrics pertaining to a set of activities. Values are in seconds and meters.
 */
@ApiModel(description = "A roll-up of metrics pertaining to a set of activities. Values are in seconds and meters.")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-31T19:43:52.141+02:00")
public class ActivityTotal {
  @SerializedName("count")
  private Integer count = null;

  @SerializedName("distance")
  private Float distance = null;

  @SerializedName("moving_time")
  private Integer movingTime = null;

  @SerializedName("elapsed_time")
  private Integer elapsedTime = null;

  @SerializedName("elevation_gain")
  private Float elevationGain = null;

  @SerializedName("achievement_count")
  private Integer achievementCount = null;

  public ActivityTotal count(Integer count) {
    this.count = count;
    return this;
  }

   /**
   * The number of activities considered in this total.
   * @return count
  **/
  @ApiModelProperty(value = "The number of activities considered in this total.")
  public Integer getCount() {
    return count;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public ActivityTotal distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The total distance covered by the considered activities.
   * @return distance
  **/
  @ApiModelProperty(value = "The total distance covered by the considered activities.")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }

  public ActivityTotal movingTime(Integer movingTime) {
    this.movingTime = movingTime;
    return this;
  }

   /**
   * The total moving time of the considered activities.
   * @return movingTime
  **/
  @ApiModelProperty(value = "The total moving time of the considered activities.")
  public Integer getMovingTime() {
    return movingTime;
  }

  public void setMovingTime(Integer movingTime) {
    this.movingTime = movingTime;
  }

  public ActivityTotal elapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
    return this;
  }

   /**
   * The total elapsed time of the considered activities.
   * @return elapsedTime
  **/
  @ApiModelProperty(value = "The total elapsed time of the considered activities.")
  public Integer getElapsedTime() {
    return elapsedTime;
  }

  public void setElapsedTime(Integer elapsedTime) {
    this.elapsedTime = elapsedTime;
  }

  public ActivityTotal elevationGain(Float elevationGain) {
    this.elevationGain = elevationGain;
    return this;
  }

   /**
   * The total elevation gain of the considered activities.
   * @return elevationGain
  **/
  @ApiModelProperty(value = "The total elevation gain of the considered activities.")
  public Float getElevationGain() {
    return elevationGain;
  }

  public void setElevationGain(Float elevationGain) {
    this.elevationGain = elevationGain;
  }

  public ActivityTotal achievementCount(Integer achievementCount) {
    this.achievementCount = achievementCount;
    return this;
  }

   /**
   * The total number of achievements of the considered activities.
   * @return achievementCount
  **/
  @ApiModelProperty(value = "The total number of achievements of the considered activities.")
  public Integer getAchievementCount() {
    return achievementCount;
  }

  public void setAchievementCount(Integer achievementCount) {
    this.achievementCount = achievementCount;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityTotal activityTotal = (ActivityTotal) o;
    return Objects.equals(this.count, activityTotal.count) &&
        Objects.equals(this.distance, activityTotal.distance) &&
        Objects.equals(this.movingTime, activityTotal.movingTime) &&
        Objects.equals(this.elapsedTime, activityTotal.elapsedTime) &&
        Objects.equals(this.elevationGain, activityTotal.elevationGain) &&
        Objects.equals(this.achievementCount, activityTotal.achievementCount);
  }

  @Override
  public int hashCode() {
    return Objects.hash(count, distance, movingTime, elapsedTime, elevationGain, achievementCount);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivityTotal {\n");
    
    sb.append("    count: ").append(toIndentedString(count)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
    sb.append("    movingTime: ").append(toIndentedString(movingTime)).append("\n");
    sb.append("    elapsedTime: ").append(toIndentedString(elapsedTime)).append("\n");
    sb.append("    elevationGain: ").append(toIndentedString(elevationGain)).append("\n");
    sb.append("    achievementCount: ").append(toIndentedString(achievementCount)).append("\n");
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

