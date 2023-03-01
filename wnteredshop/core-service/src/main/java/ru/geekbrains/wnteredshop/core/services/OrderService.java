package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.core.entities.Order;
import ru.geekbrains.wnteredshop.core.entities.OrderItem;
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
    public Order createOrder(String username) {
        CartDto cart = cartService.getCurrentCart();
        Order order = new Order();
        order.setUsername(username);
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
        return order;

    }
}
