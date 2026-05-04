/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lance.namespace.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.openapitools.jackson.nullable.JsonNullable;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/** AlterVirtualColumnEntry */
@JsonPropertyOrder({
  AlterVirtualColumnEntry.JSON_PROPERTY_INPUT_COLUMNS,
  AlterVirtualColumnEntry.JSON_PROPERTY_IMAGE,
  AlterVirtualColumnEntry.JSON_PROPERTY_UDF,
  AlterVirtualColumnEntry.JSON_PROPERTY_UDF_NAME,
  AlterVirtualColumnEntry.JSON_PROPERTY_UDF_VERSION
})
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.12.0")
public class AlterVirtualColumnEntry {
  public static final String JSON_PROPERTY_INPUT_COLUMNS = "input_columns";

  @javax.annotation.Nullable
  private JsonNullable<List<String>> inputColumns = JsonNullable.<List<String>>undefined();

  public static final String JSON_PROPERTY_IMAGE = "image";
  @javax.annotation.Nullable private JsonNullable<String> image = JsonNullable.<String>undefined();

  public static final String JSON_PROPERTY_UDF = "udf";
  @javax.annotation.Nullable private JsonNullable<String> udf = JsonNullable.<String>undefined();

  public static final String JSON_PROPERTY_UDF_NAME = "udf_name";

  @javax.annotation.Nullable
  private JsonNullable<String> udfName = JsonNullable.<String>undefined();

  public static final String JSON_PROPERTY_UDF_VERSION = "udf_version";

  @javax.annotation.Nullable
  private JsonNullable<String> udfVersion = JsonNullable.<String>undefined();

  public AlterVirtualColumnEntry() {}

  public AlterVirtualColumnEntry inputColumns(
      @javax.annotation.Nullable List<String> inputColumns) {
    this.inputColumns = JsonNullable.<List<String>>of(inputColumns);

    return this;
  }

  public AlterVirtualColumnEntry addInputColumnsItem(String inputColumnsItem) {
    if (this.inputColumns == null || !this.inputColumns.isPresent()) {
      this.inputColumns = JsonNullable.<List<String>>of(new ArrayList<>());
    }
    try {
      this.inputColumns.get().add(inputColumnsItem);
    } catch (java.util.NoSuchElementException e) {
      // this can never happen, as we make sure above that the value is present
    }
    return this;
  }

  /**
   * List of input column names for the virtual column (optional)
   *
   * @return inputColumns
   */
  @javax.annotation.Nullable
  @JsonIgnore
  public List<String> getInputColumns() {
    return inputColumns.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_INPUT_COLUMNS)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public JsonNullable<List<String>> getInputColumns_JsonNullable() {
    return inputColumns;
  }

  @JsonProperty(JSON_PROPERTY_INPUT_COLUMNS)
  public void setInputColumns_JsonNullable(JsonNullable<List<String>> inputColumns) {
    this.inputColumns = inputColumns;
  }

  public void setInputColumns(@javax.annotation.Nullable List<String> inputColumns) {
    this.inputColumns = JsonNullable.<List<String>>of(inputColumns);
  }

  public AlterVirtualColumnEntry image(@javax.annotation.Nullable String image) {
    this.image = JsonNullable.<String>of(image);

    return this;
  }

  /**
   * Docker image to use for the UDF (optional)
   *
   * @return image
   */
  @javax.annotation.Nullable
  @JsonIgnore
  public String getImage() {
    return image.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_IMAGE)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public JsonNullable<String> getImage_JsonNullable() {
    return image;
  }

  @JsonProperty(JSON_PROPERTY_IMAGE)
  public void setImage_JsonNullable(JsonNullable<String> image) {
    this.image = image;
  }

  public void setImage(@javax.annotation.Nullable String image) {
    this.image = JsonNullable.<String>of(image);
  }

  public AlterVirtualColumnEntry udf(@javax.annotation.Nullable String udf) {
    this.udf = JsonNullable.<String>of(udf);

    return this;
  }

  /**
   * Base64 encoded pickled UDF (optional)
   *
   * @return udf
   */
  @javax.annotation.Nullable
  @JsonIgnore
  public String getUdf() {
    return udf.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_UDF)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public JsonNullable<String> getUdf_JsonNullable() {
    return udf;
  }

  @JsonProperty(JSON_PROPERTY_UDF)
  public void setUdf_JsonNullable(JsonNullable<String> udf) {
    this.udf = udf;
  }

  public void setUdf(@javax.annotation.Nullable String udf) {
    this.udf = JsonNullable.<String>of(udf);
  }

  public AlterVirtualColumnEntry udfName(@javax.annotation.Nullable String udfName) {
    this.udfName = JsonNullable.<String>of(udfName);

    return this;
  }

  /**
   * Name of the UDF (optional)
   *
   * @return udfName
   */
  @javax.annotation.Nullable
  @JsonIgnore
  public String getUdfName() {
    return udfName.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_UDF_NAME)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public JsonNullable<String> getUdfName_JsonNullable() {
    return udfName;
  }

  @JsonProperty(JSON_PROPERTY_UDF_NAME)
  public void setUdfName_JsonNullable(JsonNullable<String> udfName) {
    this.udfName = udfName;
  }

  public void setUdfName(@javax.annotation.Nullable String udfName) {
    this.udfName = JsonNullable.<String>of(udfName);
  }

  public AlterVirtualColumnEntry udfVersion(@javax.annotation.Nullable String udfVersion) {
    this.udfVersion = JsonNullable.<String>of(udfVersion);

    return this;
  }

  /**
   * Version of the UDF (optional)
   *
   * @return udfVersion
   */
  @javax.annotation.Nullable
  @JsonIgnore
  public String getUdfVersion() {
    return udfVersion.orElse(null);
  }

  @JsonProperty(JSON_PROPERTY_UDF_VERSION)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public JsonNullable<String> getUdfVersion_JsonNullable() {
    return udfVersion;
  }

  @JsonProperty(JSON_PROPERTY_UDF_VERSION)
  public void setUdfVersion_JsonNullable(JsonNullable<String> udfVersion) {
    this.udfVersion = udfVersion;
  }

  public void setUdfVersion(@javax.annotation.Nullable String udfVersion) {
    this.udfVersion = JsonNullable.<String>of(udfVersion);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AlterVirtualColumnEntry alterVirtualColumnEntry = (AlterVirtualColumnEntry) o;
    return equalsNullable(this.inputColumns, alterVirtualColumnEntry.inputColumns)
        && equalsNullable(this.image, alterVirtualColumnEntry.image)
        && equalsNullable(this.udf, alterVirtualColumnEntry.udf)
        && equalsNullable(this.udfName, alterVirtualColumnEntry.udfName)
        && equalsNullable(this.udfVersion, alterVirtualColumnEntry.udfVersion);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b
        || (a != null
            && b != null
            && a.isPresent()
            && b.isPresent()
            && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        hashCodeNullable(inputColumns),
        hashCodeNullable(image),
        hashCodeNullable(udf),
        hashCodeNullable(udfName),
        hashCodeNullable(udfVersion));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[] {a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AlterVirtualColumnEntry {\n");
    sb.append("    inputColumns: ").append(toIndentedString(inputColumns)).append("\n");
    sb.append("    image: ").append(toIndentedString(image)).append("\n");
    sb.append("    udf: ").append(toIndentedString(udf)).append("\n");
    sb.append("    udfName: ").append(toIndentedString(udfName)).append("\n");
    sb.append("    udfVersion: ").append(toIndentedString(udfVersion)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `input_columns` to the URL query string
    if (getInputColumns() != null) {
      for (int i = 0; i < getInputColumns().size(); i++) {
        try {
          joiner.add(
              String.format(
                  "%sinput_columns%s%s=%s",
                  prefix,
                  suffix,
                  "".equals(suffix)
                      ? ""
                      : String.format("%s%d%s", containerPrefix, i, containerSuffix),
                  URLEncoder.encode(String.valueOf(getInputColumns().get(i)), "UTF-8")
                      .replaceAll("\\+", "%20")));
        } catch (UnsupportedEncodingException e) {
          // Should never happen, UTF-8 is always supported
          throw new RuntimeException(e);
        }
      }
    }

    // add `image` to the URL query string
    if (getImage() != null) {
      try {
        joiner.add(
            String.format(
                "%simage%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getImage()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `udf` to the URL query string
    if (getUdf() != null) {
      try {
        joiner.add(
            String.format(
                "%sudf%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getUdf()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `udf_name` to the URL query string
    if (getUdfName() != null) {
      try {
        joiner.add(
            String.format(
                "%sudf_name%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getUdfName()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `udf_version` to the URL query string
    if (getUdfVersion() != null) {
      try {
        joiner.add(
            String.format(
                "%sudf_version%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getUdfVersion()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }
}
