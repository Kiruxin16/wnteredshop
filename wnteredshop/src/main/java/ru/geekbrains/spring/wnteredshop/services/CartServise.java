package ru.geekbrains.spring.wnteredshop.services;


import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wnteredshop.dto.Cart;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServise {
    private final ProductService productService;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart= new Cart();
    }

    public  Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        Product product = productService.findProductByID(productId)
                .orElseThrow(()->new ResourceNotFoundException("Продукт с id "+productId+" не найден."));
        tempCart.add(product);

    }
}
