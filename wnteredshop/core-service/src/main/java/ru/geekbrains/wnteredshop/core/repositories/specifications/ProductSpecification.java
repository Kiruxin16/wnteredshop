package ru.geekbrains.wnteredshop.core.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.geekbrains.wnteredshop.core.entities.Product;

import java.math.BigDecimal;


public class ProductSpecification {
    public static Specification<Product> priceGreaterThanOrEqualTo(BigDecimal price){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),price)));

    }

    public static Specification<Product> priceLessThanOrEqualTo(BigDecimal price){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("price"),price)));

    }

    public static Specification<Product> titleLike(String titlePart){
        return (((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("title"),String.format("%%%s%%",titlePart))));

    }
}
