package sample.di.business.service;

import sample.di.business.domain.Product;

public interface ProductService {
		void addProduct(Product product);
	    Product findByProductName(String name);
}
