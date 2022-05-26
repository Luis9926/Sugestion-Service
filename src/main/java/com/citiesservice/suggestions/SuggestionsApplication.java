package com.citiesservice.suggestions;

import com.citiesservice.suggestions.service.CityService;
import com.citiesservice.suggestions.web.CityController;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.PropertyAccessor.FIELD;


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

	private void createCities(String fileName) throws IOException {
		CityFromFile.read(fileName).forEach(cityFromFile ->
				cityService.createCity(cityFromFile.getName(), cityFromFile.getLat(),
						cityFromFile.getLon(), cityFromFile.getDetails()));
	}

	private static class CityFromFile {
		String name;
		Float lon;
		Float lat;
		Map<String,String> details;

		CityFromFile(Map<String,String> details){
			name = details.get("name");
			lon = Float.valueOf(details.get("long"));
			lat = Float.valueOf(details.get("lat"));
			details.remove("long");
			details.remove("lat");
			details.remove("name");
			this.details = details;
		}

		static List<CityFromFile> read(String fileName) throws IOException{
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
