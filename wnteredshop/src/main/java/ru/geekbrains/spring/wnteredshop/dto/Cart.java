package ru.geekbrains.spring.wnteredshop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.spring.wnteredshop.entities.Product;
import ru.geekbrains.spring.wnteredshop.exceptions.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data

public class Cart {
    private List<CartItem> items;
    private int totalPrice;

    public Cart(){
        this.items =new ArrayList<>();
    }

    public List<CartItem> getItems(){
        return Collections.unmodifiableList(items);
    }

    public void add(Product product){
        for (CartItem item: items) {
            if(item.getProductId().equals(product.getId())){
                item.setQuantity(item.getQuantity()+1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(),1,product.getPrice(),product.getPrice()));
        recalculate();
    }

    public void clearCartItems(){
        items.clear();
        recalculate();
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
        totalPrice =0;
        for(CartItem item: items){
            item.setPrice(item.getPricePerProduct()*item.getQuantity());
            totalPrice+=item.getPricePerProduct()* item.getQuantity();
        }
    }

    private CartItem getById(Long id){
       return items.stream().filter(cartItem -> cartItem.getProductId().equals(id))
               .findFirst().orElseThrow(() -> new ResourceNotFoundException(""));
    }





}
