package sample.di.business.service;

import sample.di.business.domain.Product;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public Product findProduct() {
        // それっぽく、検索条件をいれてます
        return productDao.findProduct("ホチキス");
    }
    
    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }
}