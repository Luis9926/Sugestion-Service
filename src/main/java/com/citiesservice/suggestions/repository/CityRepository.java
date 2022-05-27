package com.citiesservice.suggestions.repository;

import com.citiesservice.suggestions.domain.City;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CityRepository extends CrudRepository<City,String> {
    /*
    * Spring data permite crear metodos de query utilizando el nombre de la funcion
    * para especificar el Query de MongoDB
     */

    /*
     * Funcion para obtener documentos que contengan algo parecido al string nombre,
     * sin importar las mayusculas o minusculas
     */
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

