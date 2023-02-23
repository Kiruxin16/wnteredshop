package ru.geekbrains.spring.wnteredshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wnteredshop.annotations.Time;
import ru.geekbrains.spring.wnteredshop.entities.Category;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.repositories.CaregoryRepository;
import ru.geekbrains.spring.wnteredshop.repositories.ProductRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CaregoryRepository caregoryRepository;

    public Optional<Category> findBytitle(String title){
        return caregoryRepository.findByTitle(title);
    }
}
