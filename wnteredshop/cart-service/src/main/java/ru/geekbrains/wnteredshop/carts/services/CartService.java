package ru.geekbrains.wnteredshop.carts.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;
import ru.geekbrains.wnteredshop.carts.model.CartItem;


import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    @Value("{cart-service.cart-prefix}")
    private String cartPrefix;

    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String,Object> redisTemplate;


    private String getCartKeyFromUuid(String uuid){
        return cartPrefix+uuid;
    }

    public Cart getCurrentCart(String username,String uuid){
        String guestCartKey= getCartKeyFromUuid(uuid);
        if (!redisTemplate.hasKey(guestCartKey)){
            redisTemplate.opsForValue().set(guestCartKey,new Cart());
        }
        if(username!=null){
            String userCartKey =getCartKeyFromUuid(username);
            if(!redisTemplate.hasKey(userCartKey)){
                redisTemplate.opsForValue().set(userCartKey,new Cart());
            }
            mergeCards(guestCartKey,userCartKey);
            return (Cart) redisTemplate.opsForValue().get(userCartKey);

        }

        return (Cart) redisTemplate.opsForValue().get(guestCartKey);
    }

    public void add(String username,String uuid,Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(username,uuid,cart -> cart.add(product));

    }
    public void clearCart(String username, String uuid){
        execute(username,uuid,cart -> cart.clearCartItems());
    }

    public void deleteItem(String username,String uuid,Long id){
        execute(username,uuid,cart -> cart.deleteItem(id));
    }

    public void changeQuantity(String username, String uuid,Long id, int delta){
        execute(username,uuid,cart->cart.changeQuantity(id,delta));
    }

    private void execute(String username,String uuid,Consumer<Cart> operation){
        Cart cart = getCurrentCart(username,uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(getCartKeyFromUuid(uuid),cart);

    }

    private void mergeCards(String guestCartKey,String userCartKey){
            Cart guestCart = (Cart) redisTemplate.opsForValue().get(guestCartKey);
            Cart userCart =(Cart) redisTemplate.opsForValue().get(userCartKey);
            if(!guestCart.getItems().isEmpty()) {
                guestCart.getItems().forEach(cartItem -> mergeCartItemsWithList(userCart, cartItem));
                guestCart.clearCartItems();
                redisTemplate.opsForValue().set(userCartKey, userCart);
                redisTemplate.opsForValue().set(guestCartKey, guestCart);
            }
    }

    private void mergeCartItemsWithList(Cart cart, CartItem cartItem){
        if(cart.getItems().stream().anyMatch(ca ->ca.getProductId().equals(cartItem.getProductId()))){
            cart.changeQuantity(cartItem.getProductId(),cartItem.getQuantity());
        }else{
            cart.getItems().add(cartItem);
        }
    }


}
