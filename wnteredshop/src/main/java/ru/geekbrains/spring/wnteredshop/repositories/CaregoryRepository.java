package ru.geekbrains.spring.wnteredshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.wnteredshop.entities.Category;

import java.util.Optional;

public interface CaregoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);
}
