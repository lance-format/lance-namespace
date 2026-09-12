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
package org.lance.namespace;

import org.lance.namespace.model.AnalyzeTableQueryPlanRequest;
import org.lance.namespace.model.ExplainTableQueryPlanRequest;
import org.lance.namespace.model.QueryTableRequest;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class QueryModelsTest {
  private final ObjectMapper mapper =
      new org.lance.namespace.client.async.ApiClient().getObjectMapper();

  @Test
  void testOptionalVectorAndOrderingRoundTrip() throws Exception {
    String[] conditions = {
      "{}",
      "{\"filter\":\"price > 0\"}",
      "{\"vector\":{\"single_vector\":[1.0,2.0]}}",
      "{\"vector\":{\"multi_vector\":[[1.0],[2.0]]}}",
      "{\"full_text_query\":{\"string_query\":{\"query\":\"book\"}}}",
      "{\"vector\":{\"single_vector\":[1.0]},\"full_text_query\":{\"string_query\":{\"query\":\"book\"}}}"
    };
    for (String condition : conditions) {
      ObjectNode body = (ObjectNode) mapper.readTree(condition);
      body.put("k", 20);
      body.put("offset", 5);
      body.set(
          "order_by",
          mapper.readTree(
              "[{\"column_name\":\"price\",\"ascending\":false,\"nulls_first\":true},{\"column_name\":\"id\"}]"));
      QueryTableRequest request = mapper.treeToValue(body, QueryTableRequest.class);
      assertEquals(20, request.getK());
      assertEquals(5, request.getOffset());
      assertEquals("price", request.getOrderBy().get(0).getColumnName());
      assertFalse(request.getOrderBy().get(0).getAscending());
      assertTrue(request.getOrderBy().get(0).getNullsFirst());
      assertTrue(request.getOrderBy().get(1).getAscending());
      assertFalse(request.getOrderBy().get(1).getNullsFirst());
      if (!body.hasNonNull("vector")) {
        assertNull(request.getVector());
      }
      String encoded = mapper.writeValueAsString(request);
      QueryTableRequest restored = mapper.readValue(encoded, QueryTableRequest.class);
      assertEquals(request, restored);
      JsonNode wire = mapper.readTree(encoded);
      // Compare with the input as well as the round trip: both models could
      // otherwise lose the same search condition without failing this test.
      body.path("vector")
          .fields()
          .forEachRemaining(
              field -> assertEquals(field.getValue(), wire.path("vector").path(field.getKey())));
      if (body.has("full_text_query")) {
        assertEquals(
            body.at("/full_text_query/string_query/query"),
            wire.at("/full_text_query/string_query/query"));
      }
      assertEquals(body.path("filter").asText(null), wire.path("filter").asText(null));
      assertEquals("price", wire.path("order_by").get(0).path("column_name").asText());
      assertEquals("id", wire.path("order_by").get(1).path("column_name").asText());
      AnalyzeTableQueryPlanRequest analyze =
          mapper.treeToValue(body, AnalyzeTableQueryPlanRequest.class);
      JsonNode analyzeWire = mapper.readTree(mapper.writeValueAsString(analyze));
      assertEquals(wire.path("order_by"), analyzeWire.path("order_by"));
      assertEquals(wire.path("vector"), analyzeWire.path("vector"));
      assertEquals(wire.path("full_text_query"), analyzeWire.path("full_text_query"));
      assertEquals(wire.path("filter"), analyzeWire.path("filter"));
      ObjectNode explanation = mapper.createObjectNode();
      explanation.set("query", body);
      ExplainTableQueryPlanRequest explain =
          mapper.treeToValue(explanation, ExplainTableQueryPlanRequest.class);
      assertEquals(request, explain.getQuery());
      assertEquals(
          explain,
          mapper.readValue(mapper.writeValueAsString(explain), ExplainTableQueryPlanRequest.class));
    }
  }

  @Test
  void testMinimalRequestAndEmptyOrdering() throws Exception {
    QueryTableRequest minimal = mapper.readValue("{\"k\":20}", QueryTableRequest.class);
    assertNull(minimal.getVector());
    QueryTableRequest empty =
        mapper.readValue("{\"k\":20,\"order_by\":[]}", QueryTableRequest.class);
    assertTrue(empty.getOrderBy().isEmpty());
    assertTrue(mapper.readTree(mapper.writeValueAsString(empty)).path("order_by").isArray());
  }
}
