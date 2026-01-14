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

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/** Tests for HeaderProvider functionality in ApiClient. */
public class HeaderProviderTest {

  private ApiClient apiClient;

  @BeforeEach
  void setUp() {
    apiClient = new ApiClient();
  }

  @Test
  void testHeaderProviderDefaultsToNull() {
    assertNull(apiClient.getHeaderProvider());
  }

  @Test
  void testSetHeaderProvider() {
    HeaderProvider provider = () -> Collections.singletonMap("Authorization", "Bearer test-token");
    apiClient.setHeaderProvider(provider);

    assertNotNull(apiClient.getHeaderProvider());
    assertEquals(provider, apiClient.getHeaderProvider());
  }

  @Test
  void testSetHeaderProviderReturnsApiClient() {
    HeaderProvider provider = () -> Collections.singletonMap("Authorization", "Bearer test-token");
    ApiClient result = apiClient.setHeaderProvider(provider);

    assertSame(apiClient, result);
  }

  @Test
  void testHeaderProviderReturnsHeaders() {
    Map<String, String> expectedHeaders = new HashMap<>();
    expectedHeaders.put("Authorization", "Bearer my-token");
    expectedHeaders.put("X-Custom-Header", "custom-value");

    HeaderProvider provider = () -> expectedHeaders;
    apiClient.setHeaderProvider(provider);

    Map<String, String> headers = apiClient.getHeaderProvider().getHeaders();
    assertEquals("Bearer my-token", headers.get("Authorization"));
    assertEquals("custom-value", headers.get("X-Custom-Header"));
  }

  @Test
  void testHeaderProviderCalledEachTime() {
    AtomicInteger callCount = new AtomicInteger(0);

    HeaderProvider provider = () -> {
      int count = callCount.incrementAndGet();
      return Collections.singletonMap("Authorization", "Bearer token-" + count);
    };

    apiClient.setHeaderProvider(provider);

    // Call getHeaders multiple times
    Map<String, String> headers1 = apiClient.getHeaderProvider().getHeaders();
    Map<String, String> headers2 = apiClient.getHeaderProvider().getHeaders();
    Map<String, String> headers3 = apiClient.getHeaderProvider().getHeaders();

    assertEquals(3, callCount.get());
    assertEquals("Bearer token-1", headers1.get("Authorization"));
    assertEquals("Bearer token-2", headers2.get("Authorization"));
    assertEquals("Bearer token-3", headers3.get("Authorization"));
  }

  @Test
  void testHeaderProviderEmptyMap() {
    HeaderProvider provider = HashMap::new;
    apiClient.setHeaderProvider(provider);

    Map<String, String> headers = apiClient.getHeaderProvider().getHeaders();
    assertNotNull(headers);
    assertTrue(headers.isEmpty());
  }

  @Test
  void testHeaderProviderWithLambda() {
    apiClient.setHeaderProvider(() -> Collections.singletonMap("X-Api-Key", "secret-key"));

    Map<String, String> headers = apiClient.getHeaderProvider().getHeaders();
    assertEquals("secret-key", headers.get("X-Api-Key"));
  }

  @Test
  void testStatefulHeaderProvider() {
    // Simulate a token provider that refreshes tokens
    StatefulTokenProvider tokenProvider = new StatefulTokenProvider();
    apiClient.setHeaderProvider(tokenProvider);

    // First call
    Map<String, String> headers1 = apiClient.getHeaderProvider().getHeaders();
    assertEquals("Bearer token-v1", headers1.get("Authorization"));

    // Simulate token refresh
    tokenProvider.refreshToken();

    // Second call should have new token
    Map<String, String> headers2 = apiClient.getHeaderProvider().getHeaders();
    assertEquals("Bearer token-v2", headers2.get("Authorization"));
  }

  @Test
  void testHeaderProviderCanBeCleared() {
    HeaderProvider provider = () -> Collections.singletonMap("Authorization", "Bearer token");
    apiClient.setHeaderProvider(provider);
    assertNotNull(apiClient.getHeaderProvider());

    apiClient.setHeaderProvider(null);
    assertNull(apiClient.getHeaderProvider());
  }

  @Test
  void testHeaderProviderWithMultipleHeaders() {
    HeaderProvider provider = () -> {
      Map<String, String> headers = new HashMap<>();
      headers.put("Authorization", "Bearer token");
      headers.put("X-Request-Id", "req-123");
      headers.put("X-Correlation-Id", "corr-456");
      headers.put("Accept-Language", "en-US");
      return headers;
    };

    apiClient.setHeaderProvider(provider);

    Map<String, String> headers = apiClient.getHeaderProvider().getHeaders();
    assertEquals(4, headers.size());
    assertEquals("Bearer token", headers.get("Authorization"));
    assertEquals("req-123", headers.get("X-Request-Id"));
    assertEquals("corr-456", headers.get("X-Correlation-Id"));
    assertEquals("en-US", headers.get("Accept-Language"));
  }

  /** Helper class to simulate a stateful token provider. */
  private static class StatefulTokenProvider implements HeaderProvider {
    private int tokenVersion = 1;

    @Override
    public Map<String, String> getHeaders() {
      return Collections.singletonMap("Authorization", "Bearer token-v" + tokenVersion);
    }

    public void refreshToken() {
      tokenVersion++;
    }
  }
}
