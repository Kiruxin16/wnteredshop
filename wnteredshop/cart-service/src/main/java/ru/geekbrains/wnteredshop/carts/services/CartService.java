package ru.geekbrains.wnteredshop.carts.services;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CartService {

    @Value("{cart-service.cart-prefix}")
    private String cartPrefix;

    private final ProductServiceIntegration productServiceIntegration;


    private HashMap<String,Cart> cartPool;

    @PostConstruct
    public void init(){
        cartPool =new HashMap<>();
    }

    public  Cart getCurrentCart(String uuid){

        String targetUuid=cartPrefix+uuid;
        if (!cartPool.containsKey(uuid)) {
            cartPool.put(uuid, new Cart());
        }
        return cartPool.get(uuid);


    }

    public void add(String uuid,Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(uuid).add(product);

    }
    public void clearCart(String uuid){
        getCurrentCart(uuid).clearCartItems();
    }

    public void deleteItem(String uuid,Long id){
        getCurrentCart(uuid).deleteItem(id);
    }

    public void changeQuantity(String uuid,Long id, int delta){
        getCurrentCart(uuid).changeQuantity(id,delta);
    }


}
