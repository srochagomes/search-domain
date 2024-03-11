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


            String citySearch = createSearch(searchData);
            request.setJsonEntity(citySearch);

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

    private String createSearch(SearchData searchData) {

        return """
       {
         "query": {
           "bool": {
             "should": [
               {
                 "multi_match": {
                   "query": "%s",
                   "fields": [
                     "municipio^2",
                     "nomeEstado^2",
                     "paisCamel^2",
                     "regiaoTuristica^2",
                     "municipio.phonetic^2",
                     "nomeEstado.phonetic^2",
                     "paisCamel.phonetic^2",
                     "regiaoTuristica.phonetic^2",
                     "municipio.lowercase^2",
                     "nomeEstado.lowercase^2",
                     "paisCamel.lowercase^2",
                     "regiaoTuristica.lowercase^2"
                   ],
                   "type": "best_fields",
                   "operator": "or",
                   "tie_breaker": 0.3  // Adicionando o tie_breaker
                 }
               },
               {
                 "multi_match": {
                   "query": "%s",
                   "fields": [
                     "municipio",
                     "nomeEstado",
                     "paisCamel",
                     "regiaoTuristica",
                     "municipio.phonetic^2",
                     "nomeEstado.phonetic^2",
                     "paisCamel.phonetic^2",
                     "regiaoTuristica.phonetic^2",
                     "municipio.lowercase^2",
                     "nomeEstado.lowercase^2",
                     "paisCamel.lowercase^2",
                     "regiaoTuristica.lowercase^2"
                   ],
                   "fuzziness": "AUTO"
                 }
               }
             ]
           }
         },
         "size": %d,
         "sort": [
           {
             "type": { "order": "desc" }  // Classificação por type em ordem decrescente
           },
         
           {
             "_score": { "order": "desc" }  // Classificação por score (relevância)
           }
         ]
       }
       """.formatted(searchData.getSearchBy(), searchData.getSearchBy(), searchData.getSize());


    }
}
