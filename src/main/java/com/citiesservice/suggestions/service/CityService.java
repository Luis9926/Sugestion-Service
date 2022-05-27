package com.citiesservice.suggestions.service;

import com.citiesservice.suggestions.domain.City;
import com.citiesservice.suggestions.repository.CityRepository;
import com.citiesservice.suggestions.tools.ScoreMaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CityService {
    /*
    * Servicio de datos que utiliza el CrudRepository de Ciudades
     */
    private CityRepository cityRepository;

    @Autowired
    public CityService(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public City createCity(String name, Float lat, Float lon,
                           Map<String,String> details){
        return cityRepository.save(new City(name,lat,lon,details));
    }

    /**
     * Metodo para obtener ciudades solo por nombre
     * @param name es el nombre de la ciudad
     */
    public List<City> getCitiesByName(String name){
        return cityRepository.findByNameLikeIgnoreCase(name);
    }

    /**
     * Metodo para obtener ciudades por nombre, latitud y longitud
     * Se usan 2 variables de latitud y 2 de longuitud para representar el rango
     * de busqueda por latidud y longitud, se hace la busqueda y luego se crean los puntajes
     *
     * @param name es el nombre de la ciudad
     * @param lat latitud
     * @param lon longitud
     */


    public List<City> getCitiesByLatAndLong(String name, Float lat, Float lon){

        int queryRange = 8;
        Float lat1 = lat + queryRange;
        Float lon1 = lon - queryRange;
        lat = lat- queryRange;
        lon = lon + queryRange;
        List<City> data = cityRepository.findByNameAndLatAndLong(name,lat,lon,lat1,lon1);

        return ScoreMaker.setScores(data,lat + queryRange,lon- queryRange,name);
    }

}
