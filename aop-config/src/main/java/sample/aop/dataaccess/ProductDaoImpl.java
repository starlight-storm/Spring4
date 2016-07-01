package sample.aop.dataaccess;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import sample.aop.business.domain.Product;
import sample.aop.business.service.ProductDao;

@Component
public class ProductDaoImpl implements ProductDao {
	// Daoだけど簡単にするためRDBにはアクセスしてません。
	// MapはRDBの替り
	private Map<String, Product> storage = new HashMap<String, Product>();

	// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルとは変えています。
	// findByProductName ---> findProduct
	public Product findProduct(String name) {
		return storage.get(name);
	}

	public void addProduct(Product product) {
		storage.put(product.getName(), product);
	}
}
