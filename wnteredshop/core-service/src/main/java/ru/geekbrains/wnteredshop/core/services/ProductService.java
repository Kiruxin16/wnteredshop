package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.core.annotations.Time;
import ru.geekbrains.wnteredshop.core.converters.ProductConverter;
import ru.geekbrains.wnteredshop.core.entities.Product;
import ru.geekbrains.wnteredshop.core.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;
    private final ProductConverter productConverter;
    @Time
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductByID(Long id){
        return productRepository.findById(id);
    }



    public void deleteProductByID(Long id) {
        productRepository.deleteById(id);
    }

    public Product createNewProduct(ProductDto productDto){

        Product product =productConverter.dtoToEntity(productDto);
        productRepository.save(product );
        return  product;

    }
}
