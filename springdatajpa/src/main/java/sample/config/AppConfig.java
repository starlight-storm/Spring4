package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.springdatajpa.dataaccess.PetDaoImpl;

@Configuration
public class AppConfig {

	@Bean
	public PetDaoImpl petDaoImpl() {
		return new PetDaoImpl();
	}
	
}
