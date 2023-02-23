package ru.geekbrains.spring.wnteredshop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.wnteredshop.entities.OrderItem;
import ru.geekbrains.spring.wnteredshop.entities.User;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {

    private Long id;

    private User user;

    private List<OrderItemDto> items;

    private String address;

    private String phone;

    private int totalPrice;


}
