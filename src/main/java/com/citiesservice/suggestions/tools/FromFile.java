package com.citiesservice.suggestions.tools;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;

public class FromFile {
    /*
    * Clase ayudante estatica para convertir un archivo .JSON a un Stream de datos en una List<City>
    * Solo se especifican los datos que necesita la aplicacion para filtrar : name,lat,long
    * El resto de datos se guardan en un mapa de strings
     */
    public static class CityFromFile {
        String name;
        Float lon;
        Float lat;
        Map<String,String> details;

        /*
        * Constructor de Objeto CityFromFile
         */
        CityFromFile(Map<String,String> details){
            name = details.get("name");
            lon = Float.valueOf(details.get("long"));
            lat = Float.valueOf(details.get("lat"));
            details.remove("long");
            details.remove("lat");
            details.remove("name");
            this.details = details;
        }

        /*
        * Funcion estatica lectora de archivos
         */
       public static List<CityFromFile> read(String fileName) throws IOException {
            List<Map<String,String>> records = new ObjectMapper().setVisibility(FIELD,ANY)
                    .readValue(new FileInputStream(fileName),
                            new TypeReference<List<Map<String, String>>>() {});
            return records.stream().map(CityFromFile::new)
                    .collect(Collectors.toList());
        }

        public String getName() {
            return name;
        }

        public Float getLon() {
            return lon;
        }

        public Float getLat() {
            return lat;
        }

        public Map<String, String> getDetails() {
            return details;
        }
    }
}
