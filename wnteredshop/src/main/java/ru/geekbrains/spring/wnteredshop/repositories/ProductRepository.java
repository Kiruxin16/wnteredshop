package ru.geekbrains.spring.wnteredshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.wnteredshop.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

}
