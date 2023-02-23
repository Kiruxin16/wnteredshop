package ru.geekbrains.wnteredshop.api;

import java.util.List;

public class CartDto {
    public List<CartItemDto> getItems() {
        return items;
    }

    public void setItems(List<CartItemDto> items) {
        this.items = items;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    private List<CartItemDto> items;
    private int totalPrice;
}
