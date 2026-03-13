package org.lance.namespace.server.springboot.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.lance.namespace.server.springboot.model.DeleteTableVersionsEntry;
import org.lance.namespace.server.springboot.model.Identity;
import org.lance.namespace.server.springboot.model.VersionRange;
import org.springframework.lang.Nullable;
import java.time.OffsetDateTime;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import jakarta.annotation.Generated;

/**
 * Request to delete table version records. Supports deleting ranges of versions for efficient bulk cleanup. This request supports two modes: - Single-table (legacy): Use &#x60;id&#x60; + &#x60;ranges&#x60; to delete versions from one table. - Multi-table (transactional): Use &#x60;entries&#x60; to atomically delete versions   across multiple tables in a single operation. When &#x60;entries&#x60; is provided, &#x60;id&#x60; and &#x60;ranges&#x60; are ignored. 
 */

@Schema(name = "BatchDeleteTableVersionsRequest", description = "Request to delete table version records. Supports deleting ranges of versions for efficient bulk cleanup. This request supports two modes: - Single-table (legacy): Use `id` + `ranges` to delete versions from one table. - Multi-table (transactional): Use `entries` to atomically delete versions   across multiple tables in a single operation. When `entries` is provided, `id` and `ranges` are ignored. ")
@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", comments = "Generator version: 7.12.0")
public class BatchDeleteTableVersionsRequest {

  private Identity identity;

  @Valid
  private Map<String, String> context = new HashMap<>();

  @Valid
  private List<String> id = new ArrayList<>();

  @Valid
  private List<@Valid VersionRange> ranges = new ArrayList<>();

  @Valid
  private List<@Valid DeleteTableVersionsEntry> entries = new ArrayList<>();

  public BatchDeleteTableVersionsRequest identity(Identity identity) {
    this.identity = identity;
    return this;
  }

  /**
   * Get identity
   * @return identity
   */
  @Valid 
  @Schema(name = "identity", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("identity")
  public Identity getIdentity() {
    return identity;
  }

  public void setIdentity(Identity identity) {
    this.identity = identity;
  }

  public BatchDeleteTableVersionsRequest context(Map<String, String> context) {
    this.context = context;
    return this;
  }

  public BatchDeleteTableVersionsRequest putContextItem(String key, String contextItem) {
    if (this.context == null) {
      this.context = new HashMap<>();
    }
    this.context.put(key, contextItem);
    return this;
  }

  /**
   * Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention `x-lance-ctx-<key>: <value>`. For example, a context entry `{\"trace_id\": \"abc123\"}` would be sent as the header `x-lance-ctx-trace_id: abc123`. 
   * @return context
   */
  
  @Schema(name = "context", description = "Arbitrary context for a request as key-value pairs. How to use the context is custom to the specific implementation.  REST NAMESPACE ONLY Context entries are passed via HTTP headers using the naming convention `x-lance-ctx-<key>: <value>`. For example, a context entry `{\"trace_id\": \"abc123\"}` would be sent as the header `x-lance-ctx-trace_id: abc123`. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("context")
  public Map<String, String> getContext() {
    return context;
  }

  public void setContext(Map<String, String> context) {
    this.context = context;
  }

  public BatchDeleteTableVersionsRequest id(List<String> id) {
    this.id = id;
    return this;
  }

  public BatchDeleteTableVersionsRequest addIdItem(String idItem) {
    if (this.id == null) {
      this.id = new ArrayList<>();
    }
    this.id.add(idItem);
    return this;
  }

  /**
   * The table identifier (single-table mode, legacy). Ignored when `entries` is provided. 
   * @return id
   */
  
  @Schema(name = "id", description = "The table identifier (single-table mode, legacy). Ignored when `entries` is provided. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("id")
  public List<String> getId() {
    return id;
  }

  public void setId(List<String> id) {
    this.id = id;
  }

  public BatchDeleteTableVersionsRequest ranges(List<@Valid VersionRange> ranges) {
    this.ranges = ranges;
    return this;
  }

  public BatchDeleteTableVersionsRequest addRangesItem(VersionRange rangesItem) {
    if (this.ranges == null) {
      this.ranges = new ArrayList<>();
    }
    this.ranges.add(rangesItem);
    return this;
  }

  /**
   * List of version ranges to delete (single-table mode, legacy). Ignored when `entries` is provided. Each range specifies start (inclusive) and end (exclusive) versions. 
   * @return ranges
   */
  @Valid 
  @Schema(name = "ranges", description = "List of version ranges to delete (single-table mode, legacy). Ignored when `entries` is provided. Each range specifies start (inclusive) and end (exclusive) versions. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("ranges")
  public List<@Valid VersionRange> getRanges() {
    return ranges;
  }

  public void setRanges(List<@Valid VersionRange> ranges) {
    this.ranges = ranges;
  }

  public BatchDeleteTableVersionsRequest entries(List<@Valid DeleteTableVersionsEntry> entries) {
    this.entries = entries;
    return this;
  }

  public BatchDeleteTableVersionsRequest addEntriesItem(DeleteTableVersionsEntry entriesItem) {
    if (this.entries == null) {
      this.entries = new ArrayList<>();
    }
    this.entries.add(entriesItem);
    return this;
  }

  /**
   * List of per-table delete entries for multi-table transactional deletion. When provided, the operation atomically deletes versions across all specified tables. 
   * @return entries
   */
  @Valid 
  @Schema(name = "entries", description = "List of per-table delete entries for multi-table transactional deletion. When provided, the operation atomically deletes versions across all specified tables. ", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  @JsonProperty("entries")
  public List<@Valid DeleteTableVersionsEntry> getEntries() {
    return entries;
  }

  public void setEntries(List<@Valid DeleteTableVersionsEntry> entries) {
    this.entries = entries;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest = (BatchDeleteTableVersionsRequest) o;
    return Objects.equals(this.identity, batchDeleteTableVersionsRequest.identity) &&
        Objects.equals(this.context, batchDeleteTableVersionsRequest.context) &&
        Objects.equals(this.id, batchDeleteTableVersionsRequest.id) &&
        Objects.equals(this.ranges, batchDeleteTableVersionsRequest.ranges) &&
        Objects.equals(this.entries, batchDeleteTableVersionsRequest.entries);
  }

  @Override
  public int hashCode() {
    return Objects.hash(identity, context, id, ranges, entries);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BatchDeleteTableVersionsRequest {\n");
    sb.append("    identity: ").append(toIndentedString(identity)).append("\n");
    sb.append("    context: ").append(toIndentedString(context)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ranges: ").append(toIndentedString(ranges)).append("\n");
    sb.append("    entries: ").append(toIndentedString(entries)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

