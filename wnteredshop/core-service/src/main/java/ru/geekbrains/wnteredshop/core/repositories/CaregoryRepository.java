package ru.geekbrains.wnteredshop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.wnteredshop.core.entities.Category;

import java.util.Optional;

public interface CaregoryRepository extends JpaRepository<Category,Long> {

    Optional<Category> findByTitle(String title);
}
