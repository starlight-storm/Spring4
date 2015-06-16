package sample.di.business.service;

import sample.di.business.domain.Product;

public interface ProductDao {
	void addProduct(Product product);
    Product findByProductName(String name);
}
