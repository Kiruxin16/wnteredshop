package ru.geekbrains.wnteredshop.core.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.api.ResourceNotFoundException;
import ru.geekbrains.wnteredshop.core.entities.Category;
import ru.geekbrains.wnteredshop.core.entities.Product;

import ru.geekbrains.wnteredshop.core.services.CategoryService;

@Component
@RequiredArgsConstructor
public class ProductConverter {
    private final CategoryService categoryService;
    public ProductDto entityToDto(Product product){
        return new ProductDto(product.getId(), product.getTitle(), product.getPrice(),product.getCategory().getTitle() );
    }


    public Product dtoToEntity(ProductDto productDto){
        Product product = new Product();
        product.setPrice(productDto.getPrice());
        product.setTitle(productDto.getTitle());

        Category category = categoryService.findBytitle(productDto.getCategoryTitle()).orElseThrow(() -> new ResourceNotFoundException("category not founded"));
        product.setCategory(category);
        return product;
    }
}
