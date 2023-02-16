package ru.geekbrains.spring.wnteredshop.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.wnteredshop.dto.Cart;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.services.CartServise;
import ru.geekbrains.spring.wnteredshop.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServise cartServise;

    @PostMapping("add/{id}")
    public void addToCart(@PathVariable Long id){
       cartServise.add(id);
    }

    @GetMapping
    public Cart getCurrentCart(){
        return cartServise.getCurrentCart();
    }

    @DeleteMapping
    public void clearCart(){
        cartServise.clearCart();
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id){
        cartServise.deleteItem(id);
    }

    @PostMapping("change")
    public void changeQuantity(@RequestParam("id") Long id,@RequestParam("delta") int delta ){
        cartServise.changeQuantity(id,delta);
    }

}
