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
import io.swagger.client.model.ExplorerSegment;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * ExplorerResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-31T19:43:52.141+02:00")
public class ExplorerResponse {
  @SerializedName("segments")
  private List<ExplorerSegment> segments = null;

  public ExplorerResponse segments(List<ExplorerSegment> segments) {
    this.segments = segments;
    return this;
  }

  public ExplorerResponse addSegmentsItem(ExplorerSegment segmentsItem) {
    if (this.segments == null) {
      this.segments = new ArrayList<ExplorerSegment>();
    }
    this.segments.add(segmentsItem);
    return this;
  }

   /**
   * The set of segments matching an explorer request
   * @return segments
  **/
  @ApiModelProperty(value = "The set of segments matching an explorer request")
  public List<ExplorerSegment> getSegments() {
    return segments;
  }

  public void setSegments(List<ExplorerSegment> segments) {
    this.segments = segments;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ExplorerResponse explorerResponse = (ExplorerResponse) o;
    return Objects.equals(this.segments, explorerResponse.segments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(segments);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ExplorerResponse {\n");
    
    sb.append("    segments: ").append(toIndentedString(segments)).append("\n");
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

