package ru.geekbrains.wnteredshop.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
@Tag(name = "Заказы",description = "Методы работы со страницей заказов")
public class OrderController {

    private final OrderService orderService;
    private final OrderConverter orderConverter;

    @Operation(
            summary = "Запрос на оформление заказа из корзины продуктов",
            responses = {
                    @ApiResponse(
                            description = "Заказ оформлен", responseCode = "200"
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewOrder(@RequestHeader String username) {
        orderService.createOrder(username);

    }
    @Operation(
            summary = "Запрос на получение списка заказов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = List.class))
                    )
            }
    )
    @GetMapping
    public List<OrderDto> getUserOrder(@RequestHeader String username){
        return orderService.getUserOrders(username).stream().map(orderConverter::entityToDto).collect(Collectors.toList());
    }
}
