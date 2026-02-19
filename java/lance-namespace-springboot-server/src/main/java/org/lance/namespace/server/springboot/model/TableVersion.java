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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/** TableVersion */
@Generated(
    value = "org.openapitools.codegen.languages.SpringCodegen",
    comments = "Generator version: 7.12.0")
public class TableVersion {

  private Long version;

  private String manifestPath;

  private Long manifestSize;

  private String eTag;

  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime timestamp;

  @Valid private Map<String, String> metadata = new HashMap<>();

  public TableVersion() {
    super();
  }

  /** Constructor with only required parameters */
  public TableVersion(Long version, String manifestPath) {
    this.version = version;
    this.manifestPath = manifestPath;
  }

  public TableVersion version(Long version) {
    this.version = version;
    return this;
  }

  /**
   * Version number minimum: 0
   *
   * @return version
   */
  @NotNull
  @Min(0L)
  @Schema(
      name = "version",
      description = "Version number",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("version")
  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public TableVersion manifestPath(String manifestPath) {
    this.manifestPath = manifestPath;
    return this;
  }

  /**
   * Path to the manifest file for this version.
   *
   * @return manifestPath
   */
  @NotNull
  @Schema(
      name = "manifest_path",
      description = "Path to the manifest file for this version.",
      requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("manifest_path")
  public String getManifestPath() {
    return manifestPath;
  }

  public void setManifestPath(String manifestPath) {
    this.manifestPath = manifestPath;
  }

  public TableVersion manifestSize(Long manifestSize) {
    this.manifestSize = manifestSize;
    return this;
  }

  /**
   * Size of the manifest file in bytes minimum: 0
   *
   * @return manifestSize
   */
  @Min(0L)
  @Schema(
      name = "manifest_size",
      description = "Size of the manifest file in bytes",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("manifest_size")
  public Long getManifestSize() {
    return manifestSize;
  }

  public void setManifestSize(Long manifestSize) {
    this.manifestSize = manifestSize;
  }

  public TableVersion eTag(String eTag) {
    this.eTag = eTag;
    return this;
  }

  /**
   * Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores.
   *
   * @return eTag
   */
  @Schema(
      name = "e_tag",
      description =
          "Optional ETag for optimistic concurrency control. Useful for S3 and similar object stores. ",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("e_tag")
  public String geteTag() {
    return eTag;
  }

  public void seteTag(String eTag) {
    this.eTag = eTag;
  }

  public TableVersion timestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
    return this;
  }

  /**
   * Timestamp when the version was created
   *
   * @return timestamp
   */
  @Valid
  @Schema(
      name = "timestamp",
      description = "Timestamp when the version was created",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("timestamp")
  public OffsetDateTime getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(OffsetDateTime timestamp) {
    this.timestamp = timestamp;
  }

  public TableVersion metadata(Map<String, String> metadata) {
    this.metadata = metadata;
    return this;
  }

  public TableVersion putMetadataItem(String key, String metadataItem) {
    if (this.metadata == null) {
      this.metadata = new HashMap<>();
    }
    this.metadata.put(key, metadataItem);
    return this;
  }

  /**
   * Optional key-value pairs of metadata
   *
   * @return metadata
   */
  @Schema(
      name = "metadata",
      description = "Optional key-value pairs of metadata",
      requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("metadata")
  public Map<String, String> getMetadata() {
    return metadata;
  }

  public void setMetadata(Map<String, String> metadata) {
    this.metadata = metadata;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TableVersion tableVersion = (TableVersion) o;
    return Objects.equals(this.version, tableVersion.version)
        && Objects.equals(this.manifestPath, tableVersion.manifestPath)
        && Objects.equals(this.manifestSize, tableVersion.manifestSize)
        && Objects.equals(this.eTag, tableVersion.eTag)
        && Objects.equals(this.timestamp, tableVersion.timestamp)
        && Objects.equals(this.metadata, tableVersion.metadata);
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, manifestPath, manifestSize, eTag, timestamp, metadata);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TableVersion {\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    manifestPath: ").append(toIndentedString(manifestPath)).append("\n");
    sb.append("    manifestSize: ").append(toIndentedString(manifestSize)).append("\n");
    sb.append("    eTag: ").append(toIndentedString(eTag)).append("\n");
    sb.append("    timestamp: ").append(toIndentedString(timestamp)).append("\n");
    sb.append("    metadata: ").append(toIndentedString(metadata)).append("\n");
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
