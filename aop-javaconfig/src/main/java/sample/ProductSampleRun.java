package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.aop.business.domain.Product;
import sample.aop.business.service.ProductService;
import sample.config.AppConfig;

public class ProductSampleRun {

	public static void main(String[] args) {
		ProductSampleRun productSampleRun = new ProductSampleRun();
		productSampleRun.execute();
	}

	@SuppressWarnings("resource")
	public void execute() {
		BeanFactory ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		ProductService productService = ctx.getBean(ProductService.class);

		productService.addProduct(new Product("ホチキス", 100));

		// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルとは変えています。
		// findByProductName ---> findProduct
		Product product = productService.findByProductName("ホチキス");
		System.out.println(product);
	}
}