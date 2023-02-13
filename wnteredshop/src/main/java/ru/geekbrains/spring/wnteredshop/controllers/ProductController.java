package ru.geekbrains.spring.wnteredshop.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wnteredshop.dto.ProductDto;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.exceptions.AppError;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts(){
        return productService.findAllProducts().stream()
                .map(p ->new ProductDto(p.getId(), p.getTitle(), p.getPrice())).collect(Collectors.toList());
    }

/*    @GetMapping("/{id}")
    public  ResponseEntity<?> findProductByID(@PathVariable Long id){
        Optional<Product> product =productService.findProductByID(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(),"Продукт не найден, id"+id),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get(),HttpStatus.OK);
    }*/

    @GetMapping("/{id}")
    public  ProductDto findProductByID(@PathVariable Long id){
        Product product = productService.findProductByID(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id"+id));
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice());
    }



    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable Long id){
        productService.deleteProductByID(id);
    }

}


