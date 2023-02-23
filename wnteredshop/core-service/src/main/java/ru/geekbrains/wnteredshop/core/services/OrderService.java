package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.core.entities.Order;
import ru.geekbrains.wnteredshop.core.entities.OrderItem;
import ru.geekbrains.wnteredshop.core.entities.User;
import ru.geekbrains.wnteredshop.core.integratoins.CartServiceIntegration;
import ru.geekbrains.wnteredshop.core.repositories.OrderRepository;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService  productService;
    private final CartServiceIntegration cartService;
    private final OrderRepository orderRepository;


    @Transactional
    public void createOrder(User user) {
        CartDto cart = cartService.getCurrentCart().get();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        order.setItems(cart.getItems().stream().map(
                cartItemDto -> new OrderItem(
                        productService.findProductByID(cartItemDto.getProductId()).get(),
                        order,
                        cartItemDto.getQuantity(),
                        cartItemDto.getPricePerProduct(),
                        cartItemDto.getPrice())
                ).collect(Collectors.toList())
        );
        orderRepository.save(order);
        cartService.clear();

    }
}
