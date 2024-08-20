package com.example.carrental;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class CarrentalApplication {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv.load();


		System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
		System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		SpringApplication.run(CarrentalApplication.class, args);
	}
    @Bean
	public ModelMapper model(){
		return new ModelMapper();   
	}
	
}
