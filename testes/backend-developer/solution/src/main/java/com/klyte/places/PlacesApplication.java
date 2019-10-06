package com.klyte.places;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PlacesApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlacesApplication.class, args);
	}

	@Bean
	public ObjectMapper getObjectMapper(){
		ObjectMapper om = new ObjectMapper();
		om.registerModule(new JavaTimeModule());
		return om;
	}
}
