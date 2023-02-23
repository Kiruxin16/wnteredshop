package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.core.entities.Category;
import ru.geekbrains.wnteredshop.core.repositories.CaregoryRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CaregoryRepository caregoryRepository;

    public Optional<Category> findBytitle(String title){
        return caregoryRepository.findByTitle(title);
    }
}
