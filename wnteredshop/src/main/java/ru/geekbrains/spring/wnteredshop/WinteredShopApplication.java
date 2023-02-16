package ru.geekbrains.spring.wnteredshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

@SpringBootApplication
public class WinteredShopApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context= SpringApplication.run(WinteredShopApplication.class, args);
/*		ProductService productService = context.getBean(ProductService.class);
		productService.findProductByID(2L);*/
	}

}
