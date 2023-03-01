package ru.geekbrains.wnteredshop.api;

import java.math.BigDecimal;
import java.util.List;

public class CartDto {
    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    private List<CartItemDto> items;
    private BigDecimal totalPrice;
}
