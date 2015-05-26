package sample.di.dataaccess;

import sample.di.business.domain.Product;
import sample.di.business.service.ProductDao;

public class ProductDaoImpl implements ProductDao {
    // Daoだけど簡単にするためRDBにはアクセスしてません。
    public Product findProduct(String name) {
        // Daoっぽく、製品名と価格をもったProductを検索したかのように返します。
        return new Product(name, 100);
    }
}
