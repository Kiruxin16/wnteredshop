package ru.geekbrains.spring.wnteredshop.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.wnteredshop.entities.Order;
import ru.geekbrains.spring.wnteredshop.entities.User;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {

    private Long id;


    private String productTitle;

    private String orderTitle;

    private int quantity;

    private int pricePerProduct;

    private int price;
}
