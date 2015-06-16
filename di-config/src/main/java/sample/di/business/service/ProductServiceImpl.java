package sample.di.business.service;

import sample.di.business.domain.Product;

public class ProductServiceImpl implements ProductService {
    private ProductDao productDao;

    public void setProductDao(ProductDao productDao) {
        this.productDao = productDao;
    }

	@Override
	public void addProduct(Product product) {
		productDao.addProduct(product);

	}

	@Override
	public Product findByProductName(String name) {
		 return productDao.findByProductName(name);
	}
}