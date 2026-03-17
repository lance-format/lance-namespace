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
package org.lance.namespace.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Objects;
import java.util.StringJoiner;

/** MatchQuery */
@JsonPropertyOrder({
  MatchQuery.JSON_PROPERTY_BOOST,
  MatchQuery.JSON_PROPERTY_COLUMN,
  MatchQuery.JSON_PROPERTY_FUZZINESS,
  MatchQuery.JSON_PROPERTY_MAX_EXPANSIONS,
  MatchQuery.JSON_PROPERTY_OPERATOR,
  MatchQuery.JSON_PROPERTY_PREFIX_LENGTH,
  MatchQuery.JSON_PROPERTY_TERMS
})
@javax.annotation.Generated(
    value = "org.openapitools.codegen.languages.JavaClientCodegen",
    comments = "Generator version: 7.20.0")
public class MatchQuery {
  public static final String JSON_PROPERTY_BOOST = "boost";
  @javax.annotation.Nullable private Float boost;

  public static final String JSON_PROPERTY_COLUMN = "column";
  @javax.annotation.Nullable private String column;

  public static final String JSON_PROPERTY_FUZZINESS = "fuzziness";
  @javax.annotation.Nullable private Integer fuzziness;

  public static final String JSON_PROPERTY_MAX_EXPANSIONS = "max_expansions";
  @javax.annotation.Nullable private Integer maxExpansions;

  public static final String JSON_PROPERTY_OPERATOR = "operator";
  @javax.annotation.Nullable private String operator;

  public static final String JSON_PROPERTY_PREFIX_LENGTH = "prefix_length";
  @javax.annotation.Nullable private Integer prefixLength;

  public static final String JSON_PROPERTY_TERMS = "terms";
  @javax.annotation.Nonnull private String terms;

  public MatchQuery() {}

  public MatchQuery boost(@javax.annotation.Nullable Float boost) {

    this.boost = boost;
    return this;
  }

  /**
   * Get boost
   *
   * @return boost
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_BOOST, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Float getBoost() {
    return boost;
  }

  @JsonProperty(value = JSON_PROPERTY_BOOST, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setBoost(@javax.annotation.Nullable Float boost) {
    this.boost = boost;
  }

  public MatchQuery column(@javax.annotation.Nullable String column) {

    this.column = column;
    return this;
  }

  /**
   * Get column
   *
   * @return column
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_COLUMN, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getColumn() {
    return column;
  }

  @JsonProperty(value = JSON_PROPERTY_COLUMN, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setColumn(@javax.annotation.Nullable String column) {
    this.column = column;
  }

  public MatchQuery fuzziness(@javax.annotation.Nullable Integer fuzziness) {

    this.fuzziness = fuzziness;
    return this;
  }

  /**
   * Get fuzziness minimum: 0
   *
   * @return fuzziness
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_FUZZINESS, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getFuzziness() {
    return fuzziness;
  }

  @JsonProperty(value = JSON_PROPERTY_FUZZINESS, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setFuzziness(@javax.annotation.Nullable Integer fuzziness) {
    this.fuzziness = fuzziness;
  }

  public MatchQuery maxExpansions(@javax.annotation.Nullable Integer maxExpansions) {

    this.maxExpansions = maxExpansions;
    return this;
  }

  /**
   * The maximum number of terms to expand for fuzzy matching. Default to 50. minimum: 0
   *
   * @return maxExpansions
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_MAX_EXPANSIONS, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getMaxExpansions() {
    return maxExpansions;
  }

  @JsonProperty(value = JSON_PROPERTY_MAX_EXPANSIONS, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setMaxExpansions(@javax.annotation.Nullable Integer maxExpansions) {
    this.maxExpansions = maxExpansions;
  }

  public MatchQuery operator(@javax.annotation.Nullable String operator) {

    this.operator = operator;
    return this;
  }

  /**
   * The operator to use for combining terms. This can be either &#x60;And&#x60; or &#x60;Or&#x60;,
   * it&#39;s &#39;Or&#39; by default. - &#x60;And&#x60;: All terms must match. - &#x60;Or&#x60;: At
   * least one term must match.
   *
   * @return operator
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_OPERATOR, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public String getOperator() {
    return operator;
  }

  @JsonProperty(value = JSON_PROPERTY_OPERATOR, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setOperator(@javax.annotation.Nullable String operator) {
    this.operator = operator;
  }

  public MatchQuery prefixLength(@javax.annotation.Nullable Integer prefixLength) {

    this.prefixLength = prefixLength;
    return this;
  }

  /**
   * The number of beginning characters being unchanged for fuzzy matching. Default to 0. minimum: 0
   *
   * @return prefixLength
   */
  @javax.annotation.Nullable
  @JsonProperty(value = JSON_PROPERTY_PREFIX_LENGTH, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public Integer getPrefixLength() {
    return prefixLength;
  }

  @JsonProperty(value = JSON_PROPERTY_PREFIX_LENGTH, required = false)
  @JsonInclude(value = JsonInclude.Include.USE_DEFAULTS)
  public void setPrefixLength(@javax.annotation.Nullable Integer prefixLength) {
    this.prefixLength = prefixLength;
  }

  public MatchQuery terms(@javax.annotation.Nonnull String terms) {

    this.terms = terms;
    return this;
  }

  /**
   * Get terms
   *
   * @return terms
   */
  @javax.annotation.Nonnull
  @JsonProperty(value = JSON_PROPERTY_TERMS, required = true)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public String getTerms() {
    return terms;
  }

  @JsonProperty(value = JSON_PROPERTY_TERMS, required = true)
  @JsonInclude(value = JsonInclude.Include.ALWAYS)
  public void setTerms(@javax.annotation.Nonnull String terms) {
    this.terms = terms;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MatchQuery matchQuery = (MatchQuery) o;
    return Objects.equals(this.boost, matchQuery.boost)
        && Objects.equals(this.column, matchQuery.column)
        && Objects.equals(this.fuzziness, matchQuery.fuzziness)
        && Objects.equals(this.maxExpansions, matchQuery.maxExpansions)
        && Objects.equals(this.operator, matchQuery.operator)
        && Objects.equals(this.prefixLength, matchQuery.prefixLength)
        && Objects.equals(this.terms, matchQuery.terms);
  }

  @Override
  public int hashCode() {
    return Objects.hash(boost, column, fuzziness, maxExpansions, operator, prefixLength, terms);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MatchQuery {\n");
    sb.append("    boost: ").append(toIndentedString(boost)).append("\n");
    sb.append("    column: ").append(toIndentedString(column)).append("\n");
    sb.append("    fuzziness: ").append(toIndentedString(fuzziness)).append("\n");
    sb.append("    maxExpansions: ").append(toIndentedString(maxExpansions)).append("\n");
    sb.append("    operator: ").append(toIndentedString(operator)).append("\n");
    sb.append("    prefixLength: ").append(toIndentedString(prefixLength)).append("\n");
    sb.append("    terms: ").append(toIndentedString(terms)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  /**
   * Convert the instance into URL query string.
   *
   * @return URL query string
   */
  public String toUrlQueryString() {
    return toUrlQueryString(null);
  }

  /**
   * Convert the instance into URL query string.
   *
   * @param prefix prefix of the query string
   * @return URL query string
   */
  public String toUrlQueryString(String prefix) {
    String suffix = "";
    String containerSuffix = "";
    String containerPrefix = "";
    if (prefix == null) {
      // style=form, explode=true, e.g. /pet?name=cat&type=manx
      prefix = "";
    } else {
      // deepObject style e.g. /pet?id[name]=cat&id[type]=manx
      prefix = prefix + "[";
      suffix = "]";
      containerSuffix = "]";
      containerPrefix = "[";
    }

    StringJoiner joiner = new StringJoiner("&");

    // add `boost` to the URL query string
    if (getBoost() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%sboost%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getBoost()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `column` to the URL query string
    if (getColumn() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%scolumn%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getColumn()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `fuzziness` to the URL query string
    if (getFuzziness() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%sfuzziness%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getFuzziness()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `max_expansions` to the URL query string
    if (getMaxExpansions() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%smax_expansions%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getMaxExpansions()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `operator` to the URL query string
    if (getOperator() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%soperator%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getOperator()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `prefix_length` to the URL query string
    if (getPrefixLength() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%sprefix_length%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getPrefixLength()), "UTF-8")
                    .replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    // add `terms` to the URL query string
    if (getTerms() != null) {
      try {
        joiner.add(
            String.format(
                java.util.Locale.ROOT,
                "%sterms%s=%s",
                prefix,
                suffix,
                URLEncoder.encode(String.valueOf(getTerms()), "UTF-8").replaceAll("\\+", "%20")));
      } catch (UnsupportedEncodingException e) {
        // Should never happen, UTF-8 is always supported
        throw new RuntimeException(e);
      }
    }

    return joiner.toString();
  }
}
