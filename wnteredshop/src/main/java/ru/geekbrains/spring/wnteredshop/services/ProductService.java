package ru.geekbrains.spring.wnteredshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wnteredshop.annotations.Time;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

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
}
