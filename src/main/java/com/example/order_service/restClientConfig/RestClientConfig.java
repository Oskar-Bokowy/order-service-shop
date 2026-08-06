package com.example.order_service.restClientConfig;

import com.example.order_service.exception.exception.RemoteServiceException;
import org.springframework.http.HttpStatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

//    @Bean
//    public RestClient restClient() {
//        return RestClient.create();
//    }

    @Bean
    RestClient.Builder restClientBuilder() {
        return RestClient.builder();
    }

    @Bean
    RestClient restClient(RestClient.Builder builder) {
        return builder
                .defaultStatusHandler(
                        HttpStatusCode::isError,
                        (request, response) -> {

                            String body = new String(response.getBody().readAllBytes());

                            throw new RemoteServiceException(body);
                        }
                )
                .build();
    }
}
