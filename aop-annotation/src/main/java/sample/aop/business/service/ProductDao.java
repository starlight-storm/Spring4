package sample.aop.business.service;

import sample.aop.business.domain.Product;

public interface ProductDao {
	void addProduct(Product product);
    Product findByProductName(String name);
}
