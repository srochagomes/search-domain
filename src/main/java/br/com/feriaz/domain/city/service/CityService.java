package br.com.feriaz.domain.city.service;

import br.com.feriaz.domain.city.controller.param.SearchData;
import br.com.feriaz.domain.city.datatransfer.CityDTO;
import br.com.feriaz.domain.city.datatransfer.CitySearchDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class CityService {

    private String INDEX = "city";

    @Inject
    private ObjectMapper mapper;

    @Inject
    RestClient restClient;

    public List<CityDTO> searchPhonetic(SearchData searchData) {
        try{
            Request request = new Request(
                    "GET",
                    String.format("/%s/_search", INDEX));


            CitySearchDTO citySearch = createSearch(searchData,4);
            request.setJsonEntity(mapper.writeValueAsString(citySearch));

            Response response = restClient.performRequest(request);

            String responseBody = EntityUtils.toString(response.getEntity());

            // Desserializar a resposta usando ObjectMapper
            JsonNode jsonResponse = mapper.readTree(responseBody);
            JsonNode hits = jsonResponse.path("hits").path("hits");

            // Mapear os resultados para objetos Fruit usando ObjectMapper
            List<CityDTO> results = new ArrayList<>();
            for (JsonNode hit : hits) {
                CityDTO city = mapper.treeToValue(hit.path("_source"), CityDTO.class);
                results.add(city);
            }

            return results;

        }catch ( IOException e){
            throw new RuntimeException(e);
        }
    }

    private CitySearchDTO createSearch(SearchData searchData, Integer maxResult) {
        CitySearchDTO searchDTO = new CitySearchDTO();
        searchDTO.setSize(maxResult);
        CitySearchDTO.QueryDTO queryDTO = new CitySearchDTO.QueryDTO();
        searchDTO.setQuery(queryDTO);
        CitySearchDTO.MatchDTO matchDTO = new CitySearchDTO.MatchDTO();
        queryDTO.setMatch(matchDTO);
        CitySearchDTO.PhoneticDTO phoneticDTO = new CitySearchDTO.PhoneticDTO();
        phoneticDTO.setQuery(searchData.getSearchBy());
        phoneticDTO.setFuzziness("AUTO");

        matchDTO.setMunicipio(phoneticDTO);

        return searchDTO;
    }
}
