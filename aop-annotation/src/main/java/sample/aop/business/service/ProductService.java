package sample.aop.business.service;

import sample.aop.business.domain.Product;

public interface ProductService {
		void addProduct(Product product);
	    Product findByProductName(String name);
}
