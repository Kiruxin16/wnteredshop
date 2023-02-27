package ru.geekbrains.wnteredshop.core.configs;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;
import ru.geekbrains.wnteredshop.core.properties.CartServiceIntergrationProperties;

import java.util.concurrent.TimeUnit;

@Configuration
@RequiredArgsConstructor
@EnableConfigurationProperties(CartServiceIntergrationProperties.class)
public class AppConfig {

    private final CartServiceIntergrationProperties cartServiceIntergrationProperties;


    @Bean
    public WebClient cartServiceWebClient(){
        TcpClient tcpClient =TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS,cartServiceIntergrationProperties.getConnectionTimeout())
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(cartServiceIntergrationProperties.getReadTimeout(), TimeUnit.MICROSECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(cartServiceIntergrationProperties.getWriteTimeout(), TimeUnit.MICROSECONDS));
                });
        return WebClient
                .builder()
                .baseUrl(cartServiceIntergrationProperties.getUrl())
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }
}
