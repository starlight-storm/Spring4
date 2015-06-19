package sample;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import sample.di.business.domain.Product;
import sample.di.business.service.ProductService;

public class ProductSampleRun {

    public static void main(String[] args) {
        ProductSampleRun productSampleRun = new ProductSampleRun();
        productSampleRun.execute();
    }

    @SuppressWarnings("resource")
	public void execute() {
    	// BeanFactoryはApplicationContextに書替えてもOK
        BeanFactory ctx = new ClassPathXmlApplicationContext(
                "/sample/config/applicationContext.xml");
        ProductService productService = ctx.getBean(ProductService.class);

        productService.addProduct(new Product("ホチキス", 100));

        Product product = productService.findByProductName("ホチキス");
        System.out.println(product);

    }
}