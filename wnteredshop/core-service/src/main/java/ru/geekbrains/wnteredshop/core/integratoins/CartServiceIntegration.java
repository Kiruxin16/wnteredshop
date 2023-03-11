package ru.geekbrains.wnteredshop.core.integratoins;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;




    public CartDto getCurrentCart(String username){
        return cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username",username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username){
        cartServiceWebClient.delete()
                .uri("/api/v1/cart/0")
                .header("username",username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
