package ru.geekbrains.wnteredshop.carts;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.wnteredshop.carts.services.CartService;

import java.math.BigDecimal;

@SpringBootTest
public class CartServiceTest {
    @Autowired
    CartService cartServise;

    @MockBean
    private ProductServiceIntegration productServiceIntegration;

    @Test
    public void changeQuantityTest(){

        ProductDto productDto =new ProductDto(1l,"Coffee",BigDecimal.valueOf(400),"Other");
        Mockito.doReturn(productDto).when(productServiceIntegration).getProductById(1l);
        for (int i = 0; i < 4; i++) {
            cartServise.add(null,null,1l);
        }

        cartServise.changeQuantity(null,null,1l,-4);
        Assertions.assertEquals(cartServise.getCurrentCart(null,null).getItems().size(),0);

    }

}
