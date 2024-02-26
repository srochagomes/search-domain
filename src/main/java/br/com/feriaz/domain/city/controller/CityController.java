package br.com.feriaz.domain.city.controller;

import br.com.feriaz.domain.city.controller.param.SearchData;
import br.com.feriaz.domain.city.datatransfer.CityDTO;
import br.com.feriaz.domain.city.service.CityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/cities")
public class CityController {

    @Inject
    private CityService cityService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CityDTO> search(SearchData searchData) {
        return cityService.searchPhonetic(searchData);
    }
}
