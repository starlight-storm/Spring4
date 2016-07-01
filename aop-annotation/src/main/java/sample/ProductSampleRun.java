package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.aop.business.domain.Product;
import sample.aop.business.service.ProductService;

public class ProductSampleRun {

	public static void main(String[] args) {
		ProductSampleRun productSampleRun = new ProductSampleRun();
		productSampleRun.execute();
	}

	@SuppressWarnings("resource")
	public void execute() {
		BeanFactory ctx = new ClassPathXmlApplicationContext("/sample/config/applicationContext.xml");
		ProductService productService = ctx.getBean(ProductService.class);

		productService.addProduct(new Product("ホチキス", 100));

		// 注意：説明の都合上、DAOのメソッド名を"2.1 SpringのDI"のサンプルと変えています。
		// findByProductName ---> findProduct
		Product product = productService.findByProductName("ホチキス");
		System.out.println(product);
	}
}