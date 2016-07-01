package sample.aop.business.service;

import sample.aop.business.domain.Product;

public interface ProductDao {
	void addProduct(Product product);

	// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルとは変えています。
	// findByProductName ---> findProduct
	Product findProduct(String name);
}
