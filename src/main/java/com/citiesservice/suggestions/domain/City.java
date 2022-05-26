package com.citiesservice.suggestions.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document
public class City {

    @Id
    private String id;

    @Indexed
    private String name;

    private Float lat;

    private Float lon;

    private Map<String,String> details;

    private Long score;

    public City(String name, Float lat, Float lon, Map<String, String> details) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.details = details;
    }

    protected City(){

    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Float getLat() {
        return lat;
    }

    public Float getLon() {
        return lon;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }
}
