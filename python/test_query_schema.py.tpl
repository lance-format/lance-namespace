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

"""Validate query contracts against the source namespace specification."""
import unittest
from pathlib import Path

import yaml
from jsonschema import Draft202012Validator


ROOT = Path(__file__).resolve().parents[3]
SPEC = yaml.safe_load((ROOT / "docs/src/spec.yaml").read_text())


class TestQuerySchema(unittest.TestCase):
    def check_request(self, request, valid):
        for name in ("QueryTableRequest", "AnalyzeTableQueryPlanRequest", "ExplainTableQueryPlanRequest"):
            with self.subTest(model=name, request=request):
                validator = Draft202012Validator({
                    **SPEC, "$ref": f"#/components/schemas/{name}",
                })
                value = {"query": request} if name == "ExplainTableQueryPlanRequest" else request
                errors = list(validator.iter_errors(value))
                self.assertEqual(not errors, valid, [e.message for e in errors])

    def test_optional_vector(self):
        for request in (
            {"k": 20},
            {"k": 20, "vector": {"single_vector": [1.0, 2.0]}},
            {"k": 20, "vector": {"multi_vector": [[1.0], [2.0]]}},
        ):
            self.check_request(request, True)

    def test_vector_follows_full_text_query_optional_style(self):
        for name in ("QueryTableRequest", "AnalyzeTableQueryPlanRequest"):
            schema = SPEC["components"]["schemas"][name]
            with self.subTest(model=name):
                for field in ("vector", "full_text_query"):
                    self.assertNotIn(field, schema["required"])
                    self.assertEqual(schema["properties"][field]["type"], "object")
                    self.assertIs(schema["properties"][field]["nullable"], True)

    def test_existing_vector_shapes_remain_accepted(self):
        # Preserve the previous Schema's accepted shapes. The query implementation
        # decides whether these objects describe a valid vector search.
        for vector in (
            {}, {"single_vector": []},
            {"multi_vector": []}, {"multi_vector": [[]]},
            {"multi_vector": [[1.0], []]},
            {"single_vector": [1.0], "multi_vector": [[1.0]]},
            {"unknown": [1.0]},
        ):
            self.check_request({"k": 20, "vector": vector}, True)

    def test_invalid_vector_types(self):
        for vector in (
            [], "vector", 1,
            {"single_vector": None}, {"single_vector": [True]},
            {"single_vector": ["1"]}, {"multi_vector": [1.0]},
        ):
            self.check_request({"k": 20, "vector": vector}, False)

    def test_ordering_for_query_modes(self):
        for conditions in (
            {}, {"filter": "price > 0"},
            {"vector": {"single_vector": [1.0]}},
            {"full_text_query": {"string_query": {"query": "book"}}},
            {"vector": {"single_vector": [1.0]},
             "full_text_query": {"string_query": {"query": "book"}}},
        ):
            for ordering in ([], [{"column_name": "price"}], [
                {"column_name": "price", "ascending": False, "nulls_first": True},
                {"column_name": "id"},
            ]):
                self.check_request({"k": 20, "offset": 5, **conditions, "order_by": ordering}, True)

    def test_invalid_ordering(self):
        for ordering in (None, "price", {}, ["price"], [None], [{}],
                         [{"column_name": ""}], [{"column_name": None}],
                         [{"column_name": 42}],
                         [{"column_name": "price", "ascending": "true"}],
                         [{"column_name": "price", "ascending": None}],
                         [{"column_name": "price", "nulls_first": 0}],
                         [{"column_name": "price", "nulls_first": None}]):
            self.check_request({"k": 20, "order_by": ordering}, False)

    def test_existing_pagination_constraints(self):
        for request in ({}, {"k": -1}, {"k": 1, "offset": -1}):
            self.check_request(request, False)
        self.check_request({"k": 0}, True)

    def test_query_and_analyze_fields_stay_aligned(self):
        schemas = SPEC["components"]["schemas"]
        query = schemas["QueryTableRequest"]
        analyze = schemas["AnalyzeTableQueryPlanRequest"]
        for field in ("vector", "order_by"):
            self.assertEqual(query["properties"][field], analyze["properties"][field])
        self.assertEqual(query["required"], ["k"])
        self.assertEqual(analyze["required"], ["k"])
        order = schemas["QueryTableOrderBy"]["properties"]
        self.assertIs(order["ascending"]["default"], True)
        self.assertIs(order["nulls_first"]["default"], False)


if __name__ == "__main__":
    unittest.main()
