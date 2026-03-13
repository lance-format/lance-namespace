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
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * An entry for deleting table versions in a batch operation. Each entry specifies a table and the
 * version ranges to delete.
 */
@Schema(
    name = "DeleteTableVersionsEntry",
    description =
        "An entry for deleting table versions in a batch operation. Each entry specifies a table and the version ranges to delete. ")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
public class DeleteTableVersionsEntry {

  @Valid private List<String> id = new ArrayList<>();

  @Valid private List<@Valid VersionRange> ranges = new ArrayList<>();

  public DeleteTableVersionsEntry() {
    super();
  }

  /** Constructor with only required parameters */
  public DeleteTableVersionsEntry(List<String> id, List<@Valid VersionRange> ranges) {
    this.id = id;
    this.ranges = ranges;
  }

  public DeleteTableVersionsEntry id(List<String> id) {
    this.id = id;
    return this;
  }

  public DeleteTableVersionsEntry addIdItem(String idItem) {
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
  @NotNull
  @Schema(
      name = "id",
      description = "The table identifier",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public List<String> getId() {
    return id;
  }

  public void setId(List<String> id) {
    this.id = id;
  }

  public DeleteTableVersionsEntry ranges(List<@Valid VersionRange> ranges) {
    this.ranges = ranges;
    return this;
  }

  public DeleteTableVersionsEntry addRangesItem(VersionRange rangesItem) {
    if (this.ranges == null) {
      this.ranges = new ArrayList<>();
    }
    this.ranges.add(rangesItem);
    return this;
  }

  /**
   * List of version ranges to delete. Each range specifies start (inclusive) and end (exclusive)
   * versions.
   *
   * @return ranges
   */
  @NotNull
  @Valid
  @Schema(
      name = "ranges",
      description =
          "List of version ranges to delete. Each range specifies start (inclusive) and end (exclusive) versions. ",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("ranges")
  public List<@Valid VersionRange> getRanges() {
    return ranges;
  }

  public void setRanges(List<@Valid VersionRange> ranges) {
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
    DeleteTableVersionsEntry deleteTableVersionsEntry = (DeleteTableVersionsEntry) o;
    return Objects.equals(this.id, deleteTableVersionsEntry.id)
        && Objects.equals(this.ranges, deleteTableVersionsEntry.ranges);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, ranges);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DeleteTableVersionsEntry {\n");
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
}
