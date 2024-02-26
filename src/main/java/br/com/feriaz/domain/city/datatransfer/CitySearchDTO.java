package br.com.feriaz.domain.city.datatransfer;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.runtime.annotations.RegisterForReflection;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@RegisterForReflection
public class CitySearchDTO {
    private int size;
    private QueryDTO query;

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public QueryDTO getQuery() {
        return query;
    }

    public void setQuery(QueryDTO query) {
        this.query = query;
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @RegisterForReflection
    public static class QueryDTO {
        private MatchDTO match;

        public MatchDTO getMatch() {
            return match;
        }

        public void setMatch(MatchDTO match) {
            this.match = match;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @RegisterForReflection
    public static class MatchDTO {
        private PhoneticDTO municipio;

        public PhoneticDTO getMunicipio() {
            return municipio;
        }

        public void setMunicipio(PhoneticDTO municipio) {
            this.municipio = municipio;
        }
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    @RegisterForReflection
    public static class PhoneticDTO {
        private String query;
        private String fuzziness;

        public String getQuery() {
            return query;
        }

        public void setQuery(String query) {
            this.query = query;
        }

        public String getFuzziness() {
            return fuzziness;
        }

        public void setFuzziness(String fuzziness) {
            this.fuzziness = fuzziness;
        }
    }
}