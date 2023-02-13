package ru.geekbrains.spring.wnteredshop.services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.wnteredshop.beans.Cart;
import ru.geekbrains.spring.wnteredshop.entities.Product;

import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class CartServise {

    private final Cart cart;

    public List<Product> getCartList(){
        return cart.getCartList();
    }

    public void addToCart(Product product){
        cart.getCartList().add(product);
    }

}
