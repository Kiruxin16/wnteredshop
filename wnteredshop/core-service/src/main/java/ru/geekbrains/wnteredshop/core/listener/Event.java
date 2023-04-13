package ru.geekbrains.wnteredshop.core.listener;

import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.core.entities.Product;

public class Event {

    ProductDto product;

    public Event(ProductDto product){
        this.product=product;
    }
    @Override
    public String toString() {
        return product.getTitle();
    }
}
