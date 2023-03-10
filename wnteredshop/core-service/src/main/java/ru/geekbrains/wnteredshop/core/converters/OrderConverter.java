package ru.geekbrains.wnteredshop.core.converters;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.wnteredshop.api.OrderDto;
import ru.geekbrains.wnteredshop.core.entities.Order;


import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        orderDto.setTotalPrice(order.getTotalPrice());
        orderDto.setUsername(order.getUsername());
        orderDto.setItems(order.getItems().stream().map(orderItemConverter::entityToDto).collect(Collectors.toList()));
        return orderDto;
    }
}
