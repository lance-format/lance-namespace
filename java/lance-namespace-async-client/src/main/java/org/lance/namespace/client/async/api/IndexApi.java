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
import org.lance.namespace.model.CreateTableIndexRequest;
import org.lance.namespace.model.CreateTableIndexResponse;
import org.lance.namespace.model.CreateTableScalarIndexResponse;
import org.lance.namespace.model.DescribeTableIndexStatsRequest;
import org.lance.namespace.model.DescribeTableIndexStatsResponse;
import org.lance.namespace.model.DropTableIndexResponse;
import org.lance.namespace.model.ListTableIndicesRequest;
import org.lance.namespace.model.ListTableIndicesResponse;

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
public class IndexApi {
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

  public IndexApi() {
    this(Configuration.getDefaultApiClient());
  }

  public IndexApi(ApiClient apiClient) {
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
}
