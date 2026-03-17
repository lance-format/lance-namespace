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
import org.lance.namespace.model.AnalyzeTableQueryPlanRequest;
import org.lance.namespace.model.CountTableRowsRequest;
import org.lance.namespace.model.CreateTableResponse;
import org.lance.namespace.model.DeleteFromTableRequest;
import org.lance.namespace.model.DeleteFromTableResponse;
import org.lance.namespace.model.ExplainTableQueryPlanRequest;
import org.lance.namespace.model.InsertIntoTableResponse;
import org.lance.namespace.model.MergeInsertIntoTableResponse;
import org.lance.namespace.model.QueryTableRequest;
import org.lance.namespace.model.UpdateTableRequest;
import org.lance.namespace.model.UpdateTableResponse;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayInputStream;
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
    comments = "Generator version: 7.12.0")
public class DataApi {
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
  private final Consumer<HttpResponse<String>> memberVarAsyncResponseInterceptor;

  public DataApi() {
    this(Configuration.getDefaultApiClient());
  }

  public DataApi(ApiClient apiClient) {
    memberVarHttpClient = apiClient.getHttpClient();
    memberVarObjectMapper = apiClient.getObjectMapper();
    memberVarBaseUri = apiClient.getBaseUri();
    memberVarInterceptor = apiClient.getRequestInterceptor();
    memberVarReadTimeout = apiClient.getReadTimeout();
    memberVarResponseInterceptor = apiClient.getResponseInterceptor();
    memberVarAsyncResponseInterceptor = apiClient.getAsyncResponseInterceptor();
  }

  private ApiException getApiException(
      String operationId, HttpResponse<?> response, InputStream responseBody) {
    try {
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

  private HttpResponse<String> toStringResponse(
      HttpResponse<InputStream> response, String responseBody) {
    return new HttpResponse<String>() {
      @Override
      public int statusCode() {
        return response.statusCode();
      }

      @Override
      public HttpRequest request() {
        return response.request();
      }

      @Override
      public java.util.Optional<HttpResponse<String>> previousResponse() {
        return java.util.Optional.empty();
      }

      @Override
      public java.net.http.HttpHeaders headers() {
        return response.headers();
      }

      @Override
      public String body() {
        return responseBody;
      }

      @Override
      public java.util.Optional<javax.net.ssl.SSLSession> sslSession() {
        return response.sslSession();
      }

      @Override
      public URI uri() {
        return response.uri();
      }

      @Override
      public HttpClient.Version version() {
        return response.version();
      }
    };
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException(
                            "alterTableAddColumns", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException(
                            "analyzeTableQueryPlan", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("countTableRows", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("createTable", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("deleteFromTable", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException(
                            "explainTableQueryPlan", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("insertIntoTable", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException(
                            "mergeInsertIntoTable", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("queryTable", localVarResponse, localVarResponseBody));
                  }
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
                try {
                  InputStream localVarResponseBody = localVarResponse.body();
                  if (memberVarAsyncResponseInterceptor != null) {
                    String localVarResponseBodyText = null;
                    if (localVarResponseBody != null) {
                      byte[] localVarResponseBodyBytes = localVarResponseBody.readAllBytes();
                      localVarResponseBody.close();
                      localVarResponseBodyText = new String(localVarResponseBodyBytes);
                      localVarResponseBody = new ByteArrayInputStream(localVarResponseBodyBytes);
                    }
                    memberVarAsyncResponseInterceptor.accept(
                        toStringResponse(localVarResponse, localVarResponseBodyText));
                  }
                  if (localVarResponse.statusCode() / 100 != 2) {
                    return CompletableFuture.failedFuture(
                        getApiException("updateTable", localVarResponse, localVarResponseBody));
                  }
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
}
