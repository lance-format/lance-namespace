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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

/** Delete version ranges from a table. */
@JsonPropertyOrder({
  CommitTableOperationDeleteTableVersions.JSON_PROPERTY_TYPE,
  CommitTableOperationDeleteTableVersions.JSON_PROPERTY_ID,
  CommitTableOperationDeleteTableVersions.JSON_PROPERTY_RANGES
})
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.12.0")
public class CommitTableOperationDeleteTableVersions {
  /** Gets or Sets type */
  public enum TypeEnum {
    DELETE_TABLE_VERSIONS(String.valueOf("delete_table_versions"));

    private String value;

    TypeEnum(String value) {
      this.value = value;
    }

    @JsonValue
    public String getValue() {
      return value;
    }

    @Override
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static TypeEnum fromValue(String value) {
      for (TypeEnum b : TypeEnum.values()) {
        if (b.value.equals(value)) {
          return b;
        }
      }
      throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }
  }

  public static final String JSON_PROPERTY_TYPE = "type";
  @javax.annotation.Nonnull private TypeEnum type;

  public static final String JSON_PROPERTY_ID = "id";
  @javax.annotation.Nonnull private List<String> id = new ArrayList<>();

  public static final String JSON_PROPERTY_RANGES = "ranges";
  @javax.annotation.Nonnull private List<VersionRange> ranges = new ArrayList<>();

  public CommitTableOperationDeleteTableVersions() {}

  public CommitTableOperationDeleteTableVersions type(@javax.annotation.Nonnull TypeEnum type) {

    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public TypeEnum getType() {
    return type;
  }

  @JsonProperty(JSON_PROPERTY_TYPE)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setType(@javax.annotation.Nonnull TypeEnum type) {
    this.type = type;
  }

  public CommitTableOperationDeleteTableVersions id(@javax.annotation.Nonnull List<String> id) {

    this.id = id;
    return this;
  }

  public CommitTableOperationDeleteTableVersions addIdItem(String idItem) {
    if (this.id == null) {
      this.id = new ArrayList<>();
    }
    this.id.add(idItem);
    return this;
  }

  /**
   * The table identifier
   *
   * @return id
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public List<String> getId() {
    return id;
  }

  @JsonProperty(JSON_PROPERTY_ID)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setId(@javax.annotation.Nonnull List<String> id) {
    this.id = id;
  }

  public CommitTableOperationDeleteTableVersions ranges(
      @javax.annotation.Nonnull List<VersionRange> ranges) {

    this.ranges = ranges;
    return this;
  }

  public CommitTableOperationDeleteTableVersions addRangesItem(VersionRange rangesItem) {
    if (this.ranges == null) {
      this.ranges = new ArrayList<>();
    }
    this.ranges.add(rangesItem);
    return this;
  }

  /**
   * List of version ranges to delete
   *
   * @return ranges
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_RANGES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public List<VersionRange> getRanges() {
    return ranges;
  }

  @JsonProperty(JSON_PROPERTY_RANGES)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setRanges(@javax.annotation.Nonnull List<VersionRange> ranges) {
    this.ranges = ranges;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommitTableOperationDeleteTableVersions commitTableOperationDeleteTableVersions =
        (CommitTableOperationDeleteTableVersions) o;
    return Objects.equals(this.type, commitTableOperationDeleteTableVersions.type)
        && Objects.equals(this.id, commitTableOperationDeleteTableVersions.id)
        && Objects.equals(this.ranges, commitTableOperationDeleteTableVersions.ranges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, ranges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommitTableOperationDeleteTableVersions {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ranges: ").append(toIndentedString(ranges)).append("\n");
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

    // add `type` to the URL query string
    if (getType() != null) {
      try {
        joiner.add(
            String.format(
                "%stype%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getType()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `id` to the URL query string
    if (getId() != null) {
      for (int i = 0; i < getId().size(); i++) {
        try {
          joiner.add(
              String.format(
                  "%sid%s%s=%s",
                  prefix,
                  suffix,
                  "".equals(suffix)
                      ? ""
                      : String.format("%s%d%s", containerPrefix, i, containerSuffix),
                  URLEncoder.encode(String.valueOf(getId().get(i)), "UTF-8")
                      .replaceAll("\\+", "%20")));
        } catch (UnsupportedEncodingException e) {
          // Should never happen, UTF-8 is always supported
          throw new RuntimeException(e);
        }
      }
    }

    // add `ranges` to the URL query string
    if (getRanges() != null) {
      for (int i = 0; i < getRanges().size(); i++) {
        if (getRanges().get(i) != null) {
          joiner.add(
              getRanges()
                  .get(i)
                  .toUrlQueryString(
                      String.format(
                          "%sranges%s%s",
                          prefix,
                          suffix,
                          "".equals(suffix)
                              ? ""
                              : String.format("%s%d%s", containerPrefix, i, containerSuffix))));
        }
      }
    }

    return joiner.toString();
  }
}
