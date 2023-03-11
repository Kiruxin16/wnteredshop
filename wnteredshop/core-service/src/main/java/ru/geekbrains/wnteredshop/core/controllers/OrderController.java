package ru.geekbrains.wnteredshop.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.OrderDto;
import ru.geekbrains.wnteredshop.api.OrderItemDto;
import ru.geekbrains.wnteredshop.core.converters.OrderConverter;
import ru.geekbrains.wnteredshop.core.converters.OrderItemConverter;

import ru.geekbrains.wnteredshop.core.entities.OrderItem;
import ru.geekbrains.wnteredshop.core.services.OrderService;


import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor

public class OrderController {

    private final OrderService orderService;
    private final OrderItemConverter orderItemConverter;
    private final OrderConverter orderConverter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username) {
        orderService.createOrder(username);

    }

    @GetMapping
    public List<OrderDto> getUserOrder(@RequestHeader String username){
        return orderService.getUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
