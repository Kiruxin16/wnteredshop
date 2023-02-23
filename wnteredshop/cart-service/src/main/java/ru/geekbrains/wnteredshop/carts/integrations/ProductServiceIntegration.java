package ru.geekbrains.wnteredshop.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.wnteredshop.api.ProductDto;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    @Value("${integration.getDto}")
    private String url;


    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id){
        return Optional.ofNullable(restTemplate.getForObject(url+id,ProductDto.class));
    }

}
