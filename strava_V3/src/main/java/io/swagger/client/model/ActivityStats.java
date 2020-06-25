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
import io.swagger.client.model.ActivityTotal;
import java.io.IOException;

/**
 * A set of rolled-up statistics and totals for an athlete
 */
@ApiModel(description = "A set of rolled-up statistics and totals for an athlete")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-06-25T14:36:57.813+02:00")
public class ActivityStats {
  @SerializedName("biggest_ride_distance")
  private Double biggestRideDistance = null;

  @SerializedName("biggest_climb_elevation_gain")
  private Double biggestClimbElevationGain = null;

  @SerializedName("recent_ride_totals")
  private ActivityTotal recentRideTotals = null;

  @SerializedName("recent_run_totals")
  private ActivityTotal recentRunTotals = null;

  @SerializedName("recent_swim_totals")
  private ActivityTotal recentSwimTotals = null;

  @SerializedName("ytd_ride_totals")
  private ActivityTotal ytdRideTotals = null;

  @SerializedName("ytd_run_totals")
  private ActivityTotal ytdRunTotals = null;

  @SerializedName("ytd_swim_totals")
  private ActivityTotal ytdSwimTotals = null;

  @SerializedName("all_ride_totals")
  private ActivityTotal allRideTotals = null;

  @SerializedName("all_run_totals")
  private ActivityTotal allRunTotals = null;

  @SerializedName("all_swim_totals")
  private ActivityTotal allSwimTotals = null;

  public ActivityStats biggestRideDistance(Double biggestRideDistance) {
    this.biggestRideDistance = biggestRideDistance;
    return this;
  }

   /**
   * The longest distance ridden by the athlete.
   * @return biggestRideDistance
  **/
  @ApiModelProperty(value = "The longest distance ridden by the athlete.")
  public Double getBiggestRideDistance() {
    return biggestRideDistance;
  }

  public void setBiggestRideDistance(Double biggestRideDistance) {
    this.biggestRideDistance = biggestRideDistance;
  }

  public ActivityStats biggestClimbElevationGain(Double biggestClimbElevationGain) {
    this.biggestClimbElevationGain = biggestClimbElevationGain;
    return this;
  }

   /**
   * The highest climb ridden by the athlete.
   * @return biggestClimbElevationGain
  **/
  @ApiModelProperty(value = "The highest climb ridden by the athlete.")
  public Double getBiggestClimbElevationGain() {
    return biggestClimbElevationGain;
  }

  public void setBiggestClimbElevationGain(Double biggestClimbElevationGain) {
    this.biggestClimbElevationGain = biggestClimbElevationGain;
  }

  public ActivityStats recentRideTotals(ActivityTotal recentRideTotals) {
    this.recentRideTotals = recentRideTotals;
    return this;
  }

   /**
   * The recent (last 4 weeks) ride stats for the athlete.
   * @return recentRideTotals
  **/
  @ApiModelProperty(value = "The recent (last 4 weeks) ride stats for the athlete.")
  public ActivityTotal getRecentRideTotals() {
    return recentRideTotals;
  }

  public void setRecentRideTotals(ActivityTotal recentRideTotals) {
    this.recentRideTotals = recentRideTotals;
  }

  public ActivityStats recentRunTotals(ActivityTotal recentRunTotals) {
    this.recentRunTotals = recentRunTotals;
    return this;
  }

   /**
   * The recent (last 4 weeks) run stats for the athlete.
   * @return recentRunTotals
  **/
  @ApiModelProperty(value = "The recent (last 4 weeks) run stats for the athlete.")
  public ActivityTotal getRecentRunTotals() {
    return recentRunTotals;
  }

  public void setRecentRunTotals(ActivityTotal recentRunTotals) {
    this.recentRunTotals = recentRunTotals;
  }

  public ActivityStats recentSwimTotals(ActivityTotal recentSwimTotals) {
    this.recentSwimTotals = recentSwimTotals;
    return this;
  }

   /**
   * The recent (last 4 weeks) swim stats for the athlete.
   * @return recentSwimTotals
  **/
  @ApiModelProperty(value = "The recent (last 4 weeks) swim stats for the athlete.")
  public ActivityTotal getRecentSwimTotals() {
    return recentSwimTotals;
  }

  public void setRecentSwimTotals(ActivityTotal recentSwimTotals) {
    this.recentSwimTotals = recentSwimTotals;
  }

  public ActivityStats ytdRideTotals(ActivityTotal ytdRideTotals) {
    this.ytdRideTotals = ytdRideTotals;
    return this;
  }

   /**
   * The year to date ride stats for the athlete.
   * @return ytdRideTotals
  **/
  @ApiModelProperty(value = "The year to date ride stats for the athlete.")
  public ActivityTotal getYtdRideTotals() {
    return ytdRideTotals;
  }

  public void setYtdRideTotals(ActivityTotal ytdRideTotals) {
    this.ytdRideTotals = ytdRideTotals;
  }

  public ActivityStats ytdRunTotals(ActivityTotal ytdRunTotals) {
    this.ytdRunTotals = ytdRunTotals;
    return this;
  }

   /**
   * The year to date run stats for the athlete.
   * @return ytdRunTotals
  **/
  @ApiModelProperty(value = "The year to date run stats for the athlete.")
  public ActivityTotal getYtdRunTotals() {
    return ytdRunTotals;
  }

  public void setYtdRunTotals(ActivityTotal ytdRunTotals) {
    this.ytdRunTotals = ytdRunTotals;
  }

  public ActivityStats ytdSwimTotals(ActivityTotal ytdSwimTotals) {
    this.ytdSwimTotals = ytdSwimTotals;
    return this;
  }

   /**
   * The year to date swim stats for the athlete.
   * @return ytdSwimTotals
  **/
  @ApiModelProperty(value = "The year to date swim stats for the athlete.")
  public ActivityTotal getYtdSwimTotals() {
    return ytdSwimTotals;
  }

  public void setYtdSwimTotals(ActivityTotal ytdSwimTotals) {
    this.ytdSwimTotals = ytdSwimTotals;
  }

  public ActivityStats allRideTotals(ActivityTotal allRideTotals) {
    this.allRideTotals = allRideTotals;
    return this;
  }

   /**
   * The all time ride stats for the athlete.
   * @return allRideTotals
  **/
  @ApiModelProperty(value = "The all time ride stats for the athlete.")
  public ActivityTotal getAllRideTotals() {
    return allRideTotals;
  }

  public void setAllRideTotals(ActivityTotal allRideTotals) {
    this.allRideTotals = allRideTotals;
  }

  public ActivityStats allRunTotals(ActivityTotal allRunTotals) {
    this.allRunTotals = allRunTotals;
    return this;
  }

   /**
   * The all time run stats for the athlete.
   * @return allRunTotals
  **/
  @ApiModelProperty(value = "The all time run stats for the athlete.")
  public ActivityTotal getAllRunTotals() {
    return allRunTotals;
  }

  public void setAllRunTotals(ActivityTotal allRunTotals) {
    this.allRunTotals = allRunTotals;
  }

  public ActivityStats allSwimTotals(ActivityTotal allSwimTotals) {
    this.allSwimTotals = allSwimTotals;
    return this;
  }

   /**
   * The all time swim stats for the athlete.
   * @return allSwimTotals
  **/
  @ApiModelProperty(value = "The all time swim stats for the athlete.")
  public ActivityTotal getAllSwimTotals() {
    return allSwimTotals;
  }

  public void setAllSwimTotals(ActivityTotal allSwimTotals) {
    this.allSwimTotals = allSwimTotals;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ActivityStats activityStats = (ActivityStats) o;
    return Objects.equals(this.biggestRideDistance, activityStats.biggestRideDistance) &&
        Objects.equals(this.biggestClimbElevationGain, activityStats.biggestClimbElevationGain) &&
        Objects.equals(this.recentRideTotals, activityStats.recentRideTotals) &&
        Objects.equals(this.recentRunTotals, activityStats.recentRunTotals) &&
        Objects.equals(this.recentSwimTotals, activityStats.recentSwimTotals) &&
        Objects.equals(this.ytdRideTotals, activityStats.ytdRideTotals) &&
        Objects.equals(this.ytdRunTotals, activityStats.ytdRunTotals) &&
        Objects.equals(this.ytdSwimTotals, activityStats.ytdSwimTotals) &&
        Objects.equals(this.allRideTotals, activityStats.allRideTotals) &&
        Objects.equals(this.allRunTotals, activityStats.allRunTotals) &&
        Objects.equals(this.allSwimTotals, activityStats.allSwimTotals);
  }

  @Override
  public int hashCode() {
    return Objects.hash(biggestRideDistance, biggestClimbElevationGain, recentRideTotals, recentRunTotals, recentSwimTotals, ytdRideTotals, ytdRunTotals, ytdSwimTotals, allRideTotals, allRunTotals, allSwimTotals);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ActivityStats {\n");
    
    sb.append("    biggestRideDistance: ").append(toIndentedString(biggestRideDistance)).append("\n");
    sb.append("    biggestClimbElevationGain: ").append(toIndentedString(biggestClimbElevationGain)).append("\n");
    sb.append("    recentRideTotals: ").append(toIndentedString(recentRideTotals)).append("\n");
    sb.append("    recentRunTotals: ").append(toIndentedString(recentRunTotals)).append("\n");
    sb.append("    recentSwimTotals: ").append(toIndentedString(recentSwimTotals)).append("\n");
    sb.append("    ytdRideTotals: ").append(toIndentedString(ytdRideTotals)).append("\n");
    sb.append("    ytdRunTotals: ").append(toIndentedString(ytdRunTotals)).append("\n");
    sb.append("    ytdSwimTotals: ").append(toIndentedString(ytdSwimTotals)).append("\n");
    sb.append("    allRideTotals: ").append(toIndentedString(allRideTotals)).append("\n");
    sb.append("    allRunTotals: ").append(toIndentedString(allRunTotals)).append("\n");
    sb.append("    allSwimTotals: ").append(toIndentedString(allSwimTotals)).append("\n");
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

