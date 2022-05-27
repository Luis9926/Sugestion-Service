package com.citiesservice.suggestions.tools;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.citiesservice.suggestions.domain.City;

public class ScoreMaker {
    /*
    * Clase ayudante para agregar puntajes a cada elemento de la lista de ciudades previamente filtrada
    * Se usa la distancia entre puntos geograficos como metodo de ponderacion para generar un puntaje por ciudad
     */
    public static List<City> setScores(List<City> cities, Float lat, Float lon , String name){
        cities.forEach(city -> {
            Double distance = distanceBetween(city,lat,lon);
            Double score = 1 - distance / 20;
            city.setScore(score.floatValue());
        });
        return sortByScore(cities);
    }

    /*
    * Funcion que usa la formula de distancia entre puntos usando la Latitud y Longitud
     */
    private static Double distanceBetween(City city,Float lat ,Float lon){
        return Math.sqrt(Math.pow(city.getLat() - lat, 2) + Math.pow(city.getLon() - lon, 2));
    }

    /*
    * Funcion de ordenamiento por Puntaje(Score)
     */
    private static List<City> sortByScore(List<City> cities){
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City o1, City o2) {
                return Float.compare(o2.getScore(),o1.getScore());
            }
        });
        return cities;
    }
}
