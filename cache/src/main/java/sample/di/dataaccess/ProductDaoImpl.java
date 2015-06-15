package sample.di.dataaccess;

import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import sample.di.business.domain.Product;
import sample.di.business.service.ProductDao;

@Repository
public class ProductDaoImpl implements ProductDao {

	// RDBの替り
	private Map<String, Product> storage = new HashMap<String, Product>();

    // Daoだけど簡単にするためRDBにはアクセスしてません。
	@Cacheable(value = "area")
    public Product findProduct(String name) {
    	slowly(); // 故意に遅らせる
        return storage.get(name);
    }

	//@CacheEvict(value = "product", allEntries = true) 全キャッシュエントリをクリア
	@CacheEvict(value = "area", key = "#product.name")
	public void addProduct(Product product) {
		storage.put(product.getName(), product);
	}

    private void slowly() {
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}