// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

use lance_namespace_reqwest_client::models::{
    AnalyzeTableQueryPlanRequest, ExplainTableQueryPlanRequest, QueryTableOrderBy,
    QueryTableRequest,
};
use serde_json::json;

#[test]
fn optional_vector_and_ordering_round_trip() {
    for vector in [
        None,
        Some(json!({"single_vector": [1.0]})),
        Some(json!({"multi_vector": [[1.0], [2.0]]})),
    ] {
        let mut body = json!({"k": 20, "offset": 5, "order_by": [
            {"column_name": "price", "ascending": false, "nulls_first": true},
            {"column_name": "id"}
        ]});
        if let Some(vector) = vector {
            body["vector"] = vector;
        }
        let query: QueryTableRequest = serde_json::from_value(body.clone()).unwrap();
        let analyze: AnalyzeTableQueryPlanRequest = serde_json::from_value(body.clone()).unwrap();
        let serialized = serde_json::to_value(&query).unwrap();
        assert_eq!(serialized["order_by"], body["order_by"]);
        assert_eq!(serialized["vector"], body["vector"]);
        assert_eq!(
            serde_json::from_value::<QueryTableRequest>(serialized.clone()).unwrap(),
            query
        );
        assert_eq!(serde_json::to_value(analyze).unwrap(), serialized);
        let explain: ExplainTableQueryPlanRequest =
            serde_json::from_value(json!({"query": body})).unwrap();
        assert_eq!(serde_json::to_value(explain).unwrap()["query"], serialized);
    }
}

#[test]
fn constructors_and_server_applied_defaults() {
    let request = QueryTableRequest::new(20);
    assert!(request.vector.is_none());
    assert_eq!(serde_json::to_value(request).unwrap(), json!({"k": 20}));
    let order = QueryTableOrderBy::new("price".into());
    // Rust represents omitted options as None: consumers apply Schema defaults.
    assert!(order.ascending.unwrap_or(true));
    assert!(!order.nulls_first.unwrap_or(false));
    let order: QueryTableOrderBy = serde_json::from_value(json!({"column_name": "id"})).unwrap();
    assert!(order.ascending.unwrap_or(true));
    assert!(!order.nulls_first.unwrap_or(false));
    assert!(serde_json::from_value::<QueryTableOrderBy>(json!({})).is_err());
    assert!(serde_json::from_value::<QueryTableOrderBy>(
        json!({"column_name": "id", "ascending": "true"})
    )
    .is_err());
}

#[test]
fn ordering_is_preserved_for_all_query_shapes() {
    for conditions in [
        json!({}),
        json!({"filter": "price > 0"}),
        json!({"full_text_query": {"string_query": {"query": "book"}}}),
        json!({"vector": {"single_vector": [1.0]}, "full_text_query": {"string_query": {"query": "book"}}}),
    ] {
        for ordering in [
            json!([]),
            json!([
                {"column_name": "price", "ascending": false, "nulls_first": true},
                {"column_name": "metadata.id"}
            ]),
        ] {
            let mut body = conditions.clone();
            body["k"] = json!(20);
            body["order_by"] = ordering;
            let request: QueryTableRequest = serde_json::from_value(body.clone()).unwrap();
            assert_eq!(serde_json::to_value(&request).unwrap(), body);
            let analyze: AnalyzeTableQueryPlanRequest =
                serde_json::from_value(body.clone()).unwrap();
            assert_eq!(serde_json::to_value(analyze).unwrap(), body);
            let explain: ExplainTableQueryPlanRequest =
                serde_json::from_value(json!({"query": body})).unwrap();
            assert_eq!(explain.query, Box::new(request));
            assert_eq!(serde_json::to_value(explain).unwrap()["query"], body);
        }
    }
}
