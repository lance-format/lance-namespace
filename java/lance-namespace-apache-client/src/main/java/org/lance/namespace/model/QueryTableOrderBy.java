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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.StringJoiner;

/**
 * Ordering of a source field, matching Lance Scanner ColumnOrdering. Multiple entries are evaluated
 * in list order. Strings use UTF-8 binary lexicographic order and floating-point values use IEEE
 * 754 total order.
 */
@JsonPropertyOrder({
  QueryTableOrderBy.JSON_PROPERTY_COLUMN_NAME,
  QueryTableOrderBy.JSON_PROPERTY_ASCENDING,
  QueryTableOrderBy.JSON_PROPERTY_NULLS_FIRST
})
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.12.0")
public class QueryTableOrderBy {
  public static final String JSON_PROPERTY_COLUMN_NAME = "column_name";
  @javax.annotation.Nonnull private String columnName;

  public static final String JSON_PROPERTY_ASCENDING = "ascending";
  @javax.annotation.Nullable private Boolean ascending = true;

  public static final String JSON_PROPERTY_NULLS_FIRST = "nulls_first";
  @javax.annotation.Nullable private Boolean nullsFirst = false;

  public QueryTableOrderBy() {}

  public QueryTableOrderBy columnName(@javax.annotation.Nonnull String columnName) {

    this.columnName = columnName;
    return this;
  }

  /**
   * Source Lance field path to sort by, independent of output aliases. Nested fields use
   * dot-separated segments; use backtick-quoted segments for literal dots and double backticks
   * inside quoted segments.
   *
   * @return columnName
   */
  @javax.annotation.Nonnull
  @JsonProperty(JSON_PROPERTY_COLUMN_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getColumnName() {
    return columnName;
  }

  @JsonProperty(JSON_PROPERTY_COLUMN_NAME)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setColumnName(@javax.annotation.Nonnull String columnName) {
    this.columnName = columnName;
  }

  public QueryTableOrderBy ascending(@javax.annotation.Nullable Boolean ascending) {

    this.ascending = ascending;
    return this;
  }

  /**
   * Whether to sort in ascending order.
   *
   * @return ascending
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_ASCENDING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getAscending() {
    return ascending;
  }

  @JsonProperty(JSON_PROPERTY_ASCENDING)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setAscending(@javax.annotation.Nullable Boolean ascending) {
    this.ascending = ascending;
  }

  public QueryTableOrderBy nullsFirst(@javax.annotation.Nullable Boolean nullsFirst) {

    this.nullsFirst = nullsFirst;
    return this;
  }

  /**
   * Whether null values precede non-null values, independent of direction.
   *
   * @return nullsFirst
   */
  @javax.annotation.Nullable
  @JsonProperty(JSON_PROPERTY_NULLS_FIRST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Boolean getNullsFirst() {
    return nullsFirst;
  }

  @JsonProperty(JSON_PROPERTY_NULLS_FIRST)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setNullsFirst(@javax.annotation.Nullable Boolean nullsFirst) {
    this.nullsFirst = nullsFirst;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QueryTableOrderBy queryTableOrderBy = (QueryTableOrderBy) o;
    return Objects.equals(this.columnName, queryTableOrderBy.columnName)
        && Objects.equals(this.ascending, queryTableOrderBy.ascending)
        && Objects.equals(this.nullsFirst, queryTableOrderBy.nullsFirst);
  }

  @Override
  public int hashCode() {
    return Objects.hash(columnName, ascending, nullsFirst);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QueryTableOrderBy {\n");
    sb.append("    columnName: ").append(toIndentedString(columnName)).append("\n");
    sb.append("    ascending: ").append(toIndentedString(ascending)).append("\n");
    sb.append("    nullsFirst: ").append(toIndentedString(nullsFirst)).append("\n");
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

    // add `column_name` to the URL query string
    if (getColumnName() != null) {
      try {
        joiner.add(
            String.format(
                "%scolumn_name%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getColumnName()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `ascending` to the URL query string
    if (getAscending() != null) {
      try {
        joiner.add(
            String.format(
                "%sascending%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getAscending()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `nulls_first` to the URL query string
    if (getNullsFirst() != null) {
      try {
        joiner.add(
            String.format(
                "%snulls_first%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getNullsFirst()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }
}
