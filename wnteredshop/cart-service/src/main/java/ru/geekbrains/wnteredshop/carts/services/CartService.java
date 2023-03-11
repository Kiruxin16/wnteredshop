package ru.geekbrains.wnteredshop.carts.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService {

    @Value("{cart-service.cart-prefix}")
    private String cartPrefix;

    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String,Object> redisTemplate;


    private HashMap<String,Cart> cartPool;

    @PostConstruct
    public void init(){
        cartPool =new HashMap<>();
    }
/*
    public  Cart getCurrentCart(String uuid){

        String targetUuid=cartPrefix+uuid;
        if (!cartPool.containsKey(uuid)) {
            cartPool.put(uuid, new Cart());
        }
        return cartPool.get(uuid);
    }*/
    private String getCartKeyFromUuid(String uuid){
        return cartPrefix+uuid;
    }

    public Cart getCurrentCart(String uuid){
        String cartKey= getCartKeyFromUuid(uuid);
        if (!redisTemplate.hasKey(cartKey)){
            redisTemplate.opsForValue().set(cartKey,new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(cartKey);
    }

    public void add(String uuid,Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid,cart -> cart.add(product));

    }
    public void clearCart(String uuid){
        execute(uuid,cart -> cart.clearCartItems());
    }

    public void deleteItem(String uuid,Long id){
        execute(uuid,cart -> cart.deleteItem(id));
    }

    public void changeQuantity(String uuid,Long id, int delta){
        execute(uuid,cart->cart.changeQuantity(id,delta));
    }

    private void execute(String uuid,Consumer<Cart> operation){
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(getCartKeyFromUuid(uuid),cart);

    }


}
