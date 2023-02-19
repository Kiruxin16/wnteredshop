package ru.geekbrains.spring.wnteredshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wnteredshop.annotations.Time;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.services.repositories.ProductRepository;
import ru.geekbrains.spring.wnteredshop.soap.Prod;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {


    public static final Function<Product,Prod> functionProducttoProd = pr -> {
        Prod p = new Prod();
        p.setId(pr.getId());
        p.setTitle(p.getTitle());
        p.setPrice(pr.getPrice());
        return p;
    };

    private final ProductRepository productRepository;


    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findProductByID(Long id){
        return productRepository.findById(id);
    }


    public List<Prod> getAllProds(){
        return productRepository.findAll().stream().map(functionProducttoProd).collect(Collectors.toList());
    }

    public void deleteProductByID(Long id) {
        productRepository.deleteById(id);
    }
}
