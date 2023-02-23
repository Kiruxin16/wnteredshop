package ru.geekbrains.wnteredshop.core.integratoins;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.wnteredshop.api.CartDto;
import ru.geekbrains.wnteredshop.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {

    private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart(){
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/winter-carts/api/v1/cart",CartDto.class));
    }

    public void clear(){
        restTemplate.delete("http://localhost:8190/winter-carts/api/v1/cart");
    }
}
