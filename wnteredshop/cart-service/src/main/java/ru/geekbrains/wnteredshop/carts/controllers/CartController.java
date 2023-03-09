package ru.geekbrains.wnteredshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.carts.converters.CartConverter;
import ru.geekbrains.wnteredshop.carts.model.Cart;
import ru.geekbrains.wnteredshop.carts.services.CartServise;

import java.net.http.HttpHeaders;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartServise cartServise;
    private final CartConverter cartConverter;

    @PostMapping("add/{id}")
    public void addToCart(@PathVariable Long id, @RequestHeader(name = "username",defaultValue = "") String username){
       cartServise.add(username,id);

    }

    @GetMapping
    public CartDto getCurrentCart(@RequestHeader(name = "username",defaultValue = "")String username){
        return cartConverter.entityToDto(cartServise.getCurrentCart(username));
    }

    @DeleteMapping
    public void clearCart(@RequestHeader(name = "username",defaultValue = "")String username){
        cartServise.clearCart(username);
    }

    @DeleteMapping("/{id}")
    public void deleteItem(@PathVariable Long id,@RequestHeader(name = "username",defaultValue = "")String username){
        cartServise.deleteItem(username,id);
    }

    @PostMapping("change")
    public void changeQuantity(
            @RequestHeader(name = "username",defaultValue = "")String username,
            @RequestParam("id") Long id,
            @RequestParam("delta") int delta
            ){
        cartServise.changeQuantity(username,id,delta);
    }

}
