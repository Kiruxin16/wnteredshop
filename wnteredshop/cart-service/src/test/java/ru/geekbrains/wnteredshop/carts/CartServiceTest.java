package ru.geekbrains.wnteredshop.carts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.model.Cart;
import ru.geekbrains.wnteredshop.carts.model.CartItem;
import ru.geekbrains.wnteredshop.carts.services.CartServise;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    CartServise cartServise;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void changeQuantityTest(){

        ProductDto productDto =new ProductDto(1l,"Coffee",BigDecimal.valueOf(400),"Other");
        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(1l);
        for (int i = 0; i < 4; i++) {
            cartServise.add(1l);
        }

        cartServise.changeQuantity(1l,-4);
        Assertions.assertEquals(cartServise.getCurrentCart().getItems().size(),0);

    }

}
