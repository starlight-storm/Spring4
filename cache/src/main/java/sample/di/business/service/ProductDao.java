package sample.di.business.service;

import sample.di.business.domain.Product;

public interface ProductDao {
    Product findProduct(String name);
    void addProduct(Product product);
}
