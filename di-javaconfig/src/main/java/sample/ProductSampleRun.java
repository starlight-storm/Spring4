package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import sample.config.AppConfig;
import sample.di.business.domain.Product;
import sample.di.business.service.ProductService;

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

        Product product = productService.findByProductName("ホチキス");
        System.out.println(product);

    }
}