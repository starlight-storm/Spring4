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

    public void execute() {
        BeanFactory ctx = new ClassPathXmlApplicationContext(
                "/sample/config/applicationContext.xml");
        ProductService productService = ctx.getBean(ProductService.class);
        Product product = productService.findProduct();
        System.out.println(product);
    }
}