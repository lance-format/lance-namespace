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
package org.lance.namespace.server.springboot.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class SpringQueryValidationTest {
  private static final Class<?>[] QUERY_MODELS = {
    QueryTableRequest.class, AnalyzeTableQueryPlanRequest.class, ExplainTableQueryPlanRequest.class
  };
  private final ObjectMapper mapper = new ObjectMapper();

  @Test
  void beanValidationAcceptsQueriesWithoutVector() throws Exception {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      for (Class<?> model : QUERY_MODELS) {
        for (String body :
            new String[] {
              "{\"k\":20}",
              "{\"k\":20,\"order_by\":[]}",
              "{\"k\":20,\"order_by\":[{\"column_name\":\"price\"}]}"
            }) {
          assertTrue(
              factory.getValidator().validate(readQuery(body, model)).isEmpty(),
              model.getSimpleName() + ": " + body);
        }
      }
    }
  }

  @Test
  void beanValidationRejectsMissingAndEmptySortFields() throws Exception {
    try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
      for (Class<?> model : QUERY_MODELS) {
        for (String body :
            new String[] {
              "{\"k\":20,\"order_by\":[{}]}", "{\"k\":20,\"order_by\":[{\"column_name\":\"\"}]}"
            }) {
          String prefix = model == ExplainTableQueryPlanRequest.class ? "query." : "";
          assertEquals(
              Collections.singleton(prefix + "orderBy[0].columnName"),
              factory.getValidator().validate(readQuery(body, model)).stream()
                  .map(violation -> violation.getPropertyPath().toString())
                  .collect(Collectors.toSet()),
              model.getSimpleName() + ": " + body);
        }
      }
    }
  }

  private Object readQuery(String body, Class<?> model) throws Exception {
    if (model == ExplainTableQueryPlanRequest.class) {
      body = "{\"query\":" + body + "}";
    }
    return mapper.readValue(body, model);
  }
}
