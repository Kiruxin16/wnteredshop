package ru.geekbrains.wnteredshop.carts.converters;


import org.springframework.stereotype.Component;
import ru.geekbrains.wnteredshop.api.CartItemDto;
import ru.geekbrains.wnteredshop.carts.model.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto modelToDto(CartItem cartItem){
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        return cartItemDto;
    }
}
