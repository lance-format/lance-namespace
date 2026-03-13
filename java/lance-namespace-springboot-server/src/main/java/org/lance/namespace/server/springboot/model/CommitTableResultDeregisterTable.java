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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.annotation.Generated;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/** Result of a deregister_table operation. */
@Schema(
    name = "CommitTableResultDeregisterTable",
    description = "Result of a deregister_table operation.")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
public class CommitTableResultDeregisterTable implements CommitTableResult {

  /** Gets or Sets type */
  public enum TypeEnum {
    DEREGISTER_TABLE("deregister_table");

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

  private TypeEnum type;

  @Valid private List<String> id = new ArrayList<>();

  private String location;

  public CommitTableResultDeregisterTable() {
    super();
  }

  /** Constructor with only required parameters */
  public CommitTableResultDeregisterTable(TypeEnum type, List<String> id) {
    this.type = type;
    this.id = id;
  }

  public CommitTableResultDeregisterTable type(TypeEnum type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   *
   * @return type
   */
  @NotNull
  @Schema(name = "type", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("type")
  public TypeEnum getType() {
    return type;
  }

  public void setType(TypeEnum type) {
    this.type = type;
  }

  public CommitTableResultDeregisterTable id(List<String> id) {
    this.id = id;
    return this;
  }

  public CommitTableResultDeregisterTable addIdItem(String idItem) {
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

  public CommitTableResultDeregisterTable location(String location) {
    this.location = location;
    return this;
  }

  /**
   * The storage location of the deregistered table
   *
   * @return location
   */
  @Schema(
      name = "location",
      description = "The storage location of the deregistered table",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("location")
  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommitTableResultDeregisterTable commitTableResultDeregisterTable =
        (CommitTableResultDeregisterTable) o;
    return Objects.equals(this.type, commitTableResultDeregisterTable.type)
        && Objects.equals(this.id, commitTableResultDeregisterTable.id)
        && Objects.equals(this.location, commitTableResultDeregisterTable.location);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, id, location);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommitTableResultDeregisterTable {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    location: ").append(toIndentedString(location)).append("\n");
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
