package com.citiesservice.suggestions;

import com.citiesservice.suggestions.service.CityService;
import com.citiesservice.suggestions.tools.FromFile;
import com.citiesservice.suggestions.web.CityController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;



@SpringBootApplication
public class SuggestionsApplication implements CommandLineRunner {

	@Value("${ec.importfile}")
	private String dataFile;

	@Autowired
	private CityController cityController;
	@Autowired
	private CityService cityService;


	public static void main(String[] args) {
		SpringApplication.run(SuggestionsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createCities(dataFile);
	}

	/*
	 * Creación de entidades City desde un archivo .JSON
	 */
	private void createCities(String fileName) throws IOException {
		FromFile.CityFromFile.read(fileName).forEach(cityFromFile ->
				cityService.createCity(cityFromFile.getName(), cityFromFile.getLat(),
						cityFromFile.getLon(), cityFromFile.getDetails()));
	}



}
