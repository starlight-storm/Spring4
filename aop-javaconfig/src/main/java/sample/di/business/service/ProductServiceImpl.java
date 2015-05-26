package sample.di.business.service;

import org.springframework.beans.factory.annotation.Autowired;

import sample.di.business.domain.Product;

public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public Product findProduct() {
        // それっぽく、検索条件をいれてます
        return productDao.findProduct("ホチキス");
    }
}