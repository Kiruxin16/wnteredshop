package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.core.annotations.Time;
import ru.geekbrains.wnteredshop.core.converters.ProductConverter;
import ru.geekbrains.wnteredshop.core.entities.Product;
import ru.geekbrains.wnteredshop.core.repositories.ProductCachedRepository;
import ru.geekbrains.wnteredshop.core.repositories.ProductRepository;
import ru.geekbrains.wnteredshop.core.repositories.specifications.ProductSpecification;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    private final ProductCachedRepository productCachedRepository;
    private final ProductConverter productConverter;
    //@Time
    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Page<ProductDto> find(Integer p, BigDecimal minPrice, BigDecimal maxPrice, String partTitle){
        Specification<Product> spec = Specification.where(null);
        if(minPrice!=null){
            spec=spec.and(ProductSpecification.priceGreaterThanOrEqualTo(minPrice));
        }
        if(maxPrice!=null){
            spec=spec.and(ProductSpecification.priceLessThanOrEqualTo(maxPrice));
        }
        if(partTitle!=null){
            spec=spec.and(ProductSpecification.titleLike(partTitle));
        }

        return productRepository.findAll(spec, PageRequest.of(p-1,5)).map(productConverter::entityToDto);
    }

    public Optional<Product> findProductByID(Long id){
        return productCachedRepository.findById(id);
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
