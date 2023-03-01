package ru.geekbrains.wnteredshop.core.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.wnteredshop.core.entities.Category;
import ru.geekbrains.wnteredshop.core.services.CategoryService;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryConverter {
    private final CategoryService categoryService;
    private final ProductConverter productConverter;


/*    public CategoryDto entityToDto(Category category){
        CategoryDto c =new CategoryDto();
        c.setId(category.getId());
        c.setTitle(category.getTitle());
        c.setProducts(category.getProducts().stream().map(productConverter::entityToDto).collect(Collectors.toList()));
        return  c;

    }*/

}
