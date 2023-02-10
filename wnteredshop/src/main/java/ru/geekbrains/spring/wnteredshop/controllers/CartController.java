package ru.geekbrains.spring.wnteredshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.services.CartServise;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServise cartServise;
    private final ProductService productService;
    @GetMapping
    public List<Product> getAllProductsFromCart(){
        return cartServise.getCartList();
    }

    @PostMapping("/{id}")
    public void addToCart(@PathVariable Long id){
        Product product =productService.findProductByID(id).get();
        cartServise.addToCart(product);
    }
}
