# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

"""Query request model tests, restored from this template after codegen."""

import json
import unittest

from pydantic import ValidationError

from lance_namespace_urllib3_client import ApiClient
from lance_namespace_urllib3_client.models import (
    AnalyzeTableQueryPlanRequest,
    ExplainTableQueryPlanRequest,
    QueryTableOrderBy,
    QueryTableRequest,
)


class TestQueryTableRequest(unittest.TestCase):
    def test_query_wire_round_trip(self):
        conditions = (
            {},
            {"filter": "price > 0"},
            {"vector": {"single_vector": [1.0, 2.0]}},
            {"vector": {"multi_vector": [[1.0], [2.0]]}},
            {"full_text_query": {"string_query": {"query": "book"}}},
            {
                "vector": {"single_vector": [1.0]},
                "full_text_query": {"string_query": {"query": "book"}},
            },
        )
        for model in (QueryTableRequest, AnalyzeTableQueryPlanRequest):
            for condition in conditions:
                with self.subTest(model=model.__name__, condition=condition):
                    ordering = [
                        QueryTableOrderBy(
                            column_name="price", ascending=False, nulls_first=True
                        ),
                        QueryTableOrderBy(column_name="metadata.id"),
                    ]
                    request = model(k=20, offset=5, order_by=ordering, **condition)
                    with ApiClient() as client:
                        body = client.sanitize_for_serialization(request)
                    self.assertEqual(body["order_by"], [
                        {"column_name": "price", "ascending": False, "nulls_first": True},
                        {"column_name": "metadata.id", "ascending": True, "nulls_first": False},
                    ])
                    self.assertEqual(body["k"], 20)
                    self.assertEqual(body["offset"], 5)
                    self.assertEqual(body.get("vector"), condition.get("vector"))
                    self.assertEqual(body.get("filter"), condition.get("filter"))
                    self.assertEqual(
                        body.get("full_text_query"), condition.get("full_text_query")
                    )
                    self.assertEqual(model.from_json(json.dumps(body)), request)
                    if model is QueryTableRequest:
                        explain = ExplainTableQueryPlanRequest(query=request)
                        self.assertEqual(
                            ExplainTableQueryPlanRequest.from_json(explain.to_json()).query,
                            request,
                        )

    def test_minimal_query_and_empty_ordering(self):
        for model in (QueryTableRequest, AnalyzeTableQueryPlanRequest):
            with self.subTest(model=model.__name__):
                minimal = model(k=20)
                self.assertIsNone(minimal.vector)
                self.assertEqual(minimal.to_dict(), {"k": 20})
                self.assertEqual(model(k=20, order_by=[]).to_dict()["order_by"], [])

    def test_ordering_defaults_and_field_constraints(self):
        ordering = QueryTableOrderBy.from_dict({"column_name": "price"})
        self.assertIs(ordering.ascending, True)
        self.assertIs(ordering.nulls_first, False)
        for fields in (
            {},
            {"column_name": ""},
            {"column_name": "price", "ascending": "true"},
        ):
            with self.subTest(fields=fields):
                with self.assertRaises(ValidationError):
                    QueryTableOrderBy(**fields)

    def test_existing_vector_shapes_round_trip(self):
        for vector in (
            {},
            {"single_vector": []},
            {"multi_vector": []},
            {"multi_vector": [[1.0], []]},
            {"single_vector": [1.0], "multi_vector": [[1.0]]},
        ):
            for model in (QueryTableRequest, AnalyzeTableQueryPlanRequest):
                with self.subTest(model=model.__name__, vector=vector):
                    request = model(k=20, vector=vector)
                    self.assertEqual(request.to_dict()["vector"], vector)
                    self.assertEqual(model.from_json(request.to_json()), request)

    def test_vector_type_validation(self):
        with self.assertRaises(ValidationError):
            QueryTableRequest(k=20, vector={"single_vector": ["1"]})


if __name__ == "__main__":
    unittest.main()
