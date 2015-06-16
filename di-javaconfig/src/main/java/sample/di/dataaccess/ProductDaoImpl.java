package sample.di.dataaccess;

import java.util.HashMap;
import java.util.Map;

import sample.di.business.domain.Product;
import sample.di.business.service.ProductDao;

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
