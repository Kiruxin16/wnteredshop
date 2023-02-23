package ru.geekbrains.spring.wnteredshop.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.wnteredshop.dto.CategoryDto;
import ru.geekbrains.spring.wnteredshop.dto.ProductDto;
import ru.geekbrains.spring.wnteredshop.entities.Category;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.wnteredshop.services.CategoryService;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryService categoryService;
    private final ProductConverter productConverter;


    public CategoryDto entityToDto(Category category){
        CategoryDto c =new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return  c;

    }

}
