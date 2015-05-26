package sample;

import org.springframework.context.ApplicationContext;
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
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = (ProductService) ctx.getBean("productService");
        Product product = productService.findProduct();
        System.out.println(product);
    }
}