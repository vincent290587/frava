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
 * PolylineMap
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-31T19:43:52.141+02:00")
public class PolylineMap {
  @SerializedName("id")
  private String id = null;

  @SerializedName("polyline")
  private String polyline = null;

  @SerializedName("summary_polyline")
  private String summaryPolyline = null;

  public PolylineMap id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The identifier of the map
   * @return id
  **/
  @ApiModelProperty(value = "The identifier of the map")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public PolylineMap polyline(String polyline) {
    this.polyline = polyline;
    return this;
  }

   /**
   * The polyline of the map, only returned on detailed representation of an object
   * @return polyline
  **/
  @ApiModelProperty(value = "The polyline of the map, only returned on detailed representation of an object")
  public String getPolyline() {
    return polyline;
  }

  public void setPolyline(String polyline) {
    this.polyline = polyline;
  }

  public PolylineMap summaryPolyline(String summaryPolyline) {
    this.summaryPolyline = summaryPolyline;
    return this;
  }

   /**
   * The summary polyline of the map
   * @return summaryPolyline
  **/
  @ApiModelProperty(value = "The summary polyline of the map")
  public String getSummaryPolyline() {
    return summaryPolyline;
  }

  public void setSummaryPolyline(String summaryPolyline) {
    this.summaryPolyline = summaryPolyline;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PolylineMap polylineMap = (PolylineMap) o;
    return Objects.equals(this.id, polylineMap.id) &&
        Objects.equals(this.polyline, polylineMap.polyline) &&
        Objects.equals(this.summaryPolyline, polylineMap.summaryPolyline);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, polyline, summaryPolyline);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PolylineMap {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    polyline: ").append(toIndentedString(polyline)).append("\n");
    sb.append("    summaryPolyline: ").append(toIndentedString(summaryPolyline)).append("\n");
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

