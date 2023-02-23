package ru.geekbrains.wnteredshop.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WinteredShopCoreApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(WinteredShopCoreApplication.class, args);
/*		ProductService productService = context.getBean(ProductService.class);
		productService.findProductByID(2L);*/
	}

}
