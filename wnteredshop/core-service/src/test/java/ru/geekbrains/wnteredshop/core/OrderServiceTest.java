package ru.geekbrains.wnteredshop.core;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.api.CartItemDto;
import ru.geekbrains.wnteredshop.core.entities.Category;
import ru.geekbrains.wnteredshop.core.entities.Order;
import ru.geekbrains.wnteredshop.core.entities.Product;
import ru.geekbrains.wnteredshop.core.integratoins.CartServiceIntegration;
import ru.geekbrains.wnteredshop.core.repositories.OrderRepository;
import ru.geekbrains.wnteredshop.core.services.OrderService;
import ru.geekbrains.wnteredshop.core.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @MockBean
    private ProductService productService

    @MockBean
    private CartServiceIntegration cartServiceIntegration;

    @MockBean
    OrderRepository orderRepository;

    @Test
    public void createOrderTest(){

        Category category = new Category();
        category.setId(1l);
        category.setTitle("products");

        CartDto cartDto = new CartDto();
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setQuantity(1);
        cartItemDto.setProductId(19224L);
        cartItemDto.setPrice(BigDecimal.valueOf(100));
        cartItemDto.setProductTitle("Cheese");
        cartItemDto.setPricePerProduct(BigDecimal.valueOf(100));
        cartDto.setItems(new ArrayList<>(List.of(cartItemDto)));
        cartDto.setTotalPrice(BigDecimal.valueOf(100));

        Mockito.doReturn(Optional.of(cartDto)).when(cartServiceIntegration).getCurrentCart();

        Product product = new Product();
        product.setId(19224l);
        product.setTitle("Cheese");
        product.setPrice(BigDecimal.valueOf(100));
        product.setCategory(category);

        Mockito.doReturn(product).when(productService).findProductByID(19224l);

        Order order = orderService.createOrder("Bob");
        Assertions.assertEquals(order.getTotalPrice(),BigDecimal.valueOf(100));
        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());

    }
}
