package ru.geekbrains.spring.wnteredshop.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.spring.wnteredshop.dto.Cart;
import ru.geekbrains.spring.wnteredshop.dto.CartItem;
import ru.geekbrains.spring.wnteredshop.entities.Order;
import ru.geekbrains.spring.wnteredshop.entities.OrderItem;
import ru.geekbrains.spring.wnteredshop.entities.User;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.wnteredshop.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService  productService;
    private final CartServise cartServise;
    private final OrderRepository orderRepository;


    @Transactional
    public void createOrder(User user) {
        Cart cart = cartServise.getCurrentCart();
        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cart.getTotalPrice());
        cart.getItems().forEach(cartItem -> {
            OrderItem oi = new OrderItem();
            oi.setProduct(productService.findProductByID(cartItem.getProductId()).get());
            oi.setOrder(order);
            oi.setQuantity(cartItem.getQuantity());
            oi.setPricePerProduct(cartItem.getPricePerProduct());
            oi.setPrice(cartItem.getPrice());
        });
    }
}
