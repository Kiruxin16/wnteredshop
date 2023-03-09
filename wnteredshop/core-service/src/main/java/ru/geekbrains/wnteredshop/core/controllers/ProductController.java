package ru.geekbrains.wnteredshop.core.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.api.ResourceNotFoundException;
import ru.geekbrains.wnteredshop.core.converters.ProductConverter;
import ru.geekbrains.wnteredshop.core.entities.Product;

import ru.geekbrains.wnteredshop.core.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
//@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public Page<ProductDto> findAllProducts(@RequestParam(name = "p",defaultValue = "1")Integer page,
                                            @RequestParam(name = "min_cost",required = false)BigDecimal minCost,
                                            @RequestParam(name = "max_cost",required = false)BigDecimal maxCost,
                                            @RequestParam(name = "title_part",required = false)String partTitle
    ){
        if(page<1){
            page=1;
        }

        return productService.find(page,minCost,maxCost,partTitle);
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
        return productConverter.entityToDto(product);
    }

    @PostMapping("/{id}")
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return  productDto;
    }


    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable Long id){
        productService.deleteProductByID(id);
    }

}


