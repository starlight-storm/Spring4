package sample.aop.dataaccess;

import java.util.HashMap;
import java.util.Map;

import sample.aop.business.domain.Product;
import sample.aop.business.service.ProductDao;

public class ProductDaoImpl implements ProductDao {
    // Daoだけど簡単にするためRDBにはアクセスしてません。
	// MapはRDBの替り
	private Map<String, Product> storage = new HashMap<String, Product>();

    public Product findByProductName(String name) {
        return storage.get(name);
    }

	public void addProduct(Product product) {
		storage.put(product.getName(), product);
	}
}
