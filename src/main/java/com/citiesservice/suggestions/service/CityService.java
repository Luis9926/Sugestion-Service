package com.citiesservice.suggestions.service;

import com.citiesservice.suggestions.domain.City;
import com.citiesservice.suggestions.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityService {
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(String name, Float lat, Float lon,
                           Map<String,String> details){
        return cityRepository.save(new City(name,lat,lon,details));
    }

    public List<City> getCitiesByName(String name){
        return cityRepository.findByNameContaining(name);
    }
}
