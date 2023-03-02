package ru.geekbrains.wnteredshop.core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import ru.geekbrains.wnteredshop.core.entities.Product;
import ru.geekbrains.wnteredshop.core.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class ProductRepositoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void setProductRepositoryTest(){
        Product product =new Product();
        product.setTitle("Chocolate");
        product.setPrice(BigDecimal.valueOf(120));
        entityManager.persist(product);
        entityManager.flush();

        List<Product> productList = productRepository.getCostDiap(BigDecimal.valueOf(100),BigDecimal.valueOf(200));
        Assertions.assertEquals(1,productList.size());
    }

}
