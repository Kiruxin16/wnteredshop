package ru.geekbrains.wnteredshop.core.controllers;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.AppError;
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
@Tag(name = "Продукты",description = "Методы работы с продусктами")
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;

    @Operation(
            summary = "Запрос на получение страницы продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = Page.class))
                    )

            }
    )
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


    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public  ProductDto findProductByID(@PathVariable @Parameter(description = "Идентификатор продукта", required = true) Long id){
        Product product = productService.findProductByID(id).orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id"+id));
        return productConverter.entityToDto(product);
    }

    @Operation(
            summary = "Запрос на создание нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )

            }
    )
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        Product product = productService.createNewProduct(productDto);
        return  productDto;
    }


    @DeleteMapping("/{id}")
    public void deleteProductByID(@PathVariable Long id){
        productService.deleteProductByID(id);
    }

}


