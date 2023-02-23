package ru.geekbrains.wnteredshop.carts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WinteredShopCartsApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(WinteredShopCartsApplication.class, args);
/*		ProductService productService = context.getBean(ProductService.class);
		productService.findProductByID(2L);*/
    }
}
