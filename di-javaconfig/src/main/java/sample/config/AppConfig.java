package sample.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.di.business.service.ProductServiceImpl;
import sample.di.dataaccess.ProductDaoImpl;

@Configuration
public class AppConfig {
	@Bean
	public ProductServiceImpl productServices() {
		return new ProductServiceImpl();
	}
	
	@Bean
	public ProductDaoImpl productDao() {
		return new ProductDaoImpl();
	}
}