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
package org.lance.namespace.client.async.api;

import org.lance.namespace.client.async.ApiClient;
import org.lance.namespace.client.async.ApiException;
import org.lance.namespace.client.async.ApiResponse;
import org.lance.namespace.client.async.Configuration;
import org.lance.namespace.client.async.Pair;
import org.lance.namespace.model.AlterTableAddColumnsRequest;
import org.lance.namespace.model.AlterTableAddColumnsResponse;
import org.lance.namespace.model.AlterTableAlterColumnsRequest;
import org.lance.namespace.model.AlterTableAlterColumnsResponse;
import org.lance.namespace.model.AlterTableDropColumnsRequest;
import org.lance.namespace.model.AlterTableDropColumnsResponse;
import org.lance.namespace.model.AnalyzeTableQueryPlanRequest;
import org.lance.namespace.model.BatchCommitTablesRequest;
import org.lance.namespace.model.BatchCommitTablesResponse;
import org.lance.namespace.model.BatchCreateTableVersionsRequest;
import org.lance.namespace.model.BatchCreateTableVersionsResponse;
import org.lance.namespace.model.BatchDeleteTableVersionsRequest;
import org.lance.namespace.model.BatchDeleteTableVersionsResponse;
import org.lance.namespace.model.CountTableRowsRequest;
import org.lance.namespace.model.CreateEmptyTableRequest;
import org.lance.namespace.model.CreateEmptyTableResponse;
import org.lance.namespace.model.CreateTableIndexRequest;
import org.lance.namespace.model.CreateTableIndexResponse;
import org.lance.namespace.model.CreateTableResponse;
import org.lance.namespace.model.CreateTableScalarIndexResponse;
import org.lance.namespace.model.CreateTableTagRequest;
import org.lance.namespace.model.CreateTableTagResponse;
import org.lance.namespace.model.CreateTableVersionRequest;
import org.lance.namespace.model.CreateTableVersionResponse;
import org.lance.namespace.model.DeclareTableRequest;
import org.lance.namespace.model.DeclareTableResponse;
import org.lance.namespace.model.DeleteFromTableRequest;
import org.lance.namespace.model.DeleteFromTableResponse;
import org.lance.namespace.model.DeleteTableTagRequest;
import org.lance.namespace.model.DeleteTableTagResponse;
import org.lance.namespace.model.DeregisterTableRequest;
import org.lance.namespace.model.DeregisterTableResponse;
import org.lance.namespace.model.DescribeTableIndexStatsRequest;
import org.lance.namespace.model.DescribeTableIndexStatsResponse;
import org.lance.namespace.model.DescribeTableRequest;
import org.lance.namespace.model.DescribeTableResponse;
import org.lance.namespace.model.DescribeTableVersionRequest;
import org.lance.namespace.model.DescribeTableVersionResponse;
import org.lance.namespace.model.DropTableIndexResponse;
import org.lance.namespace.model.DropTableResponse;
import org.lance.namespace.model.ExplainTableQueryPlanRequest;
import org.lance.namespace.model.GetTableStatsRequest;
import org.lance.namespace.model.GetTableStatsResponse;
import org.lance.namespace.model.GetTableTagVersionRequest;
import org.lance.namespace.model.GetTableTagVersionResponse;
import org.lance.namespace.model.InsertIntoTableResponse;
import org.lance.namespace.model.ListTableIndicesRequest;
import org.lance.namespace.model.ListTableIndicesResponse;
import org.lance.namespace.model.ListTableTagsResponse;
import org.lance.namespace.model.ListTableVersionsResponse;
import org.lance.namespace.model.ListTablesResponse;
import org.lance.namespace.model.MergeInsertIntoTableResponse;
import org.lance.namespace.model.QueryTableRequest;
import org.lance.namespace.model.RegisterTableRequest;
import org.lance.namespace.model.RegisterTableResponse;
import org.lance.namespace.model.RenameTableRequest;
import org.lance.namespace.model.RenameTableResponse;
import org.lance.namespace.model.RestoreTableRequest;
import org.lance.namespace.model.RestoreTableResponse;
import org.lance.namespace.model.TableExistsRequest;
import org.lance.namespace.model.UpdateTableRequest;
import org.lance.namespace.model.UpdateTableResponse;
import org.lance.namespace.model.UpdateTableTagRequest;
import org.lance.namespace.model.UpdateTableTagResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.20.0")
public class TableApi {
  /** Utility class for extending HttpRequest.Builder functionality. */
  private static class HttpRequestBuilderExtensions {
    /**
     * Adds additional headers to the provided HttpRequest.Builder. Useful for adding
     * method/endpoint specific headers.
     *
     * @param builder the HttpRequest.Builder to which headers will be added
     * @param headers a map of header names and values to add; may be null
     * @return the same HttpRequest.Builder instance with the additional headers set
     */
    static HttpRequest.Builder withAdditionalHeaders(
        HttpRequest.Builder builder, Map<String, String> headers) {
      if (headers != null) {
        for (Map.Entry<String, String> entry : headers.entrySet()) {
          builder.header(entry.getKey(), entry.getValue());
        }
      }
      return builder;
    }
  }

  private final HttpClient memberVarHttpClient;
  private final ObjectMapper memberVarObjectMapper;
  private final String memberVarBaseUri;
  private final Consumer<HttpRequest.Builder> memberVarInterceptor;
  private final Duration memberVarReadTimeout;
  private final Consumer<HttpResponse<InputStream>> memberVarResponseInterceptor;
  private final Consumer<HttpResponse<InputStream>> memberVarAsyncResponseInterceptor;

  public TableApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TableApi(ApiClient apiClient) {
    memberVarHttpClient = apiClient.getHttpClient();
    memberVarObjectMapper = apiClient.getObjectMapper();
    memberVarBaseUri = apiClient.getBaseUri();
    memberVarInterceptor = apiClient.getRequestInterceptor();
    memberVarReadTimeout = apiClient.getReadTimeout();
    memberVarResponseInterceptor = apiClient.getResponseInterceptor();
    memberVarAsyncResponseInterceptor = apiClient.getAsyncResponseInterceptor();
  }

  private ApiException getApiException(String operationId, HttpResponse<InputStream> response) {
    try {
      InputStream responseBody = ApiClient.getResponseBody(response);
      String body = null;
      if (responseBody != null) {
        body = new String(responseBody.readAllBytes());
        responseBody.close();
      }
      String message = formatExceptionMessage(operationId, response.statusCode(), body);
      return new ApiException(response.statusCode(), message, response.headers(), body);
    } catch (IOException e) {
      return new ApiException(e);
    }
  }

  private String formatExceptionMessage(String operationId, int statusCode, String body) {
    if (body == null || body.isEmpty()) {
      body = "[no body]";
    }
    return operationId + " call failed with: " + statusCode + " - " + body;
  }

  /**
   * Download file from the given response.
   *
   * @param response Response
   * @return File
   * @throws ApiException If fail to read file content from response and write to disk
   */
  public File downloadFileFromResponse(HttpResponse<InputStream> response, InputStream responseBody)
      throws ApiException {
    if (responseBody == null) {
      throw new ApiException(new IOException("Response body is empty"));
    }
    try {
      File file = prepareDownloadFile(response);
      java.nio.file.Files.copy(
          responseBody, file.toPath(), java.nio.file.StandardCopyOption.REPLACE_EXISTING);
      return file;
    } catch (IOException e) {
      throw new ApiException(e);
    }
  }

  /**
   * Prepare the file for download from the response.
   *
   * @param response a {@link java.net.http.HttpResponse} object.
   * @return a {@link java.io.File} object.
   * @throws java.io.IOException if any.
   */
  private File prepareDownloadFile(HttpResponse<InputStream> response) throws IOException {
    String filename = null;
    java.util.Optional<String> contentDisposition =
        response.headers().firstValue("Content-Disposition");
    if (contentDisposition.isPresent() && !"".equals(contentDisposition.get())) {
      // Get filename from the Content-Disposition header.
      java.util.regex.Pattern pattern =
          java.util.regex.Pattern.compile("filename=['\"]?([^'\"\\s]+)['\"]?");
      java.util.regex.Matcher matcher = pattern.matcher(contentDisposition.get());
      if (matcher.find()) filename = matcher.group(1);
    }
    File file = null;
    if (filename != null) {
      java.nio.file.Path tempDir = java.nio.file.Files.createTempDirectory("swagger-gen-native");
      java.nio.file.Path filePath = java.nio.file.Files.createFile(tempDir.resolve(filename));
      file = filePath.toFile();
      tempDir.toFile().deleteOnExit(); // best effort cleanup
      file.deleteOnExit(); // best effort cleanup
    } else {
      file = java.nio.file.Files.createTempFile("download-", "").toFile();
      file.deleteOnExit(); // best effort cleanup
    }
    return file;
  }

  /**
   * Add new columns to table schema Add new columns to table &#x60;id&#x60; using SQL expressions
   * or default values.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAddColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;AlterTableAddColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableAddColumnsResponse> alterTableAddColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAddColumnsRequest alterTableAddColumnsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return alterTableAddColumns(id, alterTableAddColumnsRequest, delimiter, null);
  }

  /**
   * Add new columns to table schema Add new columns to table &#x60;id&#x60; using SQL expressions
   * or default values.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAddColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;AlterTableAddColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableAddColumnsResponse> alterTableAddColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAddColumnsRequest alterTableAddColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return alterTableAddColumnsWithHttpInfo(id, alterTableAddColumnsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Add new columns to table schema Add new columns to table &#x60;id&#x60; using SQL expressions
   * or default values.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAddColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableAddColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableAddColumnsResponse>>
      alterTableAddColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableAddColumnsRequest alterTableAddColumnsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return alterTableAddColumnsWithHttpInfo(id, alterTableAddColumnsRequest, delimiter, null);
  }

  /**
   * Add new columns to table schema Add new columns to table &#x60;id&#x60; using SQL expressions
   * or default values.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAddColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableAddColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableAddColumnsResponse>>
      alterTableAddColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableAddColumnsRequest alterTableAddColumnsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          alterTableAddColumnsRequestBuilder(id, alterTableAddColumnsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("alterTableAddColumns", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<AlterTableAddColumnsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    AlterTableAddColumnsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<AlterTableAddColumnsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<AlterTableAddColumnsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder alterTableAddColumnsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAddColumnsRequest alterTableAddColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling alterTableAddColumns");
    }
    // verify the required parameter 'alterTableAddColumnsRequest' is set
    if (alterTableAddColumnsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'alterTableAddColumnsRequest' when calling alterTableAddColumns");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/add_columns".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(alterTableAddColumnsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Modify existing columns Modify existing columns in table &#x60;id&#x60;, such as renaming or
   * changing data types.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAlterColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;AlterTableAlterColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableAlterColumnsResponse> alterTableAlterColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAlterColumnsRequest alterTableAlterColumnsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return alterTableAlterColumns(id, alterTableAlterColumnsRequest, delimiter, null);
  }

  /**
   * Modify existing columns Modify existing columns in table &#x60;id&#x60;, such as renaming or
   * changing data types.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAlterColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;AlterTableAlterColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableAlterColumnsResponse> alterTableAlterColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAlterColumnsRequest alterTableAlterColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return alterTableAlterColumnsWithHttpInfo(
              id, alterTableAlterColumnsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Modify existing columns Modify existing columns in table &#x60;id&#x60;, such as renaming or
   * changing data types.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAlterColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableAlterColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableAlterColumnsResponse>>
      alterTableAlterColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableAlterColumnsRequest alterTableAlterColumnsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return alterTableAlterColumnsWithHttpInfo(id, alterTableAlterColumnsRequest, delimiter, null);
  }

  /**
   * Modify existing columns Modify existing columns in table &#x60;id&#x60;, such as renaming or
   * changing data types.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableAlterColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableAlterColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableAlterColumnsResponse>>
      alterTableAlterColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableAlterColumnsRequest alterTableAlterColumnsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          alterTableAlterColumnsRequestBuilder(
              id, alterTableAlterColumnsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("alterTableAlterColumns", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<AlterTableAlterColumnsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    AlterTableAlterColumnsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<AlterTableAlterColumnsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<AlterTableAlterColumnsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder alterTableAlterColumnsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableAlterColumnsRequest alterTableAlterColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling alterTableAlterColumns");
    }
    // verify the required parameter 'alterTableAlterColumnsRequest' is set
    if (alterTableAlterColumnsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'alterTableAlterColumnsRequest' when calling alterTableAlterColumns");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/alter_columns".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(alterTableAlterColumnsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Remove columns from table Remove specified columns from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableDropColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;AlterTableDropColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableDropColumnsResponse> alterTableDropColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableDropColumnsRequest alterTableDropColumnsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return alterTableDropColumns(id, alterTableDropColumnsRequest, delimiter, null);
  }

  /**
   * Remove columns from table Remove specified columns from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableDropColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;AlterTableDropColumnsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTableDropColumnsResponse> alterTableDropColumns(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableDropColumnsRequest alterTableDropColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return alterTableDropColumnsWithHttpInfo(id, alterTableDropColumnsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Remove columns from table Remove specified columns from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableDropColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableDropColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableDropColumnsResponse>>
      alterTableDropColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableDropColumnsRequest alterTableDropColumnsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return alterTableDropColumnsWithHttpInfo(id, alterTableDropColumnsRequest, delimiter, null);
  }

  /**
   * Remove columns from table Remove specified columns from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTableDropColumnsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTableDropColumnsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTableDropColumnsResponse>>
      alterTableDropColumnsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull AlterTableDropColumnsRequest alterTableDropColumnsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          alterTableDropColumnsRequestBuilder(id, alterTableDropColumnsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("alterTableDropColumns", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<AlterTableDropColumnsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    AlterTableDropColumnsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<AlterTableDropColumnsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<AlterTableDropColumnsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder alterTableDropColumnsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTableDropColumnsRequest alterTableDropColumnsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling alterTableDropColumns");
    }
    // verify the required parameter 'alterTableDropColumnsRequest' is set
    if (alterTableDropColumnsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'alterTableDropColumnsRequest' when calling alterTableDropColumns");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/drop_columns".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(alterTableDropColumnsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Analyze query execution plan Analyze the query execution plan for a query against table
   * &#x60;id&#x60;. Returns detailed statistics and analysis of the query execution plan. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;AnalyzeTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param analyzeTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;String&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<String> analyzeTableQueryPlan(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AnalyzeTableQueryPlanRequest analyzeTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return analyzeTableQueryPlan(id, analyzeTableQueryPlanRequest, delimiter, null);
  }

  /**
   * Analyze query execution plan Analyze the query execution plan for a query against table
   * &#x60;id&#x60;. Returns detailed statistics and analysis of the query execution plan. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;AnalyzeTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param analyzeTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;String&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<String> analyzeTableQueryPlan(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AnalyzeTableQueryPlanRequest analyzeTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return analyzeTableQueryPlanWithHttpInfo(id, analyzeTableQueryPlanRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Analyze query execution plan Analyze the query execution plan for a query against table
   * &#x60;id&#x60;. Returns detailed statistics and analysis of the query execution plan. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;AnalyzeTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param analyzeTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<String>> analyzeTableQueryPlanWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AnalyzeTableQueryPlanRequest analyzeTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return analyzeTableQueryPlanWithHttpInfo(id, analyzeTableQueryPlanRequest, delimiter, null);
  }

  /**
   * Analyze query execution plan Analyze the query execution plan for a query against table
   * &#x60;id&#x60;. Returns detailed statistics and analysis of the query execution plan. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;AnalyzeTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param analyzeTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<String>> analyzeTableQueryPlanWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AnalyzeTableQueryPlanRequest analyzeTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          analyzeTableQueryPlanRequestBuilder(id, analyzeTableQueryPlanRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("analyzeTableQueryPlan", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<String>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    String responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<String>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<String>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder analyzeTableQueryPlanRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AnalyzeTableQueryPlanRequest analyzeTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling analyzeTableQueryPlan");
    }
    // verify the required parameter 'analyzeTableQueryPlanRequest' is set
    if (analyzeTableQueryPlanRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'analyzeTableQueryPlanRequest' when calling analyzeTableQueryPlan");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/analyze_plan".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(analyzeTableQueryPlanRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Atomically commit a batch of mixed table operations Atomically commit a batch of table
   * operations. This is a generalized version of &#x60;BatchCreateTableVersions&#x60; that supports
   * mixed operation types within a single atomic transaction at the metadata layer. Supported
   * operation types: - &#x60;DeclareTable&#x60;: Declare (reserve) a new table -
   * &#x60;CreateTableVersion&#x60;: Create a new version entry for a table -
   * &#x60;DeleteTableVersions&#x60;: Delete version ranges from a table -
   * &#x60;DeregisterTable&#x60;: Deregister (soft-delete) a table All operations are committed
   * atomically: either all succeed or none are applied.
   *
   * @param batchCommitTablesRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;BatchCommitTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchCommitTablesResponse> batchCommitTables(
      @javax.annotation.Nonnull BatchCommitTablesRequest batchCommitTablesRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return batchCommitTables(batchCommitTablesRequest, delimiter, null);
  }

  /**
   * Atomically commit a batch of mixed table operations Atomically commit a batch of table
   * operations. This is a generalized version of &#x60;BatchCreateTableVersions&#x60; that supports
   * mixed operation types within a single atomic transaction at the metadata layer. Supported
   * operation types: - &#x60;DeclareTable&#x60;: Declare (reserve) a new table -
   * &#x60;CreateTableVersion&#x60;: Create a new version entry for a table -
   * &#x60;DeleteTableVersions&#x60;: Delete version ranges from a table -
   * &#x60;DeregisterTable&#x60;: Deregister (soft-delete) a table All operations are committed
   * atomically: either all succeed or none are applied.
   *
   * @param batchCommitTablesRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;BatchCommitTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchCommitTablesResponse> batchCommitTables(
      @javax.annotation.Nonnull BatchCommitTablesRequest batchCommitTablesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return batchCommitTablesWithHttpInfo(batchCommitTablesRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Atomically commit a batch of mixed table operations Atomically commit a batch of table
   * operations. This is a generalized version of &#x60;BatchCreateTableVersions&#x60; that supports
   * mixed operation types within a single atomic transaction at the metadata layer. Supported
   * operation types: - &#x60;DeclareTable&#x60;: Declare (reserve) a new table -
   * &#x60;CreateTableVersion&#x60;: Create a new version entry for a table -
   * &#x60;DeleteTableVersions&#x60;: Delete version ranges from a table -
   * &#x60;DeregisterTable&#x60;: Deregister (soft-delete) a table All operations are committed
   * atomically: either all succeed or none are applied.
   *
   * @param batchCommitTablesRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;BatchCommitTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchCommitTablesResponse>> batchCommitTablesWithHttpInfo(
      @javax.annotation.Nonnull BatchCommitTablesRequest batchCommitTablesRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return batchCommitTablesWithHttpInfo(batchCommitTablesRequest, delimiter, null);
  }

  /**
   * Atomically commit a batch of mixed table operations Atomically commit a batch of table
   * operations. This is a generalized version of &#x60;BatchCreateTableVersions&#x60; that supports
   * mixed operation types within a single atomic transaction at the metadata layer. Supported
   * operation types: - &#x60;DeclareTable&#x60;: Declare (reserve) a new table -
   * &#x60;CreateTableVersion&#x60;: Create a new version entry for a table -
   * &#x60;DeleteTableVersions&#x60;: Delete version ranges from a table -
   * &#x60;DeregisterTable&#x60;: Deregister (soft-delete) a table All operations are committed
   * atomically: either all succeed or none are applied.
   *
   * @param batchCommitTablesRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;BatchCommitTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchCommitTablesResponse>> batchCommitTablesWithHttpInfo(
      @javax.annotation.Nonnull BatchCommitTablesRequest batchCommitTablesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          batchCommitTablesRequestBuilder(batchCommitTablesRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("batchCommitTables", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<BatchCommitTablesResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    BatchCommitTablesResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<BatchCommitTablesResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<BatchCommitTablesResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder batchCommitTablesRequestBuilder(
      @javax.annotation.Nonnull BatchCommitTablesRequest batchCommitTablesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'batchCommitTablesRequest' is set
    if (batchCommitTablesRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'batchCommitTablesRequest' when calling batchCommitTables");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/v1/table/batch-commit";

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(batchCommitTablesRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Atomically create versions for multiple tables Atomically create new version entries for
   * multiple tables. This operation is atomic: either all table versions are created successfully,
   * or none are created. If any version creation fails (e.g., due to conflict), the entire batch
   * operation fails. Each entry in the request specifies the table identifier and version details.
   * This supports &#x60;put_if_not_exists&#x60; semantics for each version entry.
   *
   * @param batchCreateTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;BatchCreateTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchCreateTableVersionsResponse> batchCreateTableVersions(
      @javax.annotation.Nonnull BatchCreateTableVersionsRequest batchCreateTableVersionsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return batchCreateTableVersions(batchCreateTableVersionsRequest, delimiter, null);
  }

  /**
   * Atomically create versions for multiple tables Atomically create new version entries for
   * multiple tables. This operation is atomic: either all table versions are created successfully,
   * or none are created. If any version creation fails (e.g., due to conflict), the entire batch
   * operation fails. Each entry in the request specifies the table identifier and version details.
   * This supports &#x60;put_if_not_exists&#x60; semantics for each version entry.
   *
   * @param batchCreateTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;BatchCreateTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchCreateTableVersionsResponse> batchCreateTableVersions(
      @javax.annotation.Nonnull BatchCreateTableVersionsRequest batchCreateTableVersionsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return batchCreateTableVersionsWithHttpInfo(
              batchCreateTableVersionsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Atomically create versions for multiple tables Atomically create new version entries for
   * multiple tables. This operation is atomic: either all table versions are created successfully,
   * or none are created. If any version creation fails (e.g., due to conflict), the entire batch
   * operation fails. Each entry in the request specifies the table identifier and version details.
   * This supports &#x60;put_if_not_exists&#x60; semantics for each version entry.
   *
   * @param batchCreateTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;BatchCreateTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchCreateTableVersionsResponse>>
      batchCreateTableVersionsWithHttpInfo(
          @javax.annotation.Nonnull BatchCreateTableVersionsRequest batchCreateTableVersionsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return batchCreateTableVersionsWithHttpInfo(batchCreateTableVersionsRequest, delimiter, null);
  }

  /**
   * Atomically create versions for multiple tables Atomically create new version entries for
   * multiple tables. This operation is atomic: either all table versions are created successfully,
   * or none are created. If any version creation fails (e.g., due to conflict), the entire batch
   * operation fails. Each entry in the request specifies the table identifier and version details.
   * This supports &#x60;put_if_not_exists&#x60; semantics for each version entry.
   *
   * @param batchCreateTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;BatchCreateTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchCreateTableVersionsResponse>>
      batchCreateTableVersionsWithHttpInfo(
          @javax.annotation.Nonnull BatchCreateTableVersionsRequest batchCreateTableVersionsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          batchCreateTableVersionsRequestBuilder(
              batchCreateTableVersionsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("batchCreateTableVersions", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<BatchCreateTableVersionsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    BatchCreateTableVersionsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<BatchCreateTableVersionsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<BatchCreateTableVersionsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder batchCreateTableVersionsRequestBuilder(
      @javax.annotation.Nonnull BatchCreateTableVersionsRequest batchCreateTableVersionsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'batchCreateTableVersionsRequest' is set
    if (batchCreateTableVersionsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'batchCreateTableVersionsRequest' when calling batchCreateTableVersions");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/v1/table/version/batch-create";

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(batchCreateTableVersionsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Delete table version records Delete version metadata records for table &#x60;id&#x60;. This
   * operation deletes version tracking records, NOT the actual table data. It supports deleting
   * ranges of versions for efficient bulk cleanup. Special range values: - &#x60;start_version:
   * 0&#x60; with &#x60;end_version: -1&#x60; means delete ALL version records
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param batchDeleteTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;BatchDeleteTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchDeleteTableVersionsResponse> batchDeleteTableVersions(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return batchDeleteTableVersions(id, batchDeleteTableVersionsRequest, delimiter, null);
  }

  /**
   * Delete table version records Delete version metadata records for table &#x60;id&#x60;. This
   * operation deletes version tracking records, NOT the actual table data. It supports deleting
   * ranges of versions for efficient bulk cleanup. Special range values: - &#x60;start_version:
   * 0&#x60; with &#x60;end_version: -1&#x60; means delete ALL version records
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param batchDeleteTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;BatchDeleteTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<BatchDeleteTableVersionsResponse> batchDeleteTableVersions(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return batchDeleteTableVersionsWithHttpInfo(
              id, batchDeleteTableVersionsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Delete table version records Delete version metadata records for table &#x60;id&#x60;. This
   * operation deletes version tracking records, NOT the actual table data. It supports deleting
   * ranges of versions for efficient bulk cleanup. Special range values: - &#x60;start_version:
   * 0&#x60; with &#x60;end_version: -1&#x60; means delete ALL version records
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param batchDeleteTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;BatchDeleteTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchDeleteTableVersionsResponse>>
      batchDeleteTableVersionsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return batchDeleteTableVersionsWithHttpInfo(
        id, batchDeleteTableVersionsRequest, delimiter, null);
  }

  /**
   * Delete table version records Delete version metadata records for table &#x60;id&#x60;. This
   * operation deletes version tracking records, NOT the actual table data. It supports deleting
   * ranges of versions for efficient bulk cleanup. Special range values: - &#x60;start_version:
   * 0&#x60; with &#x60;end_version: -1&#x60; means delete ALL version records
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param batchDeleteTableVersionsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;BatchDeleteTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<BatchDeleteTableVersionsResponse>>
      batchDeleteTableVersionsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          batchDeleteTableVersionsRequestBuilder(
              id, batchDeleteTableVersionsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("batchDeleteTableVersions", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<BatchDeleteTableVersionsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    BatchDeleteTableVersionsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<BatchDeleteTableVersionsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<BatchDeleteTableVersionsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder batchDeleteTableVersionsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull BatchDeleteTableVersionsRequest batchDeleteTableVersionsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling batchDeleteTableVersions");
    }
    // verify the required parameter 'batchDeleteTableVersionsRequest' is set
    if (batchDeleteTableVersionsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'batchDeleteTableVersionsRequest' when calling batchDeleteTableVersions");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/version/delete".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(batchDeleteTableVersionsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Count rows in a table Count the number of rows in table &#x60;id&#x60; REST NAMESPACE ONLY REST
   * namespace returns the response as a plain integer instead of the
   * &#x60;CountTableRowsResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param countTableRowsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;Long&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Long> countTableRows(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CountTableRowsRequest countTableRowsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return countTableRows(id, countTableRowsRequest, delimiter, null);
  }

  /**
   * Count rows in a table Count the number of rows in table &#x60;id&#x60; REST NAMESPACE ONLY REST
   * namespace returns the response as a plain integer instead of the
   * &#x60;CountTableRowsResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param countTableRowsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;Long&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Long> countTableRows(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CountTableRowsRequest countTableRowsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return countTableRowsWithHttpInfo(id, countTableRowsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Count rows in a table Count the number of rows in table &#x60;id&#x60; REST NAMESPACE ONLY REST
   * namespace returns the response as a plain integer instead of the
   * &#x60;CountTableRowsResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param countTableRowsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;Long&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Long>> countTableRowsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CountTableRowsRequest countTableRowsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return countTableRowsWithHttpInfo(id, countTableRowsRequest, delimiter, null);
  }

  /**
   * Count rows in a table Count the number of rows in table &#x60;id&#x60; REST NAMESPACE ONLY REST
   * namespace returns the response as a plain integer instead of the
   * &#x60;CountTableRowsResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param countTableRowsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;Long&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Long>> countTableRowsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CountTableRowsRequest countTableRowsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          countTableRowsRequestBuilder(id, countTableRowsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("countTableRows", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<Long>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    Long responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<Long>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<Long>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder countTableRowsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CountTableRowsRequest countTableRowsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling countTableRows");
    }
    // verify the required parameter 'countTableRowsRequest' is set
    if (countTableRowsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'countTableRowsRequest' when calling countTableRows");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/count_rows".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(countTableRowsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create an empty table Create an empty table with the given name without touching storage. This
   * is a metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files. **Deprecated**: Use &#x60;DeclareTable&#x60; instead.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createEmptyTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateEmptyTableResponse&gt;
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public CompletableFuture<CreateEmptyTableResponse> createEmptyTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateEmptyTableRequest createEmptyTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createEmptyTable(id, createEmptyTableRequest, delimiter, null);
  }

  /**
   * Create an empty table Create an empty table with the given name without touching storage. This
   * is a metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files. **Deprecated**: Use &#x60;DeclareTable&#x60; instead.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createEmptyTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateEmptyTableResponse&gt;
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public CompletableFuture<CreateEmptyTableResponse> createEmptyTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateEmptyTableRequest createEmptyTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createEmptyTableWithHttpInfo(id, createEmptyTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create an empty table Create an empty table with the given name without touching storage. This
   * is a metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files. **Deprecated**: Use &#x60;DeclareTable&#x60; instead.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createEmptyTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateEmptyTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public CompletableFuture<ApiResponse<CreateEmptyTableResponse>> createEmptyTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateEmptyTableRequest createEmptyTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createEmptyTableWithHttpInfo(id, createEmptyTableRequest, delimiter, null);
  }

  /**
   * Create an empty table Create an empty table with the given name without touching storage. This
   * is a metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files. **Deprecated**: Use &#x60;DeclareTable&#x60; instead.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createEmptyTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateEmptyTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   * @deprecated
   */
  @Deprecated
  public CompletableFuture<ApiResponse<CreateEmptyTableResponse>> createEmptyTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateEmptyTableRequest createEmptyTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createEmptyTableRequestBuilder(id, createEmptyTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createEmptyTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateEmptyTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateEmptyTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateEmptyTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateEmptyTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createEmptyTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateEmptyTableRequest createEmptyTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createEmptyTable");
    }
    // verify the required parameter 'createEmptyTableRequest' is set
    if (createEmptyTableRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createEmptyTableRequest' when calling createEmptyTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/create-empty".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createEmptyTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create a table with the given name Create table &#x60;id&#x60; in the namespace with the given
   * data in Arrow IPC stream. The schema of the Arrow IPC stream is used as the table schema. If
   * the stream is empty, the API creates a new empty table. REST NAMESPACE ONLY REST namespace uses
   * Arrow IPC stream as the request body. It passes in the &#x60;CreateTableRequest&#x60;
   * information in the following way: - &#x60;id&#x60;: pass through path parameter of the same
   * name - &#x60;mode&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC data (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode (optional)
   * @return CompletableFuture&lt;CreateTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableResponse> createTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode)
      throws ApiException {
    return createTable(id, body, delimiter, mode, null);
  }

  /**
   * Create a table with the given name Create table &#x60;id&#x60; in the namespace with the given
   * data in Arrow IPC stream. The schema of the Arrow IPC stream is used as the table schema. If
   * the stream is empty, the API creates a new empty table. REST NAMESPACE ONLY REST namespace uses
   * Arrow IPC stream as the request body. It passes in the &#x60;CreateTableRequest&#x60;
   * information in the following way: - &#x60;id&#x60;: pass through path parameter of the same
   * name - &#x60;mode&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC data (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableResponse> createTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createTableWithHttpInfo(id, body, delimiter, mode, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create a table with the given name Create table &#x60;id&#x60; in the namespace with the given
   * data in Arrow IPC stream. The schema of the Arrow IPC stream is used as the table schema. If
   * the stream is empty, the API creates a new empty table. REST NAMESPACE ONLY REST namespace uses
   * Arrow IPC stream as the request body. It passes in the &#x60;CreateTableRequest&#x60;
   * information in the following way: - &#x60;id&#x60;: pass through path parameter of the same
   * name - &#x60;mode&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC data (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableResponse>> createTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode)
      throws ApiException {
    return createTableWithHttpInfo(id, body, delimiter, mode, null);
  }

  /**
   * Create a table with the given name Create table &#x60;id&#x60; in the namespace with the given
   * data in Arrow IPC stream. The schema of the Arrow IPC stream is used as the table schema. If
   * the stream is empty, the API creates a new empty table. REST NAMESPACE ONLY REST namespace uses
   * Arrow IPC stream as the request body. It passes in the &#x60;CreateTableRequest&#x60;
   * information in the following way: - &#x60;id&#x60;: pass through path parameter of the same
   * name - &#x60;mode&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC data (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableResponse>> createTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createTableRequestBuilder(id, body, delimiter, mode, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling createTable");
    }
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(400, "Missing the required parameter 'body' when calling createTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/create".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "mode";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("mode", mode));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/vnd.apache.arrow.stream");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(body);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create an index on a table Create an index on a table column for faster search operations.
   * Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and scalar indexes (BTREE,
   * BITMAP, FTS, etc.). Index creation is handled asynchronously. Use the
   * &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to monitor
   * index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateTableIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableIndexResponse> createTableIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableIndex(id, createTableIndexRequest, delimiter, null);
  }

  /**
   * Create an index on a table Create an index on a table column for faster search operations.
   * Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and scalar indexes (BTREE,
   * BITMAP, FTS, etc.). Index creation is handled asynchronously. Use the
   * &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to monitor
   * index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateTableIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableIndexResponse> createTableIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createTableIndexWithHttpInfo(id, createTableIndexRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create an index on a table Create an index on a table column for faster search operations.
   * Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and scalar indexes (BTREE,
   * BITMAP, FTS, etc.). Index creation is handled asynchronously. Use the
   * &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to monitor
   * index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableIndexResponse>> createTableIndexWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableIndexWithHttpInfo(id, createTableIndexRequest, delimiter, null);
  }

  /**
   * Create an index on a table Create an index on a table column for faster search operations.
   * Supports vector indexes (IVF_FLAT, IVF_HNSW_SQ, IVF_PQ, etc.) and scalar indexes (BTREE,
   * BITMAP, FTS, etc.). Index creation is handled asynchronously. Use the
   * &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to monitor
   * index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableIndexResponse>> createTableIndexWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createTableIndexRequestBuilder(id, createTableIndexRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createTableIndex", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateTableIndexResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateTableIndexResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateTableIndexResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateTableIndexResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createTableIndexRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createTableIndex");
    }
    // verify the required parameter 'createTableIndexRequest' is set
    if (createTableIndexRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createTableIndexRequest' when calling createTableIndex");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/create_index".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createTableIndexRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create a scalar index on a table Create a scalar index on a table column for faster filtering
   * operations. Supports scalar indexes (BTREE, BITMAP, LABEL_LIST, FTS, etc.). This is an alias
   * for CreateTableIndex specifically for scalar indexes. Index creation is handled asynchronously.
   * Use the &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to
   * monitor index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Scalar index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateTableScalarIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableScalarIndexResponse> createTableScalarIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableScalarIndex(id, createTableIndexRequest, delimiter, null);
  }

  /**
   * Create a scalar index on a table Create a scalar index on a table column for faster filtering
   * operations. Supports scalar indexes (BTREE, BITMAP, LABEL_LIST, FTS, etc.). This is an alias
   * for CreateTableIndex specifically for scalar indexes. Index creation is handled asynchronously.
   * Use the &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to
   * monitor index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Scalar index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateTableScalarIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableScalarIndexResponse> createTableScalarIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createTableScalarIndexWithHttpInfo(id, createTableIndexRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create a scalar index on a table Create a scalar index on a table column for faster filtering
   * operations. Supports scalar indexes (BTREE, BITMAP, LABEL_LIST, FTS, etc.). This is an alias
   * for CreateTableIndex specifically for scalar indexes. Index creation is handled asynchronously.
   * Use the &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to
   * monitor index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Scalar index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableScalarIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableScalarIndexResponse>>
      createTableScalarIndexWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return createTableScalarIndexWithHttpInfo(id, createTableIndexRequest, delimiter, null);
  }

  /**
   * Create a scalar index on a table Create a scalar index on a table column for faster filtering
   * operations. Supports scalar indexes (BTREE, BITMAP, LABEL_LIST, FTS, etc.). This is an alias
   * for CreateTableIndex specifically for scalar indexes. Index creation is handled asynchronously.
   * Use the &#x60;ListTableIndices&#x60; and &#x60;DescribeTableIndexStats&#x60; operations to
   * monitor index creation progress.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableIndexRequest Scalar index creation request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableScalarIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableScalarIndexResponse>>
      createTableScalarIndexWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createTableScalarIndexRequestBuilder(id, createTableIndexRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createTableScalarIndex", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateTableScalarIndexResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateTableScalarIndexResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<CreateTableScalarIndexResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateTableScalarIndexResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createTableScalarIndexRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableIndexRequest createTableIndexRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createTableScalarIndex");
    }
    // verify the required parameter 'createTableIndexRequest' is set
    if (createTableIndexRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createTableIndexRequest' when calling createTableScalarIndex");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/create_scalar_index".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createTableIndexRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create a new tag Create a new tag for table &#x60;id&#x60; that points to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableTagResponse> createTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableTagRequest createTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableTag(id, createTableTagRequest, delimiter, null);
  }

  /**
   * Create a new tag Create a new tag for table &#x60;id&#x60; that points to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableTagResponse> createTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableTagRequest createTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createTableTagWithHttpInfo(id, createTableTagRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create a new tag Create a new tag for table &#x60;id&#x60; that points to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableTagResponse>> createTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableTagRequest createTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableTagWithHttpInfo(id, createTableTagRequest, delimiter, null);
  }

  /**
   * Create a new tag Create a new tag for table &#x60;id&#x60; that points to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableTagResponse>> createTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableTagRequest createTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createTableTagRequestBuilder(id, createTableTagRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createTableTag", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateTableTagResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateTableTagResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateTableTagResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateTableTagResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createTableTagRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableTagRequest createTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createTableTag");
    }
    // verify the required parameter 'createTableTagRequest' is set
    if (createTableTagRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createTableTagRequest' when calling createTableTag");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/tags/create".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createTableTagRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Create a new table version Create a new version entry for table &#x60;id&#x60;. This operation
   * supports &#x60;put_if_not_exists&#x60; semantics. The operation will fail with 409 Conflict if
   * the version already exists.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateTableVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableVersionResponse> createTableVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableVersionRequest createTableVersionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableVersion(id, createTableVersionRequest, delimiter, null);
  }

  /**
   * Create a new table version Create a new version entry for table &#x60;id&#x60;. This operation
   * supports &#x60;put_if_not_exists&#x60; semantics. The operation will fail with 409 Conflict if
   * the version already exists.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateTableVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateTableVersionResponse> createTableVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableVersionRequest createTableVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createTableVersionWithHttpInfo(id, createTableVersionRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create a new table version Create a new version entry for table &#x60;id&#x60;. This operation
   * supports &#x60;put_if_not_exists&#x60; semantics. The operation will fail with 409 Conflict if
   * the version already exists.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableVersionResponse>> createTableVersionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableVersionRequest createTableVersionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createTableVersionWithHttpInfo(id, createTableVersionRequest, delimiter, null);
  }

  /**
   * Create a new table version Create a new version entry for table &#x60;id&#x60;. This operation
   * supports &#x60;put_if_not_exists&#x60; semantics. The operation will fail with 409 Conflict if
   * the version already exists.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateTableVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateTableVersionResponse>> createTableVersionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableVersionRequest createTableVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createTableVersionRequestBuilder(id, createTableVersionRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("createTableVersion", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateTableVersionResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateTableVersionResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateTableVersionResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateTableVersionResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder createTableVersionRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateTableVersionRequest createTableVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createTableVersion");
    }
    // verify the required parameter 'createTableVersionRequest' is set
    if (createTableVersionRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createTableVersionRequest' when calling createTableVersion");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/version/create".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createTableVersionRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Declare a table Declare a table with the given name without touching storage. This is a
   * metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param declareTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DeclareTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeclareTableResponse> declareTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeclareTableRequest declareTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return declareTable(id, declareTableRequest, delimiter, null);
  }

  /**
   * Declare a table Declare a table with the given name without touching storage. This is a
   * metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param declareTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DeclareTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeclareTableResponse> declareTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeclareTableRequest declareTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return declareTableWithHttpInfo(id, declareTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Declare a table Declare a table with the given name without touching storage. This is a
   * metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param declareTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DeclareTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeclareTableResponse>> declareTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeclareTableRequest declareTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return declareTableWithHttpInfo(id, declareTableRequest, delimiter, null);
  }

  /**
   * Declare a table Declare a table with the given name without touching storage. This is a
   * metadata-only operation that records the table existence and sets up aspects like access
   * control. For DirectoryNamespace implementation, this creates a &#x60;.lance-reserved&#x60; file
   * in the table directory to mark the table&#39;s existence without creating actual Lance data
   * files.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param declareTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DeclareTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeclareTableResponse>> declareTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeclareTableRequest declareTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          declareTableRequestBuilder(id, declareTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("declareTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DeclareTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DeclareTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DeclareTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DeclareTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder declareTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeclareTableRequest declareTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling declareTable");
    }
    // verify the required parameter 'declareTableRequest' is set
    if (declareTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'declareTableRequest' when calling declareTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/declare".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(declareTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Delete rows from a table Delete rows from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteFromTableRequest Delete request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DeleteFromTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeleteFromTableResponse> deleteFromTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteFromTableRequest deleteFromTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deleteFromTable(id, deleteFromTableRequest, delimiter, null);
  }

  /**
   * Delete rows from a table Delete rows from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteFromTableRequest Delete request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DeleteFromTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeleteFromTableResponse> deleteFromTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteFromTableRequest deleteFromTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return deleteFromTableWithHttpInfo(id, deleteFromTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Delete rows from a table Delete rows from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteFromTableRequest Delete request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DeleteFromTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeleteFromTableResponse>> deleteFromTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteFromTableRequest deleteFromTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deleteFromTableWithHttpInfo(id, deleteFromTableRequest, delimiter, null);
  }

  /**
   * Delete rows from a table Delete rows from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteFromTableRequest Delete request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DeleteFromTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeleteFromTableResponse>> deleteFromTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteFromTableRequest deleteFromTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          deleteFromTableRequestBuilder(id, deleteFromTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("deleteFromTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DeleteFromTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DeleteFromTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DeleteFromTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DeleteFromTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder deleteFromTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteFromTableRequest deleteFromTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling deleteFromTable");
    }
    // verify the required parameter 'deleteFromTableRequest' is set
    if (deleteFromTableRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'deleteFromTableRequest' when calling deleteFromTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/delete".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(deleteFromTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Delete a tag Delete an existing tag from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DeleteTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeleteTableTagResponse> deleteTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteTableTagRequest deleteTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deleteTableTag(id, deleteTableTagRequest, delimiter, null);
  }

  /**
   * Delete a tag Delete an existing tag from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DeleteTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeleteTableTagResponse> deleteTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteTableTagRequest deleteTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return deleteTableTagWithHttpInfo(id, deleteTableTagRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Delete a tag Delete an existing tag from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DeleteTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeleteTableTagResponse>> deleteTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteTableTagRequest deleteTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deleteTableTagWithHttpInfo(id, deleteTableTagRequest, delimiter, null);
  }

  /**
   * Delete a tag Delete an existing tag from table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deleteTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DeleteTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeleteTableTagResponse>> deleteTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteTableTagRequest deleteTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          deleteTableTagRequestBuilder(id, deleteTableTagRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("deleteTableTag", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DeleteTableTagResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DeleteTableTagResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DeleteTableTagResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DeleteTableTagResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder deleteTableTagRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeleteTableTagRequest deleteTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling deleteTableTag");
    }
    // verify the required parameter 'deleteTableTagRequest' is set
    if (deleteTableTagRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'deleteTableTagRequest' when calling deleteTableTag");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/tags/delete".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(deleteTableTagRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Deregister a table Deregister table &#x60;id&#x60; from its namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deregisterTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DeregisterTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeregisterTableResponse> deregisterTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeregisterTableRequest deregisterTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deregisterTable(id, deregisterTableRequest, delimiter, null);
  }

  /**
   * Deregister a table Deregister table &#x60;id&#x60; from its namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deregisterTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DeregisterTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DeregisterTableResponse> deregisterTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeregisterTableRequest deregisterTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return deregisterTableWithHttpInfo(id, deregisterTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Deregister a table Deregister table &#x60;id&#x60; from its namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deregisterTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DeregisterTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeregisterTableResponse>> deregisterTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeregisterTableRequest deregisterTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return deregisterTableWithHttpInfo(id, deregisterTableRequest, delimiter, null);
  }

  /**
   * Deregister a table Deregister table &#x60;id&#x60; from its namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param deregisterTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DeregisterTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DeregisterTableResponse>> deregisterTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeregisterTableRequest deregisterTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          deregisterTableRequestBuilder(id, deregisterTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("deregisterTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DeregisterTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DeregisterTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DeregisterTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DeregisterTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder deregisterTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DeregisterTableRequest deregisterTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling deregisterTable");
    }
    // verify the required parameter 'deregisterTableRequest' is set
    if (deregisterTableRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'deregisterTableRequest' when calling deregisterTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/deregister".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(deregisterTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Describe information of a table Describe the detailed information for table &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace passes &#x60;with_table_uri&#x60; and
   * &#x60;load_detailed_metadata&#x60; as query parameters instead of in the request body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param withTableUri Whether to include the table URI in the response (optional, default to
   *     false)
   * @param loadDetailedMetadata Whether to load detailed metadata that requires opening the
   *     dataset. When false (default), only &#x60;location&#x60; is required in the response. When
   *     true, the response includes additional metadata such as &#x60;version&#x60;,
   *     &#x60;schema&#x60;, and &#x60;stats&#x60;. (optional, default to false)
   * @return CompletableFuture&lt;DescribeTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableResponse> describeTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableRequest describeTableRequest,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean withTableUri,
      @javax.annotation.Nullable Boolean loadDetailedMetadata)
      throws ApiException {
    return describeTable(
        id, describeTableRequest, delimiter, withTableUri, loadDetailedMetadata, null);
  }

  /**
   * Describe information of a table Describe the detailed information for table &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace passes &#x60;with_table_uri&#x60; and
   * &#x60;load_detailed_metadata&#x60; as query parameters instead of in the request body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param withTableUri Whether to include the table URI in the response (optional, default to
   *     false)
   * @param loadDetailedMetadata Whether to load detailed metadata that requires opening the
   *     dataset. When false (default), only &#x60;location&#x60; is required in the response. When
   *     true, the response includes additional metadata such as &#x60;version&#x60;,
   *     &#x60;schema&#x60;, and &#x60;stats&#x60;. (optional, default to false)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DescribeTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableResponse> describeTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableRequest describeTableRequest,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean withTableUri,
      @javax.annotation.Nullable Boolean loadDetailedMetadata,
      Map<String, String> headers)
      throws ApiException {
    try {
      return describeTableWithHttpInfo(
              id, describeTableRequest, delimiter, withTableUri, loadDetailedMetadata, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Describe information of a table Describe the detailed information for table &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace passes &#x60;with_table_uri&#x60; and
   * &#x60;load_detailed_metadata&#x60; as query parameters instead of in the request body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param withTableUri Whether to include the table URI in the response (optional, default to
   *     false)
   * @param loadDetailedMetadata Whether to load detailed metadata that requires opening the
   *     dataset. When false (default), only &#x60;location&#x60; is required in the response. When
   *     true, the response includes additional metadata such as &#x60;version&#x60;,
   *     &#x60;schema&#x60;, and &#x60;stats&#x60;. (optional, default to false)
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableResponse>> describeTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableRequest describeTableRequest,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean withTableUri,
      @javax.annotation.Nullable Boolean loadDetailedMetadata)
      throws ApiException {
    return describeTableWithHttpInfo(
        id, describeTableRequest, delimiter, withTableUri, loadDetailedMetadata, null);
  }

  /**
   * Describe information of a table Describe the detailed information for table &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace passes &#x60;with_table_uri&#x60; and
   * &#x60;load_detailed_metadata&#x60; as query parameters instead of in the request body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param withTableUri Whether to include the table URI in the response (optional, default to
   *     false)
   * @param loadDetailedMetadata Whether to load detailed metadata that requires opening the
   *     dataset. When false (default), only &#x60;location&#x60; is required in the response. When
   *     true, the response includes additional metadata such as &#x60;version&#x60;,
   *     &#x60;schema&#x60;, and &#x60;stats&#x60;. (optional, default to false)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableResponse>> describeTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableRequest describeTableRequest,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean withTableUri,
      @javax.annotation.Nullable Boolean loadDetailedMetadata,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          describeTableRequestBuilder(
              id, describeTableRequest, delimiter, withTableUri, loadDetailedMetadata, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("describeTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DescribeTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DescribeTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DescribeTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DescribeTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder describeTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableRequest describeTableRequest,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean withTableUri,
      @javax.annotation.Nullable Boolean loadDetailedMetadata,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling describeTable");
    }
    // verify the required parameter 'describeTableRequest' is set
    if (describeTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'describeTableRequest' when calling describeTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/describe".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "with_table_uri";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("with_table_uri", withTableUri));
    localVarQueryParameterBaseName = "load_detailed_metadata";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs("load_detailed_metadata", loadDetailedMetadata));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(describeTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Get table index statistics Get statistics for a specific index on a table. Returns information
   * about the index type, distance type (for vector indices), and row counts.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to get stats for (required)
   * @param describeTableIndexStatsRequest Index stats request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DescribeTableIndexStatsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableIndexStatsResponse> describeTableIndexStats(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nonnull DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return describeTableIndexStats(id, indexName, describeTableIndexStatsRequest, delimiter, null);
  }

  /**
   * Get table index statistics Get statistics for a specific index on a table. Returns information
   * about the index type, distance type (for vector indices), and row counts.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to get stats for (required)
   * @param describeTableIndexStatsRequest Index stats request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DescribeTableIndexStatsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableIndexStatsResponse> describeTableIndexStats(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nonnull DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return describeTableIndexStatsWithHttpInfo(
              id, indexName, describeTableIndexStatsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Get table index statistics Get statistics for a specific index on a table. Returns information
   * about the index type, distance type (for vector indices), and row counts.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to get stats for (required)
   * @param describeTableIndexStatsRequest Index stats request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableIndexStatsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableIndexStatsResponse>>
      describeTableIndexStatsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull String indexName,
          @javax.annotation.Nonnull DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return describeTableIndexStatsWithHttpInfo(
        id, indexName, describeTableIndexStatsRequest, delimiter, null);
  }

  /**
   * Get table index statistics Get statistics for a specific index on a table. Returns information
   * about the index type, distance type (for vector indices), and row counts.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to get stats for (required)
   * @param describeTableIndexStatsRequest Index stats request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableIndexStatsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableIndexStatsResponse>>
      describeTableIndexStatsWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull String indexName,
          @javax.annotation.Nonnull DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          describeTableIndexStatsRequestBuilder(
              id, indexName, describeTableIndexStatsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("describeTableIndexStats", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DescribeTableIndexStatsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DescribeTableIndexStatsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody,
                                new TypeReference<DescribeTableIndexStatsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DescribeTableIndexStatsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder describeTableIndexStatsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nonnull DescribeTableIndexStatsRequest describeTableIndexStatsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling describeTableIndexStats");
    }
    // verify the required parameter 'indexName' is set
    if (indexName == null) {
      throw new ApiException(
          400, "Missing the required parameter 'indexName' when calling describeTableIndexStats");
    }
    // verify the required parameter 'describeTableIndexStatsRequest' is set
    if (describeTableIndexStatsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'describeTableIndexStatsRequest' when calling describeTableIndexStats");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/index/{index_name}/stats"
            .replace("{id}", ApiClient.urlEncode(id.toString()))
            .replace("{index_name}", ApiClient.urlEncode(indexName.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(describeTableIndexStatsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Describe a specific table version Describe the detailed information for a specific version of
   * table &#x60;id&#x60;. Returns the manifest path and metadata for the specified version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DescribeTableVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableVersionResponse> describeTableVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableVersionRequest describeTableVersionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return describeTableVersion(id, describeTableVersionRequest, delimiter, null);
  }

  /**
   * Describe a specific table version Describe the detailed information for a specific version of
   * table &#x60;id&#x60;. Returns the manifest path and metadata for the specified version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DescribeTableVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTableVersionResponse> describeTableVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableVersionRequest describeTableVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return describeTableVersionWithHttpInfo(id, describeTableVersionRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Describe a specific table version Describe the detailed information for a specific version of
   * table &#x60;id&#x60;. Returns the manifest path and metadata for the specified version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableVersionResponse>>
      describeTableVersionWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull DescribeTableVersionRequest describeTableVersionRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return describeTableVersionWithHttpInfo(id, describeTableVersionRequest, delimiter, null);
  }

  /**
   * Describe a specific table version Describe the detailed information for a specific version of
   * table &#x60;id&#x60;. Returns the manifest path and metadata for the specified version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTableVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTableVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTableVersionResponse>>
      describeTableVersionWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull DescribeTableVersionRequest describeTableVersionRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          describeTableVersionRequestBuilder(id, describeTableVersionRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("describeTableVersion", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DescribeTableVersionResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DescribeTableVersionResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DescribeTableVersionResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DescribeTableVersionResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder describeTableVersionRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTableVersionRequest describeTableVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling describeTableVersion");
    }
    // verify the required parameter 'describeTableVersionRequest' is set
    if (describeTableVersionRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'describeTableVersionRequest' when calling describeTableVersion");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/version/describe".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(describeTableVersionRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Drop a table Drop table &#x60;id&#x60; and delete its data. REST NAMESPACE ONLY REST namespace
   * does not use a request body for this operation. The &#x60;DropTableRequest&#x60; information is
   * passed in the following way: - &#x60;id&#x60;: pass through path parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DropTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropTableResponse> dropTable(
      @javax.annotation.Nonnull String id, @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropTable(id, delimiter, null);
  }

  /**
   * Drop a table Drop table &#x60;id&#x60; and delete its data. REST NAMESPACE ONLY REST namespace
   * does not use a request body for this operation. The &#x60;DropTableRequest&#x60; information is
   * passed in the following way: - &#x60;id&#x60;: pass through path parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DropTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropTableResponse> dropTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return dropTableWithHttpInfo(id, delimiter, headers).thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Drop a table Drop table &#x60;id&#x60; and delete its data. REST NAMESPACE ONLY REST namespace
   * does not use a request body for this operation. The &#x60;DropTableRequest&#x60; information is
   * passed in the following way: - &#x60;id&#x60;: pass through path parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DropTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropTableResponse>> dropTableWithHttpInfo(
      @javax.annotation.Nonnull String id, @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropTableWithHttpInfo(id, delimiter, null);
  }

  /**
   * Drop a table Drop table &#x60;id&#x60; and delete its data. REST NAMESPACE ONLY REST namespace
   * does not use a request body for this operation. The &#x60;DropTableRequest&#x60; information is
   * passed in the following way: - &#x60;id&#x60;: pass through path parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DropTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropTableResponse>> dropTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder = dropTableRequestBuilder(id, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("dropTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DropTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DropTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DropTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DropTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder dropTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling dropTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/v1/table/{id}/drop".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Drop a specific index Drop the specified index from table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace does not use a request body for this operation. The
   * &#x60;DropTableIndexRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;index_name&#x60;: pass through path
   * parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to drop (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DropTableIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropTableIndexResponse> dropTableIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropTableIndex(id, indexName, delimiter, null);
  }

  /**
   * Drop a specific index Drop the specified index from table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace does not use a request body for this operation. The
   * &#x60;DropTableIndexRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;index_name&#x60;: pass through path
   * parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to drop (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DropTableIndexResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropTableIndexResponse> dropTableIndex(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return dropTableIndexWithHttpInfo(id, indexName, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Drop a specific index Drop the specified index from table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace does not use a request body for this operation. The
   * &#x60;DropTableIndexRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;index_name&#x60;: pass through path
   * parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to drop (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DropTableIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropTableIndexResponse>> dropTableIndexWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropTableIndexWithHttpInfo(id, indexName, delimiter, null);
  }

  /**
   * Drop a specific index Drop the specified index from table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace does not use a request body for this operation. The
   * &#x60;DropTableIndexRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;index_name&#x60;: pass through path
   * parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param indexName Name of the index to drop (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DropTableIndexResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropTableIndexResponse>> dropTableIndexWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          dropTableIndexRequestBuilder(id, indexName, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("dropTableIndex", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DropTableIndexResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DropTableIndexResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DropTableIndexResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DropTableIndexResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder dropTableIndexRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String indexName,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling dropTableIndex");
    }
    // verify the required parameter 'indexName' is set
    if (indexName == null) {
      throw new ApiException(
          400, "Missing the required parameter 'indexName' when calling dropTableIndex");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/index/{index_name}/drop"
            .replace("{id}", ApiClient.urlEncode(id.toString()))
            .replace("{index_name}", ApiClient.urlEncode(indexName.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Get query execution plan explanation Get the query execution plan for a query against table
   * &#x60;id&#x60;. Returns a human-readable explanation of how the query will be executed. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;ExplainTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param explainTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;String&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<String> explainTableQueryPlan(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ExplainTableQueryPlanRequest explainTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return explainTableQueryPlan(id, explainTableQueryPlanRequest, delimiter, null);
  }

  /**
   * Get query execution plan explanation Get the query execution plan for a query against table
   * &#x60;id&#x60;. Returns a human-readable explanation of how the query will be executed. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;ExplainTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param explainTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;String&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<String> explainTableQueryPlan(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ExplainTableQueryPlanRequest explainTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return explainTableQueryPlanWithHttpInfo(id, explainTableQueryPlanRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Get query execution plan explanation Get the query execution plan for a query against table
   * &#x60;id&#x60;. Returns a human-readable explanation of how the query will be executed. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;ExplainTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param explainTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<String>> explainTableQueryPlanWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ExplainTableQueryPlanRequest explainTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return explainTableQueryPlanWithHttpInfo(id, explainTableQueryPlanRequest, delimiter, null);
  }

  /**
   * Get query execution plan explanation Get the query execution plan for a query against table
   * &#x60;id&#x60;. Returns a human-readable explanation of how the query will be executed. REST
   * NAMESPACE ONLY REST namespace returns the response as a plain string instead of the
   * &#x60;ExplainTableQueryPlanResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param explainTableQueryPlanRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<String>> explainTableQueryPlanWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ExplainTableQueryPlanRequest explainTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          explainTableQueryPlanRequestBuilder(id, explainTableQueryPlanRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("explainTableQueryPlan", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<String>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    String responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<String>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<String>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder explainTableQueryPlanRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ExplainTableQueryPlanRequest explainTableQueryPlanRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling explainTableQueryPlan");
    }
    // verify the required parameter 'explainTableQueryPlanRequest' is set
    if (explainTableQueryPlanRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'explainTableQueryPlanRequest' when calling explainTableQueryPlan");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/explain_plan".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody =
          memberVarObjectMapper.writeValueAsBytes(explainTableQueryPlanRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Get table statistics Get statistics for table &#x60;id&#x60;, including row counts, data sizes,
   * and column statistics.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableStatsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;GetTableStatsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<GetTableStatsResponse> getTableStats(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableStatsRequest getTableStatsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return getTableStats(id, getTableStatsRequest, delimiter, null);
  }

  /**
   * Get table statistics Get statistics for table &#x60;id&#x60;, including row counts, data sizes,
   * and column statistics.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableStatsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;GetTableStatsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<GetTableStatsResponse> getTableStats(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableStatsRequest getTableStatsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return getTableStatsWithHttpInfo(id, getTableStatsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Get table statistics Get statistics for table &#x60;id&#x60;, including row counts, data sizes,
   * and column statistics.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableStatsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;GetTableStatsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<GetTableStatsResponse>> getTableStatsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableStatsRequest getTableStatsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return getTableStatsWithHttpInfo(id, getTableStatsRequest, delimiter, null);
  }

  /**
   * Get table statistics Get statistics for table &#x60;id&#x60;, including row counts, data sizes,
   * and column statistics.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableStatsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;GetTableStatsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<GetTableStatsResponse>> getTableStatsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableStatsRequest getTableStatsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          getTableStatsRequestBuilder(id, getTableStatsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("getTableStats", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<GetTableStatsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    GetTableStatsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<GetTableStatsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<GetTableStatsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder getTableStatsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableStatsRequest getTableStatsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling getTableStats");
    }
    // verify the required parameter 'getTableStatsRequest' is set
    if (getTableStatsRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'getTableStatsRequest' when calling getTableStats");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/stats".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(getTableStatsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Get version for a specific tag Get the version number that a specific tag points to for table
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableTagVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;GetTableTagVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<GetTableTagVersionResponse> getTableTagVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableTagVersionRequest getTableTagVersionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return getTableTagVersion(id, getTableTagVersionRequest, delimiter, null);
  }

  /**
   * Get version for a specific tag Get the version number that a specific tag points to for table
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableTagVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;GetTableTagVersionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<GetTableTagVersionResponse> getTableTagVersion(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableTagVersionRequest getTableTagVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return getTableTagVersionWithHttpInfo(id, getTableTagVersionRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Get version for a specific tag Get the version number that a specific tag points to for table
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableTagVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;GetTableTagVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<GetTableTagVersionResponse>> getTableTagVersionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableTagVersionRequest getTableTagVersionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return getTableTagVersionWithHttpInfo(id, getTableTagVersionRequest, delimiter, null);
  }

  /**
   * Get version for a specific tag Get the version number that a specific tag points to for table
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param getTableTagVersionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;GetTableTagVersionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<GetTableTagVersionResponse>> getTableTagVersionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableTagVersionRequest getTableTagVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          getTableTagVersionRequestBuilder(id, getTableTagVersionRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("getTableTagVersion", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<GetTableTagVersionResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    GetTableTagVersionResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<GetTableTagVersionResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<GetTableTagVersionResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder getTableTagVersionRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull GetTableTagVersionRequest getTableTagVersionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling getTableTagVersion");
    }
    // verify the required parameter 'getTableTagVersionRequest' is set
    if (getTableTagVersionRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'getTableTagVersionRequest' when calling getTableTagVersion");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/tags/version".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(getTableTagVersionRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Insert records into a table Insert new records into table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;InsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;: pass
   * through path parameter of the same name - &#x60;mode&#x60;: pass through query parameter of the
   * same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC stream containing the records to insert (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode How the insert should behave. Case insensitive, supports both PascalCase and
   *     snake_case. Valid values are: - Append (default): insert data to the existing table -
   *     Overwrite: remove all data in the table and then insert data to it (optional, default to
   *     append)
   * @return CompletableFuture&lt;InsertIntoTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<InsertIntoTableResponse> insertIntoTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode)
      throws ApiException {
    return insertIntoTable(id, body, delimiter, mode, null);
  }

  /**
   * Insert records into a table Insert new records into table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;InsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;: pass
   * through path parameter of the same name - &#x60;mode&#x60;: pass through query parameter of the
   * same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC stream containing the records to insert (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode How the insert should behave. Case insensitive, supports both PascalCase and
   *     snake_case. Valid values are: - Append (default): insert data to the existing table -
   *     Overwrite: remove all data in the table and then insert data to it (optional, default to
   *     append)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;InsertIntoTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<InsertIntoTableResponse> insertIntoTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    try {
      return insertIntoTableWithHttpInfo(id, body, delimiter, mode, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Insert records into a table Insert new records into table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;InsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;: pass
   * through path parameter of the same name - &#x60;mode&#x60;: pass through query parameter of the
   * same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC stream containing the records to insert (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode How the insert should behave. Case insensitive, supports both PascalCase and
   *     snake_case. Valid values are: - Append (default): insert data to the existing table -
   *     Overwrite: remove all data in the table and then insert data to it (optional, default to
   *     append)
   * @return CompletableFuture&lt;ApiResponse&lt;InsertIntoTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<InsertIntoTableResponse>> insertIntoTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode)
      throws ApiException {
    return insertIntoTableWithHttpInfo(id, body, delimiter, mode, null);
  }

  /**
   * Insert records into a table Insert new records into table &#x60;id&#x60;. REST NAMESPACE ONLY
   * REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;InsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;: pass
   * through path parameter of the same name - &#x60;mode&#x60;: pass through query parameter of the
   * same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param body Arrow IPC stream containing the records to insert (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param mode How the insert should behave. Case insensitive, supports both PascalCase and
   *     snake_case. Valid values are: - Append (default): insert data to the existing table -
   *     Overwrite: remove all data in the table and then insert data to it (optional, default to
   *     append)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;InsertIntoTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<InsertIntoTableResponse>> insertIntoTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          insertIntoTableRequestBuilder(id, body, delimiter, mode, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("insertIntoTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<InsertIntoTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    InsertIntoTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<InsertIntoTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<InsertIntoTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder insertIntoTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String mode,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling insertIntoTable");
    }
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(
          400, "Missing the required parameter 'body' when calling insertIntoTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/insert".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "mode";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("mode", mode));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/vnd.apache.arrow.stream");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(body);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * List all tables List all tables across all namespaces. REST NAMESPACE ONLY REST namespace uses
   * GET to perform this operation without a request body. It passes in the
   * &#x60;ListAllTablesRequest&#x60; information in the following way: - &#x60;page_token&#x60;:
   * pass through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter
   * of the same name - &#x60;delimiter&#x60;: pass through query parameter of the same name
   *
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ListTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTablesResponse> listAllTables(
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listAllTables(delimiter, pageToken, limit, null);
  }

  /**
   * List all tables List all tables across all namespaces. REST NAMESPACE ONLY REST namespace uses
   * GET to perform this operation without a request body. It passes in the
   * &#x60;ListAllTablesRequest&#x60; information in the following way: - &#x60;page_token&#x60;:
   * pass through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter
   * of the same name - &#x60;delimiter&#x60;: pass through query parameter of the same name
   *
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ListTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTablesResponse> listAllTables(
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listAllTablesWithHttpInfo(delimiter, pageToken, limit, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List all tables List all tables across all namespaces. REST NAMESPACE ONLY REST namespace uses
   * GET to perform this operation without a request body. It passes in the
   * &#x60;ListAllTablesRequest&#x60; information in the following way: - &#x60;page_token&#x60;:
   * pass through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter
   * of the same name - &#x60;delimiter&#x60;: pass through query parameter of the same name
   *
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;ListTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTablesResponse>> listAllTablesWithHttpInfo(
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listAllTablesWithHttpInfo(delimiter, pageToken, limit, null);
  }

  /**
   * List all tables List all tables across all namespaces. REST NAMESPACE ONLY REST namespace uses
   * GET to perform this operation without a request body. It passes in the
   * &#x60;ListAllTablesRequest&#x60; information in the following way: - &#x60;page_token&#x60;:
   * pass through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter
   * of the same name - &#x60;delimiter&#x60;: pass through query parameter of the same name
   *
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;ListTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTablesResponse>> listAllTablesWithHttpInfo(
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listAllTablesRequestBuilder(delimiter, pageToken, limit, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("listAllTables", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListTablesResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListTablesResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListTablesResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListTablesResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder listAllTablesRequestBuilder(
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath = "/v1/table";

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "page_token";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("page_token", pageToken));
    localVarQueryParameterBaseName = "limit";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("limit", limit));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * List indexes on a table List all indices created on a table. Returns information about each
   * index including name, columns, status, and UUID.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param listTableIndicesRequest Index list request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ListTableIndicesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableIndicesResponse> listTableIndices(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ListTableIndicesRequest listTableIndicesRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return listTableIndices(id, listTableIndicesRequest, delimiter, null);
  }

  /**
   * List indexes on a table List all indices created on a table. Returns information about each
   * index including name, columns, status, and UUID.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param listTableIndicesRequest Index list request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ListTableIndicesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableIndicesResponse> listTableIndices(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ListTableIndicesRequest listTableIndicesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listTableIndicesWithHttpInfo(id, listTableIndicesRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List indexes on a table List all indices created on a table. Returns information about each
   * index including name, columns, status, and UUID.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param listTableIndicesRequest Index list request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableIndicesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableIndicesResponse>> listTableIndicesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ListTableIndicesRequest listTableIndicesRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return listTableIndicesWithHttpInfo(id, listTableIndicesRequest, delimiter, null);
  }

  /**
   * List indexes on a table List all indices created on a table. Returns information about each
   * index including name, columns, status, and UUID.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param listTableIndicesRequest Index list request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableIndicesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableIndicesResponse>> listTableIndicesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ListTableIndicesRequest listTableIndicesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listTableIndicesRequestBuilder(id, listTableIndicesRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("listTableIndices", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListTableIndicesResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListTableIndicesResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListTableIndicesResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListTableIndicesResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder listTableIndicesRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull ListTableIndicesRequest listTableIndicesRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling listTableIndices");
    }
    // verify the required parameter 'listTableIndicesRequest' is set
    if (listTableIndicesRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'listTableIndicesRequest' when calling listTableIndices");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/index/list".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(listTableIndicesRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * List all tags for a table List all tags that have been created for table &#x60;id&#x60;.
   * Returns a map of tag names to their corresponding version numbers and metadata. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableTagsRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;page_token&#x60;: pass through query
   * parameter of the same name - &#x60;limit&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ListTableTagsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableTagsResponse> listTableTags(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listTableTags(id, delimiter, pageToken, limit, null);
  }

  /**
   * List all tags for a table List all tags that have been created for table &#x60;id&#x60;.
   * Returns a map of tag names to their corresponding version numbers and metadata. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableTagsRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;page_token&#x60;: pass through query
   * parameter of the same name - &#x60;limit&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ListTableTagsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableTagsResponse> listTableTags(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listTableTagsWithHttpInfo(id, delimiter, pageToken, limit, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List all tags for a table List all tags that have been created for table &#x60;id&#x60;.
   * Returns a map of tag names to their corresponding version numbers and metadata. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableTagsRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;page_token&#x60;: pass through query
   * parameter of the same name - &#x60;limit&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableTagsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableTagsResponse>> listTableTagsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listTableTagsWithHttpInfo(id, delimiter, pageToken, limit, null);
  }

  /**
   * List all tags for a table List all tags that have been created for table &#x60;id&#x60;.
   * Returns a map of tag names to their corresponding version numbers and metadata. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableTagsRequest&#x60; information is passed in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;page_token&#x60;: pass through query
   * parameter of the same name - &#x60;limit&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableTagsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableTagsResponse>> listTableTagsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listTableTagsRequestBuilder(id, delimiter, pageToken, limit, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("listTableTags", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListTableTagsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListTableTagsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListTableTagsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListTableTagsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder listTableTagsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling listTableTags");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/tags/list".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "page_token";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("page_token", pageToken));
    localVarQueryParameterBaseName = "limit";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("limit", limit));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * List all versions of a table List all versions (commits) of table &#x60;id&#x60; with their
   * metadata. Use &#x60;descending&#x3D;true&#x60; to guarantee versions are returned in descending
   * order (latest to oldest). Otherwise, the ordering is implementation-defined. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableVersionsRequest&#x60; information is passed in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name - &#x60;descending&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param descending When true, versions are guaranteed to be returned in descending order (latest
   *     to oldest). When false or not specified, the ordering is implementation-defined. (optional)
   * @return CompletableFuture&lt;ListTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableVersionsResponse> listTableVersions(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      @javax.annotation.Nullable Boolean descending)
      throws ApiException {
    return listTableVersions(id, delimiter, pageToken, limit, descending, null);
  }

  /**
   * List all versions of a table List all versions (commits) of table &#x60;id&#x60; with their
   * metadata. Use &#x60;descending&#x3D;true&#x60; to guarantee versions are returned in descending
   * order (latest to oldest). Otherwise, the ordering is implementation-defined. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableVersionsRequest&#x60; information is passed in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name - &#x60;descending&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param descending When true, versions are guaranteed to be returned in descending order (latest
   *     to oldest). When false or not specified, the ordering is implementation-defined. (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ListTableVersionsResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTableVersionsResponse> listTableVersions(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      @javax.annotation.Nullable Boolean descending,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listTableVersionsWithHttpInfo(id, delimiter, pageToken, limit, descending, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List all versions of a table List all versions (commits) of table &#x60;id&#x60; with their
   * metadata. Use &#x60;descending&#x3D;true&#x60; to guarantee versions are returned in descending
   * order (latest to oldest). Otherwise, the ordering is implementation-defined. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableVersionsRequest&#x60; information is passed in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name - &#x60;descending&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param descending When true, versions are guaranteed to be returned in descending order (latest
   *     to oldest). When false or not specified, the ordering is implementation-defined. (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableVersionsResponse>> listTableVersionsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      @javax.annotation.Nullable Boolean descending)
      throws ApiException {
    return listTableVersionsWithHttpInfo(id, delimiter, pageToken, limit, descending, null);
  }

  /**
   * List all versions of a table List all versions (commits) of table &#x60;id&#x60; with their
   * metadata. Use &#x60;descending&#x3D;true&#x60; to guarantee versions are returned in descending
   * order (latest to oldest). Otherwise, the ordering is implementation-defined. REST NAMESPACE
   * ONLY REST namespace does not use a request body for this operation. The
   * &#x60;ListTableVersionsRequest&#x60; information is passed in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name - &#x60;descending&#x60;: pass through query parameter of the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param descending When true, versions are guaranteed to be returned in descending order (latest
   *     to oldest). When false or not specified, the ordering is implementation-defined. (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;ListTableVersionsResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTableVersionsResponse>> listTableVersionsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      @javax.annotation.Nullable Boolean descending,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listTableVersionsRequestBuilder(id, delimiter, pageToken, limit, descending, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("listTableVersions", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListTableVersionsResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListTableVersionsResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListTableVersionsResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListTableVersionsResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder listTableVersionsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      @javax.annotation.Nullable Boolean descending,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling listTableVersions");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/version/list".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "page_token";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("page_token", pageToken));
    localVarQueryParameterBaseName = "limit";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("limit", limit));
    localVarQueryParameterBaseName = "descending";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("descending", descending));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("POST", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * List tables in a namespace List all child table names of the parent namespace &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body.
   * It passes in the &#x60;ListTablesRequest&#x60; information in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ListTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTablesResponse> listTables(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listTables(id, delimiter, pageToken, limit, null);
  }

  /**
   * List tables in a namespace List all child table names of the parent namespace &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body.
   * It passes in the &#x60;ListTablesRequest&#x60; information in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ListTablesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListTablesResponse> listTables(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listTablesWithHttpInfo(id, delimiter, pageToken, limit, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List tables in a namespace List all child table names of the parent namespace &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body.
   * It passes in the &#x60;ListTablesRequest&#x60; information in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;ListTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTablesResponse>> listTablesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listTablesWithHttpInfo(id, delimiter, pageToken, limit, null);
  }

  /**
   * List tables in a namespace List all child table names of the parent namespace &#x60;id&#x60;.
   * REST NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body.
   * It passes in the &#x60;ListTablesRequest&#x60; information in the following way: -
   * &#x60;id&#x60;: pass through path parameter of the same name - &#x60;page_token&#x60;: pass
   * through query parameter of the same name - &#x60;limit&#x60;: pass through query parameter of
   * the same name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param pageToken Pagination token from a previous request (optional)
   * @param limit Maximum number of items to return (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;ListTablesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListTablesResponse>> listTablesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listTablesRequestBuilder(id, delimiter, pageToken, limit, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("listTables", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListTablesResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListTablesResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListTablesResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListTablesResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder listTablesRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling listTables");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/table/list".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "page_token";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("page_token", pageToken));
    localVarQueryParameterBaseName = "limit";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("limit", limit));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Accept", "application/json");

    localVarRequestBuilder.method("GET", HttpRequest.BodyPublishers.noBody());
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Merge insert (upsert) records into a table Performs a merge insert (upsert) operation on table
   * &#x60;id&#x60;. This operation updates existing rows based on a matching column and inserts new
   * rows that don&#39;t match. It returns the number of rows inserted and updated. REST NAMESPACE
   * ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;MergeInsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;on&#x60;: pass through query parameter of
   * the same name - &#x60;when_matched_update_all&#x60;: pass through query parameter of the same
   * name - &#x60;when_matched_update_all_filt&#x60;: pass through query parameter of the same name
   * - &#x60;when_not_matched_insert_all&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete_filt&#x60;: pass through query parameter of the same
   * name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param on Column name to use for matching rows (required) (required)
   * @param body Arrow IPC stream containing the records to merge (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param whenMatchedUpdateAll Update all columns when rows match (optional, default to false)
   * @param whenMatchedUpdateAllFilt The row is updated (similar to UpdateAll) only for rows where
   *     the SQL expression evaluates to true (optional)
   * @param whenNotMatchedInsertAll Insert all columns when rows don&#39;t match (optional, default
   *     to false)
   * @param whenNotMatchedBySourceDelete Delete all rows from target table that don&#39;t match a
   *     row in the source table (optional, default to false)
   * @param whenNotMatchedBySourceDeleteFilt Delete rows from the target table if there is no match
   *     AND the SQL expression evaluates to true (optional)
   * @param timeout Timeout for the operation (e.g., \&quot;30s\&quot;, \&quot;5m\&quot;) (optional)
   * @param useIndex Whether to use index for matching rows (optional, default to false)
   * @return CompletableFuture&lt;MergeInsertIntoTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<MergeInsertIntoTableResponse> mergeInsertIntoTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String on,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean whenMatchedUpdateAll,
      @javax.annotation.Nullable String whenMatchedUpdateAllFilt,
      @javax.annotation.Nullable Boolean whenNotMatchedInsertAll,
      @javax.annotation.Nullable Boolean whenNotMatchedBySourceDelete,
      @javax.annotation.Nullable String whenNotMatchedBySourceDeleteFilt,
      @javax.annotation.Nullable String timeout,
      @javax.annotation.Nullable Boolean useIndex)
      throws ApiException {
    return mergeInsertIntoTable(
        id,
        on,
        body,
        delimiter,
        whenMatchedUpdateAll,
        whenMatchedUpdateAllFilt,
        whenNotMatchedInsertAll,
        whenNotMatchedBySourceDelete,
        whenNotMatchedBySourceDeleteFilt,
        timeout,
        useIndex,
        null);
  }

  /**
   * Merge insert (upsert) records into a table Performs a merge insert (upsert) operation on table
   * &#x60;id&#x60;. This operation updates existing rows based on a matching column and inserts new
   * rows that don&#39;t match. It returns the number of rows inserted and updated. REST NAMESPACE
   * ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;MergeInsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;on&#x60;: pass through query parameter of
   * the same name - &#x60;when_matched_update_all&#x60;: pass through query parameter of the same
   * name - &#x60;when_matched_update_all_filt&#x60;: pass through query parameter of the same name
   * - &#x60;when_not_matched_insert_all&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete_filt&#x60;: pass through query parameter of the same
   * name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param on Column name to use for matching rows (required) (required)
   * @param body Arrow IPC stream containing the records to merge (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param whenMatchedUpdateAll Update all columns when rows match (optional, default to false)
   * @param whenMatchedUpdateAllFilt The row is updated (similar to UpdateAll) only for rows where
   *     the SQL expression evaluates to true (optional)
   * @param whenNotMatchedInsertAll Insert all columns when rows don&#39;t match (optional, default
   *     to false)
   * @param whenNotMatchedBySourceDelete Delete all rows from target table that don&#39;t match a
   *     row in the source table (optional, default to false)
   * @param whenNotMatchedBySourceDeleteFilt Delete rows from the target table if there is no match
   *     AND the SQL expression evaluates to true (optional)
   * @param timeout Timeout for the operation (e.g., \&quot;30s\&quot;, \&quot;5m\&quot;) (optional)
   * @param useIndex Whether to use index for matching rows (optional, default to false)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;MergeInsertIntoTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<MergeInsertIntoTableResponse> mergeInsertIntoTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String on,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean whenMatchedUpdateAll,
      @javax.annotation.Nullable String whenMatchedUpdateAllFilt,
      @javax.annotation.Nullable Boolean whenNotMatchedInsertAll,
      @javax.annotation.Nullable Boolean whenNotMatchedBySourceDelete,
      @javax.annotation.Nullable String whenNotMatchedBySourceDeleteFilt,
      @javax.annotation.Nullable String timeout,
      @javax.annotation.Nullable Boolean useIndex,
      Map<String, String> headers)
      throws ApiException {
    try {
      return mergeInsertIntoTableWithHttpInfo(
              id,
              on,
              body,
              delimiter,
              whenMatchedUpdateAll,
              whenMatchedUpdateAllFilt,
              whenNotMatchedInsertAll,
              whenNotMatchedBySourceDelete,
              whenNotMatchedBySourceDeleteFilt,
              timeout,
              useIndex,
              headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Merge insert (upsert) records into a table Performs a merge insert (upsert) operation on table
   * &#x60;id&#x60;. This operation updates existing rows based on a matching column and inserts new
   * rows that don&#39;t match. It returns the number of rows inserted and updated. REST NAMESPACE
   * ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;MergeInsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;on&#x60;: pass through query parameter of
   * the same name - &#x60;when_matched_update_all&#x60;: pass through query parameter of the same
   * name - &#x60;when_matched_update_all_filt&#x60;: pass through query parameter of the same name
   * - &#x60;when_not_matched_insert_all&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete_filt&#x60;: pass through query parameter of the same
   * name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param on Column name to use for matching rows (required) (required)
   * @param body Arrow IPC stream containing the records to merge (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param whenMatchedUpdateAll Update all columns when rows match (optional, default to false)
   * @param whenMatchedUpdateAllFilt The row is updated (similar to UpdateAll) only for rows where
   *     the SQL expression evaluates to true (optional)
   * @param whenNotMatchedInsertAll Insert all columns when rows don&#39;t match (optional, default
   *     to false)
   * @param whenNotMatchedBySourceDelete Delete all rows from target table that don&#39;t match a
   *     row in the source table (optional, default to false)
   * @param whenNotMatchedBySourceDeleteFilt Delete rows from the target table if there is no match
   *     AND the SQL expression evaluates to true (optional)
   * @param timeout Timeout for the operation (e.g., \&quot;30s\&quot;, \&quot;5m\&quot;) (optional)
   * @param useIndex Whether to use index for matching rows (optional, default to false)
   * @return CompletableFuture&lt;ApiResponse&lt;MergeInsertIntoTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<MergeInsertIntoTableResponse>>
      mergeInsertIntoTableWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull String on,
          @javax.annotation.Nonnull byte[] body,
          @javax.annotation.Nullable String delimiter,
          @javax.annotation.Nullable Boolean whenMatchedUpdateAll,
          @javax.annotation.Nullable String whenMatchedUpdateAllFilt,
          @javax.annotation.Nullable Boolean whenNotMatchedInsertAll,
          @javax.annotation.Nullable Boolean whenNotMatchedBySourceDelete,
          @javax.annotation.Nullable String whenNotMatchedBySourceDeleteFilt,
          @javax.annotation.Nullable String timeout,
          @javax.annotation.Nullable Boolean useIndex)
          throws ApiException {
    return mergeInsertIntoTableWithHttpInfo(
        id,
        on,
        body,
        delimiter,
        whenMatchedUpdateAll,
        whenMatchedUpdateAllFilt,
        whenNotMatchedInsertAll,
        whenNotMatchedBySourceDelete,
        whenNotMatchedBySourceDeleteFilt,
        timeout,
        useIndex,
        null);
  }

  /**
   * Merge insert (upsert) records into a table Performs a merge insert (upsert) operation on table
   * &#x60;id&#x60;. This operation updates existing rows based on a matching column and inserts new
   * rows that don&#39;t match. It returns the number of rows inserted and updated. REST NAMESPACE
   * ONLY REST namespace uses Arrow IPC stream as the request body. It passes in the
   * &#x60;MergeInsertIntoTableRequest&#x60; information in the following way: - &#x60;id&#x60;:
   * pass through path parameter of the same name - &#x60;on&#x60;: pass through query parameter of
   * the same name - &#x60;when_matched_update_all&#x60;: pass through query parameter of the same
   * name - &#x60;when_matched_update_all_filt&#x60;: pass through query parameter of the same name
   * - &#x60;when_not_matched_insert_all&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete&#x60;: pass through query parameter of the same name -
   * &#x60;when_not_matched_by_source_delete_filt&#x60;: pass through query parameter of the same
   * name
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param on Column name to use for matching rows (required) (required)
   * @param body Arrow IPC stream containing the records to merge (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param whenMatchedUpdateAll Update all columns when rows match (optional, default to false)
   * @param whenMatchedUpdateAllFilt The row is updated (similar to UpdateAll) only for rows where
   *     the SQL expression evaluates to true (optional)
   * @param whenNotMatchedInsertAll Insert all columns when rows don&#39;t match (optional, default
   *     to false)
   * @param whenNotMatchedBySourceDelete Delete all rows from target table that don&#39;t match a
   *     row in the source table (optional, default to false)
   * @param whenNotMatchedBySourceDeleteFilt Delete rows from the target table if there is no match
   *     AND the SQL expression evaluates to true (optional)
   * @param timeout Timeout for the operation (e.g., \&quot;30s\&quot;, \&quot;5m\&quot;) (optional)
   * @param useIndex Whether to use index for matching rows (optional, default to false)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;MergeInsertIntoTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<MergeInsertIntoTableResponse>>
      mergeInsertIntoTableWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull String on,
          @javax.annotation.Nonnull byte[] body,
          @javax.annotation.Nullable String delimiter,
          @javax.annotation.Nullable Boolean whenMatchedUpdateAll,
          @javax.annotation.Nullable String whenMatchedUpdateAllFilt,
          @javax.annotation.Nullable Boolean whenNotMatchedInsertAll,
          @javax.annotation.Nullable Boolean whenNotMatchedBySourceDelete,
          @javax.annotation.Nullable String whenNotMatchedBySourceDeleteFilt,
          @javax.annotation.Nullable String timeout,
          @javax.annotation.Nullable Boolean useIndex,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          mergeInsertIntoTableRequestBuilder(
              id,
              on,
              body,
              delimiter,
              whenMatchedUpdateAll,
              whenMatchedUpdateAllFilt,
              whenNotMatchedInsertAll,
              whenNotMatchedBySourceDelete,
              whenNotMatchedBySourceDeleteFilt,
              timeout,
              useIndex,
              headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("mergeInsertIntoTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<MergeInsertIntoTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    MergeInsertIntoTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<MergeInsertIntoTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<MergeInsertIntoTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder mergeInsertIntoTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull String on,
      @javax.annotation.Nonnull byte[] body,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable Boolean whenMatchedUpdateAll,
      @javax.annotation.Nullable String whenMatchedUpdateAllFilt,
      @javax.annotation.Nullable Boolean whenNotMatchedInsertAll,
      @javax.annotation.Nullable Boolean whenNotMatchedBySourceDelete,
      @javax.annotation.Nullable String whenNotMatchedBySourceDeleteFilt,
      @javax.annotation.Nullable String timeout,
      @javax.annotation.Nullable Boolean useIndex,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling mergeInsertIntoTable");
    }
    // verify the required parameter 'on' is set
    if (on == null) {
      throw new ApiException(
          400, "Missing the required parameter 'on' when calling mergeInsertIntoTable");
    }
    // verify the required parameter 'body' is set
    if (body == null) {
      throw new ApiException(
          400, "Missing the required parameter 'body' when calling mergeInsertIntoTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/merge_insert".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));
    localVarQueryParameterBaseName = "on";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("on", on));
    localVarQueryParameterBaseName = "when_matched_update_all";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs("when_matched_update_all", whenMatchedUpdateAll));
    localVarQueryParameterBaseName = "when_matched_update_all_filt";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs("when_matched_update_all_filt", whenMatchedUpdateAllFilt));
    localVarQueryParameterBaseName = "when_not_matched_insert_all";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs("when_not_matched_insert_all", whenNotMatchedInsertAll));
    localVarQueryParameterBaseName = "when_not_matched_by_source_delete";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs(
            "when_not_matched_by_source_delete", whenNotMatchedBySourceDelete));
    localVarQueryParameterBaseName = "when_not_matched_by_source_delete_filt";
    localVarQueryParams.addAll(
        ApiClient.parameterToPairs(
            "when_not_matched_by_source_delete_filt", whenNotMatchedBySourceDeleteFilt));
    localVarQueryParameterBaseName = "timeout";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("timeout", timeout));
    localVarQueryParameterBaseName = "use_index";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("use_index", useIndex));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/vnd.apache.arrow.stream");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(body);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Query a table Query table &#x60;id&#x60; with vector search, full text search and optional SQL
   * filtering. Returns results in Arrow IPC file or stream format. REST NAMESPACE ONLY REST
   * namespace returns the response as Arrow IPC file binary data instead of the
   * &#x60;QueryTableResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param queryTableRequest Query request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;byte[]&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<byte[]> queryTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull QueryTableRequest queryTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return queryTable(id, queryTableRequest, delimiter, null);
  }

  /**
   * Query a table Query table &#x60;id&#x60; with vector search, full text search and optional SQL
   * filtering. Returns results in Arrow IPC file or stream format. REST NAMESPACE ONLY REST
   * namespace returns the response as Arrow IPC file binary data instead of the
   * &#x60;QueryTableResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param queryTableRequest Query request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;byte[]&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<byte[]> queryTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull QueryTableRequest queryTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return queryTableWithHttpInfo(id, queryTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Query a table Query table &#x60;id&#x60; with vector search, full text search and optional SQL
   * filtering. Returns results in Arrow IPC file or stream format. REST NAMESPACE ONLY REST
   * namespace returns the response as Arrow IPC file binary data instead of the
   * &#x60;QueryTableResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param queryTableRequest Query request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;byte[]&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<byte[]>> queryTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull QueryTableRequest queryTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return queryTableWithHttpInfo(id, queryTableRequest, delimiter, null);
  }

  /**
   * Query a table Query table &#x60;id&#x60; with vector search, full text search and optional SQL
   * filtering. Returns results in Arrow IPC file or stream format. REST NAMESPACE ONLY REST
   * namespace returns the response as Arrow IPC file binary data instead of the
   * &#x60;QueryTableResponse&#x60; JSON object.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param queryTableRequest Query request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;byte[]&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<byte[]>> queryTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull QueryTableRequest queryTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          queryTableRequestBuilder(id, queryTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("queryTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<byte[]>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    byte[] responseValue = localVarResponseBody.readAllBytes();

                    return CompletableFuture.completedFuture(
                        new ApiResponse<byte[]>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder queryTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull QueryTableRequest queryTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling queryTable");
    }
    // verify the required parameter 'queryTableRequest' is set
    if (queryTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'queryTableRequest' when calling queryTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/query".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/vnd.apache.arrow.file, application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(queryTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Register a table to a namespace Register an existing table at a given storage location as
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param registerTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;RegisterTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RegisterTableResponse> registerTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RegisterTableRequest registerTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return registerTable(id, registerTableRequest, delimiter, null);
  }

  /**
   * Register a table to a namespace Register an existing table at a given storage location as
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param registerTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;RegisterTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RegisterTableResponse> registerTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RegisterTableRequest registerTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return registerTableWithHttpInfo(id, registerTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Register a table to a namespace Register an existing table at a given storage location as
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param registerTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;RegisterTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RegisterTableResponse>> registerTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RegisterTableRequest registerTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return registerTableWithHttpInfo(id, registerTableRequest, delimiter, null);
  }

  /**
   * Register a table to a namespace Register an existing table at a given storage location as
   * &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param registerTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;RegisterTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RegisterTableResponse>> registerTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RegisterTableRequest registerTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          registerTableRequestBuilder(id, registerTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("registerTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<RegisterTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    RegisterTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<RegisterTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<RegisterTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder registerTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RegisterTableRequest registerTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling registerTable");
    }
    // verify the required parameter 'registerTableRequest' is set
    if (registerTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'registerTableRequest' when calling registerTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/register".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(registerTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Rename a table Rename table &#x60;id&#x60; to a new name.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param renameTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;RenameTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RenameTableResponse> renameTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RenameTableRequest renameTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return renameTable(id, renameTableRequest, delimiter, null);
  }

  /**
   * Rename a table Rename table &#x60;id&#x60; to a new name.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param renameTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;RenameTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RenameTableResponse> renameTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RenameTableRequest renameTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return renameTableWithHttpInfo(id, renameTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Rename a table Rename table &#x60;id&#x60; to a new name.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param renameTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;RenameTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RenameTableResponse>> renameTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RenameTableRequest renameTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return renameTableWithHttpInfo(id, renameTableRequest, delimiter, null);
  }

  /**
   * Rename a table Rename table &#x60;id&#x60; to a new name.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param renameTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;RenameTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RenameTableResponse>> renameTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RenameTableRequest renameTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          renameTableRequestBuilder(id, renameTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("renameTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<RenameTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    RenameTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<RenameTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<RenameTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder renameTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RenameTableRequest renameTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling renameTable");
    }
    // verify the required parameter 'renameTableRequest' is set
    if (renameTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'renameTableRequest' when calling renameTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/rename".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(renameTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Restore table to a specific version Restore table &#x60;id&#x60; to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param restoreTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;RestoreTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RestoreTableResponse> restoreTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RestoreTableRequest restoreTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return restoreTable(id, restoreTableRequest, delimiter, null);
  }

  /**
   * Restore table to a specific version Restore table &#x60;id&#x60; to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param restoreTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;RestoreTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<RestoreTableResponse> restoreTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RestoreTableRequest restoreTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return restoreTableWithHttpInfo(id, restoreTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Restore table to a specific version Restore table &#x60;id&#x60; to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param restoreTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;RestoreTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RestoreTableResponse>> restoreTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RestoreTableRequest restoreTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return restoreTableWithHttpInfo(id, restoreTableRequest, delimiter, null);
  }

  /**
   * Restore table to a specific version Restore table &#x60;id&#x60; to a specific version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param restoreTableRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;RestoreTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<RestoreTableResponse>> restoreTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RestoreTableRequest restoreTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          restoreTableRequestBuilder(id, restoreTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("restoreTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<RestoreTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    RestoreTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<RestoreTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<RestoreTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder restoreTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull RestoreTableRequest restoreTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling restoreTable");
    }
    // verify the required parameter 'restoreTableRequest' is set
    if (restoreTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'restoreTableRequest' when calling restoreTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/restore".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(restoreTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Check if a table exists Check if table &#x60;id&#x60; exists. This operation should behave
   * exactly like DescribeTable, except it does not contain a response body. For DirectoryNamespace
   * implementation, a table exists if either: - The table has Lance data versions (regular table
   * created with CreateTable) - A &#x60;.lance-reserved&#x60; file exists in the table directory
   * (declared table created with DeclareTable)
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param tableExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;Void&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Void> tableExists(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull TableExistsRequest tableExistsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return tableExists(id, tableExistsRequest, delimiter, null);
  }

  /**
   * Check if a table exists Check if table &#x60;id&#x60; exists. This operation should behave
   * exactly like DescribeTable, except it does not contain a response body. For DirectoryNamespace
   * implementation, a table exists if either: - The table has Lance data versions (regular table
   * created with CreateTable) - A &#x60;.lance-reserved&#x60; file exists in the table directory
   * (declared table created with DeclareTable)
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param tableExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;Void&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Void> tableExists(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull TableExistsRequest tableExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return tableExistsWithHttpInfo(id, tableExistsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Check if a table exists Check if table &#x60;id&#x60; exists. This operation should behave
   * exactly like DescribeTable, except it does not contain a response body. For DirectoryNamespace
   * implementation, a table exists if either: - The table has Lance data versions (regular table
   * created with CreateTable) - A &#x60;.lance-reserved&#x60; file exists in the table directory
   * (declared table created with DeclareTable)
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param tableExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;Void&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Void>> tableExistsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull TableExistsRequest tableExistsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return tableExistsWithHttpInfo(id, tableExistsRequest, delimiter, null);
  }

  /**
   * Check if a table exists Check if table &#x60;id&#x60; exists. This operation should behave
   * exactly like DescribeTable, except it does not contain a response body. For DirectoryNamespace
   * implementation, a table exists if either: - The table has Lance data versions (regular table
   * created with CreateTable) - A &#x60;.lance-reserved&#x60; file exists in the table directory
   * (declared table created with DeclareTable)
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param tableExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;Void&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Void>> tableExistsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull TableExistsRequest tableExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          tableExistsRequestBuilder(id, tableExistsRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("tableExists", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.readAllBytes();
                    }
                    return CompletableFuture.completedFuture(
                        new ApiResponse<Void>(
                            localVarResponse.statusCode(), localVarResponse.headers().map(), null));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder tableExistsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull TableExistsRequest tableExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling tableExists");
    }
    // verify the required parameter 'tableExistsRequest' is set
    if (tableExistsRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'tableExistsRequest' when calling tableExists");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/exists".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(tableExistsRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Update rows in a table Update existing rows in table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableRequest Update request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;UpdateTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<UpdateTableResponse> updateTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableRequest updateTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTable(id, updateTableRequest, delimiter, null);
  }

  /**
   * Update rows in a table Update existing rows in table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableRequest Update request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;UpdateTableResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<UpdateTableResponse> updateTable(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableRequest updateTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return updateTableWithHttpInfo(id, updateTableRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Update rows in a table Update existing rows in table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableRequest Update request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;UpdateTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<UpdateTableResponse>> updateTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableRequest updateTableRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTableWithHttpInfo(id, updateTableRequest, delimiter, null);
  }

  /**
   * Update rows in a table Update existing rows in table &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableRequest Update request (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;UpdateTableResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<UpdateTableResponse>> updateTableWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableRequest updateTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          updateTableRequestBuilder(id, updateTableRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("updateTable", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<UpdateTableResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    UpdateTableResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<UpdateTableResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<UpdateTableResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder updateTableRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableRequest updateTableRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling updateTable");
    }
    // verify the required parameter 'updateTableRequest' is set
    if (updateTableRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'updateTableRequest' when calling updateTable");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/update".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(updateTableRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Update table schema metadata Replace the schema metadata for table &#x60;id&#x60; with the
   * provided key-value pairs. REST NAMESPACE ONLY REST namespace uses a direct object (map of
   * string to string) as both request and response body instead of the wrapped
   * &#x60;UpdateTableSchemaMetadataRequest&#x60; and &#x60;UpdateTableSchemaMetadataResponse&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param requestBody (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;Map&lt;String, String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Map<String, String>> updateTableSchemaMetadata(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull Map<String, String> requestBody,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTableSchemaMetadata(id, requestBody, delimiter, null);
  }

  /**
   * Update table schema metadata Replace the schema metadata for table &#x60;id&#x60; with the
   * provided key-value pairs. REST NAMESPACE ONLY REST namespace uses a direct object (map of
   * string to string) as both request and response body instead of the wrapped
   * &#x60;UpdateTableSchemaMetadataRequest&#x60; and &#x60;UpdateTableSchemaMetadataResponse&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param requestBody (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;Map&lt;String, String&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Map<String, String>> updateTableSchemaMetadata(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull Map<String, String> requestBody,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return updateTableSchemaMetadataWithHttpInfo(id, requestBody, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Update table schema metadata Replace the schema metadata for table &#x60;id&#x60; with the
   * provided key-value pairs. REST NAMESPACE ONLY REST namespace uses a direct object (map of
   * string to string) as both request and response body instead of the wrapped
   * &#x60;UpdateTableSchemaMetadataRequest&#x60; and &#x60;UpdateTableSchemaMetadataResponse&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param requestBody (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;Map&lt;String, String&gt;&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Map<String, String>>> updateTableSchemaMetadataWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull Map<String, String> requestBody,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTableSchemaMetadataWithHttpInfo(id, requestBody, delimiter, null);
  }

  /**
   * Update table schema metadata Replace the schema metadata for table &#x60;id&#x60; with the
   * provided key-value pairs. REST NAMESPACE ONLY REST namespace uses a direct object (map of
   * string to string) as both request and response body instead of the wrapped
   * &#x60;UpdateTableSchemaMetadataRequest&#x60; and &#x60;UpdateTableSchemaMetadataResponse&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param requestBody (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;Map&lt;String, String&gt;&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Map<String, String>>> updateTableSchemaMetadataWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull Map<String, String> requestBody,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          updateTableSchemaMetadataRequestBuilder(id, requestBody, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("updateTableSchemaMetadata", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<Map<String, String>>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    Map<String, String> responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<Map<String, String>>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<Map<String, String>>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder updateTableSchemaMetadataRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull Map<String, String> requestBody,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling updateTableSchemaMetadata");
    }
    // verify the required parameter 'requestBody' is set
    if (requestBody == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'requestBody' when calling updateTableSchemaMetadata");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/schema_metadata/update".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(requestBody);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }

  /**
   * Update a tag to point to a different version Update an existing tag for table &#x60;id&#x60; to
   * point to a different version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;UpdateTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<UpdateTableTagResponse> updateTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableTagRequest updateTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTableTag(id, updateTableTagRequest, delimiter, null);
  }

  /**
   * Update a tag to point to a different version Update an existing tag for table &#x60;id&#x60; to
   * point to a different version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;UpdateTableTagResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<UpdateTableTagResponse> updateTableTag(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableTagRequest updateTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return updateTableTagWithHttpInfo(id, updateTableTagRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Update a tag to point to a different version Update an existing tag for table &#x60;id&#x60; to
   * point to a different version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;UpdateTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<UpdateTableTagResponse>> updateTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableTagRequest updateTableTagRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return updateTableTagWithHttpInfo(id, updateTableTagRequest, delimiter, null);
  }

  /**
   * Update a tag to point to a different version Update an existing tag for table &#x60;id&#x60; to
   * point to a different version.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param updateTableTagRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;UpdateTableTagResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<UpdateTableTagResponse>> updateTableTagWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableTagRequest updateTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          updateTableTagRequestBuilder(id, updateTableTagRequest, delimiter, headers);
      return memberVarHttpClient
          .sendAsync(localVarRequestBuilder.build(), HttpResponse.BodyHandlers.ofInputStream())
          .thenComposeAsync(
              localVarResponse -> {
                if (memberVarAsyncResponseInterceptor != null) {
                  memberVarAsyncResponseInterceptor.accept(localVarResponse);
                }
                if (localVarResponse.statusCode() / 100 != 2) {
                  return CompletableFuture.failedFuture(
                      getApiException("updateTableTag", localVarResponse));
                }
                try {
                  InputStream localVarResponseBody = ApiClient.getResponseBody(localVarResponse);
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<UpdateTableTagResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    UpdateTableTagResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<UpdateTableTagResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<UpdateTableTagResponse>(
                            localVarResponse.statusCode(),
                            localVarResponse.headers().map(),
                            responseValue));
                  } finally {
                    if (localVarResponseBody != null) {
                      localVarResponseBody.close();
                    }
                  }
                } catch (IOException e) {
                  return CompletableFuture.failedFuture(new ApiException(e));
                }
              });
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  private HttpRequest.Builder updateTableTagRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull UpdateTableTagRequest updateTableTagRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling updateTableTag");
    }
    // verify the required parameter 'updateTableTagRequest' is set
    if (updateTableTagRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'updateTableTagRequest' when calling updateTableTag");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/table/{id}/tags/update".replace("{id}", ApiClient.urlEncode(id.toString()));

    List<Pair> localVarQueryParams = new ArrayList<>();
    StringJoiner localVarQueryStringJoiner = new StringJoiner("&");
    String localVarQueryParameterBaseName;
    localVarQueryParameterBaseName = "delimiter";
    localVarQueryParams.addAll(ApiClient.parameterToPairs("delimiter", delimiter));

    if (!localVarQueryParams.isEmpty() || localVarQueryStringJoiner.length() != 0) {
      StringJoiner queryJoiner = new StringJoiner("&");
      localVarQueryParams.forEach(p -> queryJoiner.add(p.getName() + '=' + p.getValue()));
      if (localVarQueryStringJoiner.length() != 0) {
        queryJoiner.add(localVarQueryStringJoiner.toString());
      }
      localVarRequestBuilder.uri(
          URI.create(memberVarBaseUri + localVarPath + '?' + queryJoiner.toString()));
    } else {
      localVarRequestBuilder.uri(URI.create(memberVarBaseUri + localVarPath));
    }

    localVarRequestBuilder.header("Content-Type", "application/json");
    localVarRequestBuilder.header("Accept", "application/json");

    try {
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(updateTableTagRequest);
      localVarRequestBuilder.method(
          "POST", HttpRequest.BodyPublishers.ofByteArray(localVarPostBody));
    } catch (IOException e) {
      throw new ApiException(e);
    }
    if (memberVarReadTimeout != null) {
      localVarRequestBuilder.timeout(memberVarReadTimeout);
    }
    // Add custom headers if provided
    localVarRequestBuilder =
        HttpRequestBuilderExtensions.withAdditionalHeaders(localVarRequestBuilder, headers);
    if (memberVarInterceptor != null) {
      memberVarInterceptor.accept(localVarRequestBuilder);
    }
    return localVarRequestBuilder;
  }
}
