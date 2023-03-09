package ru.geekbrains.wnteredshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.carts.converters.CartConverter;
import ru.geekbrains.wnteredshop.carts.model.Cart;
import ru.geekbrains.wnteredshop.carts.services.CartServise;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServise cartServise;
    private final CartConverter cartConverter;

    @PostMapping("add/{id}")
    public void addToCart(@PathVariable Long id,@RequestHeader(name = "username",required = false) Optional<String> username){
       cartServise.add("",id);
    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(name = "username",required = false)String username){
        return cartConverter.entityToDto(cartServise.getCurrentCart(""));
    }

    @DeleteMapping
    public void clearCart(@RequestHeader(name = "username",required = false)String username){
        cartServise.clearCart(username);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id,@RequestHeader(name = "username",required = false)String username){
        cartServise.deleteItem(username,id);
    }

    @PostMapping("change")
    public void changeQuantity(
            @RequestHeader(name = "username",required = false)String username,
            @RequestParam("id") Long id,
            @RequestParam("delta") int delta
            ){
        cartServise.changeQuantity(username,id,delta);
    }

}
