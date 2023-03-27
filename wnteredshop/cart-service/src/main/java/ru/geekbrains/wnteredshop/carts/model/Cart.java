package ru.geekbrains.wnteredshop.carts.model;

import lombok.Data;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.api.ResourceNotFoundException;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart(){
        this.items =new ArrayList<>();
    }



    public void add(ProductDto product){
        for (CartItem item: items) {
            if(item.getProductId().equals(product.getId())){
                item.setQuantity(item.getQuantity()+1);
                recalculate();
                return;
            }
        }


        items.add(CartItem.newBuilder()
                        .withProductId(product.getId())
                        .withProductTitle(product.getTitle())
                        .withPrice(product.getPrice())
                        .withPricePerProduct(product.getPrice())
                        .withQuantity(1)
                        .build());

        recalculate();
    }

    public void clearCartItems(){
        items.clear();
        totalPrice= BigDecimal.ZERO;
    }

    public void deleteItem(Long id){
        items.remove(getById(id));
        recalculate();
    }

    public void changeQuantity(Long id,int delta){
        CartItem item = getById(id);
        item.setQuantity(item.getQuantity()+delta);
        if(item.getQuantity()==0){
            items.remove(item);
        }
        recalculate();
    }

    private void recalculate(){
        totalPrice = BigDecimal.ZERO ;
        for(CartItem item: items){
            item.setPrice(BigDecimal.ZERO);
            for (int i = 0; i <item.getQuantity() ; i++) {
                item.setPrice(item.getPrice().add(item.getPricePerProduct()));
            }
            totalPrice=totalPrice.add(item.getPrice());
        }
    }

    private CartItem getById(Long id){
       return items.stream().filter(cartItem -> cartItem.getProductId().equals(id))
               .findFirst().orElseThrow(() -> new ResourceNotFoundException(""));
    }





}
