package com.citiesservice.suggestions.repository;

import com.citiesservice.suggestions.domain.City;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CityRepository extends CrudRepository<City,String> {
    List<City> findByNameContaining(String name);
}
