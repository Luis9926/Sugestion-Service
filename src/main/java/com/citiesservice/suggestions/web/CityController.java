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

    @GetMapping("/{name}")
    public List<City> getCitiesByName(@PathVariable(value = "name") String name){
        return cityService.getCitiesByName(name);
    }
}
