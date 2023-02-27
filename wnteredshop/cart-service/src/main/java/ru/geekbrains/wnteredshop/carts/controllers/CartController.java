package ru.geekbrains.wnteredshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.carts.converters.CartConverter;
import ru.geekbrains.wnteredshop.carts.model.Cart;
import ru.geekbrains.wnteredshop.carts.services.CartServise;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {
    private final CartServise cartServise;
    private final CartConverter cartConverter;

    @PostMapping("add/{id}")
    public void addToCart(@PathVariable Long id){
       cartServise.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart(){
        return cartConverter.entityToDto(cartServise.getCurrentCart());
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
