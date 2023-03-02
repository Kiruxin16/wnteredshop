package ru.geekbrains.wnteredshop.core;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.geekbrains.wnteredshop.core.entities.Category;
import ru.geekbrains.wnteredshop.core.entities.Product;
import ru.geekbrains.wnteredshop.core.repositories.ProductRepository;
import ru.geekbrains.wnteredshop.core.services.ProductService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import java.math.BigDecimal;
import java.util.Optional;


@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @Test
    public void getProductByIdTest() throws Exception{
        Product product = new Product();
        product.setId(21l);
        product.setTitle("Marshmello");
        product.setPrice(BigDecimal.valueOf(94));
        Category category=new Category();
        category.setId(4l);
        category.setTitle("sweets");
        product.setCategory(category);

        Optional<Product>  optionalProduct =Optional.of(product);

        given(productService.findProductByID(21l)).willReturn(optionalProduct);

        mvc.perform(
                get("/api/v1/products/21")
                        .contentType(MediaType.APPLICATION_JSON)

        )
                .andDo(print())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.categoryTitle", is(optionalProduct.get().getCategory().getTitle())));

    }
}
