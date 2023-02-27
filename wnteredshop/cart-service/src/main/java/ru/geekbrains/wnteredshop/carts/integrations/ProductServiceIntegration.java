package ru.geekbrains.wnteredshop.carts.integrations;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import ru.geekbrains.wnteredshop.api.ProductDto;
import ru.geekbrains.wnteredshop.carts.properties.ProductServiceIntergrationProperties;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceIntegration {

    private final ProductServiceIntergrationProperties properties;

    private final WebClient productServiceWebClient;



    public ProductDto getProductById(Long id){
        return productServiceWebClient.get()
                .uri(properties.getUrl()+"/"+id)
                .retrieve()
                .onStatus(
                        httpStatus -> httpStatus.value() == HttpStatus.NOT_FOUND.value(),
                        clientResponse -> Mono.error(new RuntimeException("Товар не найден в МС продукты"))
                        )
                .bodyToMono(ProductDto.class)
                .block();

    }

}
