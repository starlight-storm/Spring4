package sample.di.business.service;

import sample.di.business.domain.Product;

public interface ProductDao {
    Product getProduct(String name);
}
