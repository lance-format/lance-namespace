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
import org.lance.namespace.model.CreateNamespaceRequest;
import org.lance.namespace.model.CreateNamespaceResponse;
import org.lance.namespace.model.DescribeNamespaceRequest;
import org.lance.namespace.model.DescribeNamespaceResponse;
import org.lance.namespace.model.DropNamespaceRequest;
import org.lance.namespace.model.DropNamespaceResponse;
import org.lance.namespace.model.ListNamespacesResponse;
import org.lance.namespace.model.ListTablesResponse;
import org.lance.namespace.model.NamespaceExistsRequest;

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
public class NamespaceApi {
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

  public NamespaceApi() {
    this(Configuration.getDefaultApiClient());
  }

  public NamespaceApi(ApiClient apiClient) {
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
   * Create a new namespace Create new namespace &#x60;id&#x60;. During the creation process, the
   * implementation may modify user-provided &#x60;properties&#x60;, such as adding additional
   * properties like &#x60;created_at&#x60; to user-provided properties, omitting any specific
   * property, or performing actions based on any property value.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;CreateNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateNamespaceResponse> createNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateNamespaceRequest createNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createNamespace(id, createNamespaceRequest, delimiter, null);
  }

  /**
   * Create a new namespace Create new namespace &#x60;id&#x60;. During the creation process, the
   * implementation may modify user-provided &#x60;properties&#x60;, such as adding additional
   * properties like &#x60;created_at&#x60; to user-provided properties, omitting any specific
   * property, or performing actions based on any property value.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;CreateNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<CreateNamespaceResponse> createNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateNamespaceRequest createNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return createNamespaceWithHttpInfo(id, createNamespaceRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Create a new namespace Create new namespace &#x60;id&#x60;. During the creation process, the
   * implementation may modify user-provided &#x60;properties&#x60;, such as adding additional
   * properties like &#x60;created_at&#x60; to user-provided properties, omitting any specific
   * property, or performing actions based on any property value.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;CreateNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateNamespaceResponse>> createNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateNamespaceRequest createNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return createNamespaceWithHttpInfo(id, createNamespaceRequest, delimiter, null);
  }

  /**
   * Create a new namespace Create new namespace &#x60;id&#x60;. During the creation process, the
   * implementation may modify user-provided &#x60;properties&#x60;, such as adding additional
   * properties like &#x60;created_at&#x60; to user-provided properties, omitting any specific
   * property, or performing actions based on any property value.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param createNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;CreateNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<CreateNamespaceResponse>> createNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateNamespaceRequest createNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          createNamespaceRequestBuilder(id, createNamespaceRequest, delimiter, headers);
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
                        getApiException("createNamespace", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<CreateNamespaceResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    CreateNamespaceResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<CreateNamespaceResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<CreateNamespaceResponse>(
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

  private HttpRequest.Builder createNamespaceRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull CreateNamespaceRequest createNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling createNamespace");
    }
    // verify the required parameter 'createNamespaceRequest' is set
    if (createNamespaceRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'createNamespaceRequest' when calling createNamespace");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/create".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(createNamespaceRequest);
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
   * Describe a namespace Describe the detailed information for namespace &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DescribeNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeNamespaceResponse> describeNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeNamespaceRequest describeNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return describeNamespace(id, describeNamespaceRequest, delimiter, null);
  }

  /**
   * Describe a namespace Describe the detailed information for namespace &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DescribeNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DescribeNamespaceResponse> describeNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeNamespaceRequest describeNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return describeNamespaceWithHttpInfo(id, describeNamespaceRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Describe a namespace Describe the detailed information for namespace &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeNamespaceResponse>> describeNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeNamespaceRequest describeNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return describeNamespaceWithHttpInfo(id, describeNamespaceRequest, delimiter, null);
  }

  /**
   * Describe a namespace Describe the detailed information for namespace &#x60;id&#x60;.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param describeNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DescribeNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DescribeNamespaceResponse>> describeNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeNamespaceRequest describeNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          describeNamespaceRequestBuilder(id, describeNamespaceRequest, delimiter, headers);
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
                            "describeNamespace", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DescribeNamespaceResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DescribeNamespaceResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DescribeNamespaceResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DescribeNamespaceResponse>(
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

  private HttpRequest.Builder describeNamespaceRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DescribeNamespaceRequest describeNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling describeNamespace");
    }
    // verify the required parameter 'describeNamespaceRequest' is set
    if (describeNamespaceRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'describeNamespaceRequest' when calling describeNamespace");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/describe".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(describeNamespaceRequest);
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
   * Drop a namespace Drop namespace &#x60;id&#x60; from its parent namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param dropNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;DropNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropNamespaceResponse> dropNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DropNamespaceRequest dropNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropNamespace(id, dropNamespaceRequest, delimiter, null);
  }

  /**
   * Drop a namespace Drop namespace &#x60;id&#x60; from its parent namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param dropNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;DropNamespaceResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<DropNamespaceResponse> dropNamespace(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DropNamespaceRequest dropNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return dropNamespaceWithHttpInfo(id, dropNamespaceRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Drop a namespace Drop namespace &#x60;id&#x60; from its parent namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param dropNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;DropNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropNamespaceResponse>> dropNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DropNamespaceRequest dropNamespaceRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return dropNamespaceWithHttpInfo(id, dropNamespaceRequest, delimiter, null);
  }

  /**
   * Drop a namespace Drop namespace &#x60;id&#x60; from its parent namespace.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param dropNamespaceRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;DropNamespaceResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<DropNamespaceResponse>> dropNamespaceWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DropNamespaceRequest dropNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          dropNamespaceRequestBuilder(id, dropNamespaceRequest, delimiter, headers);
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
                        getApiException("dropNamespace", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<DropNamespaceResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    DropNamespaceResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<DropNamespaceResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<DropNamespaceResponse>(
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

  private HttpRequest.Builder dropNamespaceRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull DropNamespaceRequest dropNamespaceRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(400, "Missing the required parameter 'id' when calling dropNamespace");
    }
    // verify the required parameter 'dropNamespaceRequest' is set
    if (dropNamespaceRequest == null) {
      throw new ApiException(
          400, "Missing the required parameter 'dropNamespaceRequest' when calling dropNamespace");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/drop".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(dropNamespaceRequest);
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
   * List namespaces List all child namespace names of the parent namespace &#x60;id&#x60;. REST
   * NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body. It
   * passes in the &#x60;ListNamespacesRequest&#x60; information in the following way: -
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
   * @return CompletableFuture&lt;ListNamespacesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListNamespacesResponse> listNamespaces(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listNamespaces(id, delimiter, pageToken, limit, null);
  }

  /**
   * List namespaces List all child namespace names of the parent namespace &#x60;id&#x60;. REST
   * NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body. It
   * passes in the &#x60;ListNamespacesRequest&#x60; information in the following way: -
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
   * @return CompletableFuture&lt;ListNamespacesResponse&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ListNamespacesResponse> listNamespaces(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      return listNamespacesWithHttpInfo(id, delimiter, pageToken, limit, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * List namespaces List all child namespace names of the parent namespace &#x60;id&#x60;. REST
   * NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body. It
   * passes in the &#x60;ListNamespacesRequest&#x60; information in the following way: -
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
   * @return CompletableFuture&lt;ApiResponse&lt;ListNamespacesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListNamespacesResponse>> listNamespacesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit)
      throws ApiException {
    return listNamespacesWithHttpInfo(id, delimiter, pageToken, limit, null);
  }

  /**
   * List namespaces List all child namespace names of the parent namespace &#x60;id&#x60;. REST
   * NAMESPACE ONLY REST namespace uses GET to perform this operation without a request body. It
   * passes in the &#x60;ListNamespacesRequest&#x60; information in the following way: -
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
   * @return CompletableFuture&lt;ApiResponse&lt;ListNamespacesResponse&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<ListNamespacesResponse>> listNamespacesWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          listNamespacesRequestBuilder(id, delimiter, pageToken, limit, headers);
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
                        getApiException("listNamespaces", localVarResponse, localVarResponseBody));
                  }
                  try {
                    if (localVarResponseBody == null) {
                      return CompletableFuture.completedFuture(
                          new ApiResponse<ListNamespacesResponse>(
                              localVarResponse.statusCode(),
                              localVarResponse.headers().map(),
                              null));
                    }

                    String responseBody = new String(localVarResponseBody.readAllBytes());
                    ListNamespacesResponse responseValue =
                        responseBody.isBlank()
                            ? null
                            : memberVarObjectMapper.readValue(
                                responseBody, new TypeReference<ListNamespacesResponse>() {});

                    return CompletableFuture.completedFuture(
                        new ApiResponse<ListNamespacesResponse>(
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

  private HttpRequest.Builder listNamespacesRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nullable String delimiter,
      @javax.annotation.Nullable String pageToken,
      @javax.annotation.Nullable Integer limit,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling listNamespaces");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/list".replace("{id}", ApiClient.urlEncode(id.toString()));

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
                        getApiException("listTables", localVarResponse, localVarResponseBody));
                  }
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
   * Check if a namespace exists Check if namespace &#x60;id&#x60; exists. This operation must
   * behave exactly like the DescribeNamespace API, except it does not contain a response body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param namespaceExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;Void&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Void> namespaceExists(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull NamespaceExistsRequest namespaceExistsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return namespaceExists(id, namespaceExistsRequest, delimiter, null);
  }

  /**
   * Check if a namespace exists Check if namespace &#x60;id&#x60; exists. This operation must
   * behave exactly like the DescribeNamespace API, except it does not contain a response body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param namespaceExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;Void&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<Void> namespaceExists(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull NamespaceExistsRequest namespaceExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      return namespaceExistsWithHttpInfo(id, namespaceExistsRequest, delimiter, headers)
          .thenApply(ApiResponse::getData);
    } catch (ApiException e) {
      return CompletableFuture.failedFuture(e);
    }
  }

  /**
   * Check if a namespace exists Check if namespace &#x60;id&#x60; exists. This operation must
   * behave exactly like the DescribeNamespace API, except it does not contain a response body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param namespaceExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @return CompletableFuture&lt;ApiResponse&lt;Void&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Void>> namespaceExistsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull NamespaceExistsRequest namespaceExistsRequest,
      @javax.annotation.Nullable String delimiter)
      throws ApiException {
    return namespaceExistsWithHttpInfo(id, namespaceExistsRequest, delimiter, null);
  }

  /**
   * Check if a namespace exists Check if namespace &#x60;id&#x60; exists. This operation must
   * behave exactly like the DescribeNamespace API, except it does not contain a response body.
   *
   * @param id &#x60;string identifier&#x60; of an object in a namespace, following the Lance
   *     Namespace spec. When the value is equal to the delimiter, it represents the root namespace.
   *     For example, &#x60;v1/namespace/$/list&#x60; performs a &#x60;ListNamespace&#x60; on the
   *     root namespace. (required)
   * @param namespaceExistsRequest (required)
   * @param delimiter An optional delimiter of the &#x60;string identifier&#x60;, following the
   *     Lance Namespace spec. When not specified, the &#x60;$&#x60; delimiter must be used.
   *     (optional)
   * @param headers Optional headers to include in the request
   * @return CompletableFuture&lt;ApiResponse&lt;Void&gt;&gt;
   * @throws ApiException if fails to make API call
   */
  public CompletableFuture<ApiResponse<Void>> namespaceExistsWithHttpInfo(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull NamespaceExistsRequest namespaceExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    try {
      HttpRequest.Builder localVarRequestBuilder =
          namespaceExistsRequestBuilder(id, namespaceExistsRequest, delimiter, headers);
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
                        getApiException("namespaceExists", localVarResponse, localVarResponseBody));
                  }
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

  private HttpRequest.Builder namespaceExistsRequestBuilder(
      @javax.annotation.Nonnull String id,
      @javax.annotation.Nonnull NamespaceExistsRequest namespaceExistsRequest,
      @javax.annotation.Nullable String delimiter,
      Map<String, String> headers)
      throws ApiException {
    // verify the required parameter 'id' is set
    if (id == null) {
      throw new ApiException(
          400, "Missing the required parameter 'id' when calling namespaceExists");
    }
    // verify the required parameter 'namespaceExistsRequest' is set
    if (namespaceExistsRequest == null) {
      throw new ApiException(
          400,
          "Missing the required parameter 'namespaceExistsRequest' when calling namespaceExists");
    }

    HttpRequest.Builder localVarRequestBuilder = HttpRequest.newBuilder();

    String localVarPath =
        "/v1/namespace/{id}/exists".replace("{id}", ApiClient.urlEncode(id.toString()));

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
      byte[] localVarPostBody = memberVarObjectMapper.writeValueAsBytes(namespaceExistsRequest);
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
