package com.citiesservice.suggestions.repository;

import com.citiesservice.suggestions.domain.City;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City,String> {
    List<City> findByNameLikeIgnoreCase(String name);


    @Query(value = "{$and:[" +
            "{'name': /?0/}," +
            "{'lat': {$gt: ?1}}" +
            "{'lat': {$lt: ?3}}"+
            "{'lon': {$lt: ?2}}"+
            "{'lon': {$gt: ?4}}"+
            "]}")
    List<City> findByNameAndLatAndLong(
            @Param("name")String name,@Param("lat") Float lat,
            @Param("lon") Float lon, @Param("lat1")Float lat1 ,@Param("lon1") Float lon1);
}

