package com.citiesservice.suggestions.web;

import com.citiesservice.suggestions.domain.City;
import com.citiesservice.suggestions.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/cities/suggestions")
public class CityController {
    private CityService cityService;

    @Autowired
    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    protected CityController(){

    }

    /*
    * Metodo para obtener datos de ciudades usando 1 parametro obligatorio (nombre)
    * y 2 parametros opcionales (lat, long), se utiliza un servicio de datos intermediario
    * para generar la respuesta
     */

    @GetMapping
    public List<City> getCitiesByName(@RequestParam(name = "name") String name,
                                      @RequestParam(required = false,name = "lat")Float lat,
                                      @RequestParam(required = false,name = "long")Float lon){
        if(lat != null && lon != null){
            return cityService.getCitiesByLatAndLong(name, lat, lon);
        }
        return cityService.getCitiesByName(name);
    }
}
