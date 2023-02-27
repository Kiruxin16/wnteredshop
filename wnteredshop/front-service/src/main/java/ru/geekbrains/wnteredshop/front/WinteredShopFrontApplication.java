package ru.geekbrains.wnteredshop.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class WinteredShopFrontApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context= SpringApplication.run(WinteredShopFrontApplication.class);
    }
}
