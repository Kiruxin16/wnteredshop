package ru.geekbrains.spring.wnteredshop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> findAllProducts(){
        return productService.findAllProducts();
    }

    @GetMapping("/{id}")
    public Product findProductByID(@PathVariable Long id){
        return productService.findProductByID(id).get();
    }

    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable Long id){
        productService.deleteProductByID(id);
    }

}


