package ru.geekbrains.wnteredshop.core.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.wnteredshop.api.AppError;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.api.OrderDto;
import ru.geekbrains.wnteredshop.api.ResourceNotFoundException;
import ru.geekbrains.wnteredshop.core.entities.Order;
import ru.geekbrains.wnteredshop.core.entities.OrderItem;
import ru.geekbrains.wnteredshop.core.integratoins.CartServiceIntegration;
import ru.geekbrains.wnteredshop.core.repositories.OrderRepository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderService {
    private final ProductService  productService;
    private final CartServiceIntegration cartService;
    private final OrderRepository orderRepository;

    private final HashMap<Long,OrderItem> identityOrderItemMap;





    @Transactional
    public Order createOrder(String username) {
        CartDto cart = cartService.getCurrentCart(username);

        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cart.getTotalPrice());
        order.setItems(cart.getItems().stream().map(

                //Если так то будет просто кэш
/*                cartItemDto -> {
                    OrderItem oa;
                    if(!identityOrderItemMap.containsKey(cartItemDto.getProductId())) {
                        oa = new OrderItem(
                                productService.findProductByID(cartItemDto.getProductId()).get(),
                                order,
                                cartItemDto.getQuantity(),
                                cartItemDto.getPricePerProduct(),
                                cartItemDto.getPrice());
                        identityOrderItemMap.put(cartItemDto.getProductId(),oa);
                    }else{
                        oa=identityOrderItemMap.get(cartItemDto.getProductId());
                    }
                    return oa;*/


                cartItemDto -> {
                    OrderItem orderItem = new OrderItem(
                                productService.findProductByID(cartItemDto.getProductId()).get(),
                                order,
                                cartItemDto.getQuantity(),
                                cartItemDto.getPricePerProduct(),
                                cartItemDto.getPrice());
                    if(!identityOrderItemMap.containsKey(orderItem.getId())) {
                        identityOrderItemMap.put(orderItem.getId(), orderItem);
                    }
                    return orderItem;
                }).collect(Collectors.toList())
        );
        order.getItems().forEach(oi ->log.info("В базе найден продукт с id="+oi.getId()+": "+oi.getProduct().getTitle()));
        orderRepository.save(order);
        cartService.clear(username);
        return order;

    }

    public List<Order> getUserOrders(String username) {
        return orderRepository.findAllByUsername(username);
    }


}
