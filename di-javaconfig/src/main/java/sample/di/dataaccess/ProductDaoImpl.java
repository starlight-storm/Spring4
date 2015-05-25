package sample.di.dataaccess;

import org.springframework.stereotype.Component;

import sample.di.business.domain.Product;
import sample.di.business.service.ProductDao;

@Component
public class ProductDaoImpl implements ProductDao {
    // Daoだけど簡単にするためRDBにはアクセスしてません。
    public Product findProduct() {
        // Daoっぽく、製品名と価格をもったProductを検索したかのように返します。
        return new Product("ホチキス", 100);
    }
}
