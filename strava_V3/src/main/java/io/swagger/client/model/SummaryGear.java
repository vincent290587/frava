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
 * SummaryGear
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2020-05-31T19:43:52.141+02:00")
public class SummaryGear {
  @SerializedName("id")
  private String id = null;

  @SerializedName("resource_state")
  private Integer resourceState = null;

  @SerializedName("primary")
  private Boolean primary = null;

  @SerializedName("name")
  private String name = null;

  @SerializedName("distance")
  private Float distance = null;

  public SummaryGear id(String id) {
    this.id = id;
    return this;
  }

   /**
   * The gear&#39;s unique identifier.
   * @return id
  **/
  @ApiModelProperty(value = "The gear's unique identifier.")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public SummaryGear resourceState(Integer resourceState) {
    this.resourceState = resourceState;
    return this;
  }

   /**
   * Resource state, indicates level of detail. Possible values: 2 -&gt; \&quot;summary\&quot;, 3 -&gt; \&quot;detail\&quot;
   * @return resourceState
  **/
  @ApiModelProperty(value = "Resource state, indicates level of detail. Possible values: 2 -> \"summary\", 3 -> \"detail\"")
  public Integer getResourceState() {
    return resourceState;
  }

  public void setResourceState(Integer resourceState) {
    this.resourceState = resourceState;
  }

  public SummaryGear primary(Boolean primary) {
    this.primary = primary;
    return this;
  }

   /**
   * Whether this gear&#39;s is the owner&#39;s default one.
   * @return primary
  **/
  @ApiModelProperty(value = "Whether this gear's is the owner's default one.")
  public Boolean isPrimary() {
    return primary;
  }

  public void setPrimary(Boolean primary) {
    this.primary = primary;
  }

  public SummaryGear name(String name) {
    this.name = name;
    return this;
  }

   /**
   * The gear&#39;s name.
   * @return name
  **/
  @ApiModelProperty(value = "The gear's name.")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SummaryGear distance(Float distance) {
    this.distance = distance;
    return this;
  }

   /**
   * The distance logged with this gear.
   * @return distance
  **/
  @ApiModelProperty(value = "The distance logged with this gear.")
  public Float getDistance() {
    return distance;
  }

  public void setDistance(Float distance) {
    this.distance = distance;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    SummaryGear summaryGear = (SummaryGear) o;
    return Objects.equals(this.id, summaryGear.id) &&
        Objects.equals(this.resourceState, summaryGear.resourceState) &&
        Objects.equals(this.primary, summaryGear.primary) &&
        Objects.equals(this.name, summaryGear.name) &&
        Objects.equals(this.distance, summaryGear.distance);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, resourceState, primary, name, distance);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class SummaryGear {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    resourceState: ").append(toIndentedString(resourceState)).append("\n");
    sb.append("    primary: ").append(toIndentedString(primary)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    distance: ").append(toIndentedString(distance)).append("\n");
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

