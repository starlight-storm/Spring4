package sample.config;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import sample.di.business.service.ProductServiceImpl;
import sample.di.dataaccess.ProductDaoImpl;

@Configuration
public class AppConfig {
	@Bean(autowire = Autowire.BY_TYPE)
	public ProductServiceImpl productServices() {
		return new ProductServiceImpl();
	}

	@Bean
	public ProductDaoImpl productDao() {
		return new ProductDaoImpl();
	}
}