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
import org.lance.namespace.model.CreateTableTagRequest;
import org.lance.namespace.model.CreateTableTagResponse;
import org.lance.namespace.model.DeleteTableTagRequest;
import org.lance.namespace.model.DeleteTableTagResponse;
import org.lance.namespace.model.GetTableTagVersionRequest;
import org.lance.namespace.model.GetTableTagVersionResponse;
import org.lance.namespace.model.ListTableTagsResponse;
import org.lance.namespace.model.UpdateTableTagRequest;
import org.lance.namespace.model.UpdateTableTagResponse;

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
public class TagApi {
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

  public TagApi() {
    this(Configuration.getDefaultApiClient());
  }

  public TagApi(ApiClient apiClient) {
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
                        getApiException("createTableTag", localVarResponse, localVarResponseBody));
                  }
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
                        getApiException("deleteTableTag", localVarResponse, localVarResponseBody));
                  }
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
                            "getTableTagVersion", localVarResponse, localVarResponseBody));
                  }
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
                        getApiException("listTableTags", localVarResponse, localVarResponseBody));
                  }
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
                        getApiException("updateTableTag", localVarResponse, localVarResponseBody));
                  }
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
