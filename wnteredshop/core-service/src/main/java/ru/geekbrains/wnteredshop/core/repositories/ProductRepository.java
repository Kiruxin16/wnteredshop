package ru.geekbrains.wnteredshop.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.geekbrains.wnteredshop.core.entities.Product;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>, JpaSpecificationExecutor<Product> {

    @Query("select p from Product p where p.price >= :minPrice and p.price <= :maxPrice" )
    List<Product> getCostDiap(BigDecimal minPrice, BigDecimal maxPrice);
}
