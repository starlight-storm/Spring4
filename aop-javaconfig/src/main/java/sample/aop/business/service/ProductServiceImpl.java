package sample.aop.business.service;

import org.springframework.beans.factory.annotation.Autowired;

import sample.aop.business.domain.Product;

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	public void addProduct(Product product) {
		productDao.addProduct(product);
	}

	// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルとは変えています。
	// findByProductName ---> findProduct
	public Product findByProductName(String name) {
		return productDao.findProduct(name);
	}
}