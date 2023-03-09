package ru.geekbrains.wnteredshop.carts.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;


import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CartServise {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    private HashMap<String,Cart> cartPool;

    @PostConstruct
    public void init(){
        tempCart= new Cart();
        cartPool =new HashMap<>();
    }

    public  Cart getCurrentCart(String username){

        if(!(username.equals(""))){
            if(!cartPool.containsKey(username)){
                cartPool.put(username,new Cart());
            }
            return cartPool.get(username);

        }

        return tempCart;
    }

    public void add(String username,Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        getCurrentCart(username).add(product);

    }
    public void clearCart(String username){
        getCurrentCart(username).clearCartItems();
    }

    public void deleteItem(String username,Long id){
        getCurrentCart(username).deleteItem(id);
    }

    public void changeQuantity(String username,Long id, int delta){
        getCurrentCart(username).changeQuantity(id,delta);
    }


}
