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
import java.util.Objects;

/** Result of a create_table_version operation. */
@Schema(
    name = "CommitTableResultCreateTableVersion",
    description = "Result of a create_table_version operation.")
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
public class CommitTableResultCreateTableVersion implements CommitTableResult {

  /** Gets or Sets type */
  public enum TypeEnum {
    CREATE_TABLE_VERSION("create_table_version");

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

  private TableVersion version;

  public CommitTableResultCreateTableVersion() {
    super();
  }

  /** Constructor with only required parameters */
  public CommitTableResultCreateTableVersion(TypeEnum type, TableVersion version) {
    this.type = type;
    this.version = version;
  }

  public CommitTableResultCreateTableVersion type(TypeEnum type) {
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

  public CommitTableResultCreateTableVersion version(TableVersion version) {
    this.version = version;
    return this;
  }

  /**
   * The created table version details
   *
   * @return version
   */
  @NotNull
  @Valid
  @Schema(
      name = "version",
      description = "The created table version details",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version")
  public TableVersion getVersion() {
    return version;
  }

  public void setVersion(TableVersion version) {
    this.version = version;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CommitTableResultCreateTableVersion commitTableResultCreateTableVersion =
        (CommitTableResultCreateTableVersion) o;
    return Objects.equals(this.type, commitTableResultCreateTableVersion.type)
        && Objects.equals(this.version, commitTableResultCreateTableVersion.version);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, version);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CommitTableResultCreateTableVersion {\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
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
