package ru.geekbrains.wnteredshop.core.converters;


import org.springframework.stereotype.Component;
import ru.geekbrains.wnteredshop.api.CartItemDto;
import ru.geekbrains.wnteredshop.api.OrderItemDto;
import ru.geekbrains.wnteredshop.core.entities.OrderItem;


@Component
public class OrderItemConverter {

    public OrderItemDto entityToDto(OrderItem orderItem){
        OrderItemDto orderItemDto =new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setPrice(orderItem.getPrice());
        orderItemDto.setPricePerProduct(orderItem.getPricePerProduct());
        orderItemDto.setProductTitle(orderItem.getProduct().getTitle());
        orderItemDto.setQuantity(orderItem.getQuantity());
        return orderItemDto;
    }
}
