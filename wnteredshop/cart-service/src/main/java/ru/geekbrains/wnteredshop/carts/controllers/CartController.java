package ru.geekbrains.wnteredshop.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.api.StringResponse;
import ru.geekbrains.wnteredshop.carts.converters.CartConverter;
import ru.geekbrains.wnteredshop.carts.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartServise;
    private final CartConverter cartConverter;

    @GetMapping("/generate_uuid")
    public StringResponse generateUuid(){
        return new StringResponse(UUID.randomUUID().toString());
    }


    @PostMapping("/{uuid}/add/{id}")
    public void addToCart(@PathVariable Long id, @RequestHeader(name = "username",required = false) String username,@PathVariable("uuid") String uuid){
       cartServise.add(username,uuid,id);

    }

    @GetMapping("/{uuid}")
    public CartDto getCurrentCart(@RequestHeader(name = "username",required = false)String username,@PathVariable("uuid") String uuid){
        return cartConverter.entityToDto(cartServise.getCurrentCart(username,uuid));
    }

    @DeleteMapping("/{uuid}")
    public void clearCart(@RequestHeader(name = "username",required = false)String username,@PathVariable("uuid") String uuid){
        cartServise.clearCart(username,uuid);
    }

    @DeleteMapping("/{uuid}/{id}")
    public void deleteItem(@PathVariable Long id,@RequestHeader(name = "username",required = false)String username,@PathVariable("uuid") String uuid){
        cartServise.deleteItem(username,uuid,id);
    }

    @PostMapping("/{uuid}/change")
    public void changeQuantity(
            @RequestHeader(name = "username",required = false)String username,
            @RequestParam("id") Long id,
            @RequestParam("delta") int delta,
            @PathVariable("uuid") String uuid
            ){
        cartServise.changeQuantity(username,uuid,id,delta);
    }



}


