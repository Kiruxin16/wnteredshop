package ru.geekbrains.spring.wnteredshop.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.wnteredshop.dto.ProductDto;
import ru.geekbrains.spring.wnteredshop.entities.Category;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.wnteredshop.services.CategoryService;

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
