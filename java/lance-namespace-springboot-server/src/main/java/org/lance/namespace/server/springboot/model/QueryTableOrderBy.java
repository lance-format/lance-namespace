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
package org.lance.namespace.server.springboot.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.constraints.*;

import java.util.*;
import java.util.Objects;

/**
 * Ordering of a source field, matching Lance Scanner ColumnOrdering. Multiple entries are evaluated
 * in list order. Strings use UTF-8 binary lexicographic order and floating-point values use IEEE
 * 754 total order.
 */
@Schema(
    name = "QueryTableOrderBy",
    description =
        "Ordering of a source field, matching Lance Scanner ColumnOrdering. Multiple entries are evaluated in list order. Strings use UTF-8 binary lexicographic order and floating-point values use IEEE 754 total order.")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
public class QueryTableOrderBy {

  private String columnName;

  private Boolean ascending = true;

  private Boolean nullsFirst = false;

  public QueryTableOrderBy() {
    super();
  }

  /** Constructor with only required parameters */
  public QueryTableOrderBy(String columnName) {
    this.columnName = columnName;
  }

  public QueryTableOrderBy columnName(String columnName) {
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
  @NotNull
  @Size(min = 1)
  @Schema(
      name = "column_name",
      description =
          "Source Lance field path to sort by, independent of output aliases. Nested fields use dot-separated segments; use backtick-quoted segments for literal dots and double backticks inside quoted segments.",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("column_name")
  public String getColumnName() {
    return columnName;
  }

  public void setColumnName(String columnName) {
    this.columnName = columnName;
  }

  public QueryTableOrderBy ascending(Boolean ascending) {
    this.ascending = ascending;
    return this;
  }

  /**
   * Whether to sort in ascending order.
   *
   * @return ascending
   */
  @Schema(
      name = "ascending",
      description = "Whether to sort in ascending order.",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ascending")
  public Boolean getAscending() {
    return ascending;
  }

  public void setAscending(Boolean ascending) {
    this.ascending = ascending;
  }

  public QueryTableOrderBy nullsFirst(Boolean nullsFirst) {
    this.nullsFirst = nullsFirst;
    return this;
  }

  /**
   * Whether null values precede non-null values, independent of direction.
   *
   * @return nullsFirst
   */
  @Schema(
      name = "nulls_first",
      description = "Whether null values precede non-null values, independent of direction.",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("nulls_first")
  public Boolean getNullsFirst() {
    return nullsFirst;
  }

  public void setNullsFirst(Boolean nullsFirst) {
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
}
