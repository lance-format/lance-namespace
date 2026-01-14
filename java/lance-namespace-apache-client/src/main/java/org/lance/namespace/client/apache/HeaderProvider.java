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
package org.lance.namespace.client.apache;

import java.util.Map;

/**
 * Provides headers dynamically for each API request.
 *
 * <p>Use this interface to supply headers that may change over time, such as authentication tokens
 * that need periodic refresh.
 *
 * <p>Example usage:
 *
 * <pre>{@code
 * ApiClient client = new ApiClient();
 * client.setHeaderProvider(() -> {
 *     String token = refreshTokenIfNeeded();
 *     Map<String, String> headers = new HashMap<>();
 *     headers.put("Authorization", "Bearer " + token);
 *     return headers;
 * });
 * }</pre>
 */
@FunctionalInterface
public interface HeaderProvider {
  /**
   * Get headers to add to the request.
   *
   * <p>This method is called before each API request, allowing dynamic headers to be provided.
   *
   * @return A map of header names to values. May return an empty map but should not return null.
   */
  Map<String, String> getHeaders();
}
