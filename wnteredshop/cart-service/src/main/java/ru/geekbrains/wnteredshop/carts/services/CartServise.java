package ru.geekbrains.wnteredshop.carts.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.api.ResourceNotFoundException;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;


import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartServise {
    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init(){
        tempCart= new Cart();
    }

    public  Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);

    }
    public void clearCart(){
        tempCart.clearCartItems();
    }

    public void deleteItem(Long id){
        tempCart.deleteItem(id);
    }

    public void changeQuantity(Long id, int delta){
        tempCart.changeQuantity(id,delta);
    }


}
