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
import org.lance.namespace.model.AlterTransactionRequest;
import org.lance.namespace.model.AlterTransactionResponse;
import org.lance.namespace.model.BatchCommitTablesRequest;
import org.lance.namespace.model.BatchCommitTablesResponse;
import org.lance.namespace.model.DescribeTransactionRequest;
import org.lance.namespace.model.DescribeTransactionResponse;

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
public class TransactionApi {
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

  public TransactionApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TransactionApi(ApiClient apiClient) {
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
   * Alter information of a transaction. Alter a transaction with a list of actions such as setting
   * status or properties. The server should either succeed and apply all actions, or fail and apply
   * no action.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;AlterTransactionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTransactionResponse> alterTransaction(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTransactionRequest alterTransactionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return alterTransaction(id, alterTransactionRequest, delimiter, null);
  }

  /**
   * Alter information of a transaction. Alter a transaction with a list of actions such as setting
   * status or properties. The server should either succeed and apply all actions, or fail and apply
   * no action.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;AlterTransactionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<AlterTransactionResponse> alterTransaction(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTransactionRequest alterTransactionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return alterTransactionWithHttpInfo(id, alterTransactionRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Alter information of a transaction. Alter a transaction with a list of actions such as setting
   * status or properties. The server should either succeed and apply all actions, or fail and apply
   * no action.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTransactionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTransactionResponse>> alterTransactionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTransactionRequest alterTransactionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return alterTransactionWithHttpInfo(id, alterTransactionRequest, delimiter, null);
  }

  /**
   * Alter information of a transaction. Alter a transaction with a list of actions such as setting
   * status or properties. The server should either succeed and apply all actions, or fail and apply
   * no action.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param alterTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;AlterTransactionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<AlterTransactionResponse>> alterTransactionWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTransactionRequest alterTransactionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          alterTransactionRequestBuilder(id, alterTransactionRequest, delimiter, headers);
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
                            "alterTransaction", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<AlterTransactionResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    AlterTransactionResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<AlterTransactionResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<AlterTransactionResponse>(
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

  private HttpRequest.Builder alterTransactionRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull AlterTransactionRequest alterTransactionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling alterTransaction");
    }
    // verify the required parameter 'alterTransactionRequest' is set
    if (alterTransactionRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'alterTransactionRequest' when calling alterTransaction");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/transaction/{id}/alter".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(alterTransactionRequest);
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
                            "batchCommitTables", localVarResponse, localVarResponseBody));
                  }
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
   * Describe information about a transaction Return a detailed information for a given transaction
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DescribeTransactionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTransactionResponse> describeTransaction(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTransactionRequest describeTransactionRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return describeTransaction(id, describeTransactionRequest, delimiter, null);
  }

  /**
   * Describe information about a transaction Return a detailed information for a given transaction
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DescribeTransactionResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeTransactionResponse> describeTransaction(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTransactionRequest describeTransactionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return describeTransactionWithHttpInfo(id, describeTransactionRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Describe information about a transaction Return a detailed information for a given transaction
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTransactionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTransactionResponse>>
      describeTransactionWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull DescribeTransactionRequest describeTransactionRequest,
          @javax.annotation.Nullable String delimiter)
          throws ApiException {
    return describeTransactionWithHttpInfo(id, describeTransactionRequest, delimiter, null);
  }

  /**
   * Describe information about a transaction Return a detailed information for a given transaction
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeTransactionRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeTransactionResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeTransactionResponse>>
      describeTransactionWithHttpInfo(
          @javax.annotation.Nonnull String id,
          @javax.annotation.Nonnull DescribeTransactionRequest describeTransactionRequest,
          @javax.annotation.Nullable String delimiter,
          Map<String, String> headers)
          throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          describeTransactionRequestBuilder(id, describeTransactionRequest, delimiter, headers);
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
                            "describeTransaction", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DescribeTransactionResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DescribeTransactionResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DescribeTransactionResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DescribeTransactionResponse>(
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

  private HttpRequest.Builder describeTransactionRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeTransactionRequest describeTransactionRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling describeTransaction");
    }
    // verify the required parameter 'describeTransactionRequest' is set
    if (describeTransactionRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'describeTransactionRequest' when calling describeTransaction");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/transaction/{id}/describe".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(describeTransactionRequest);
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
