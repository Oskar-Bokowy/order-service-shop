package com.example.order_service.restClient;

import com.example.order_service.dto.response.ClientServiceResponse;
import com.example.order_service.restClientConfig.RestClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientServiceClient {
    private final RestClientConfig restClientConfig;

    @Value("${client.service.url}")
    private String URL;

    public ClientServiceResponse getClientById(Long id) {
        return restClientConfig.restClient().get()
                .uri(URL + "/"+ id)
                .retrieve()
                .body(ClientServiceResponse.class);
    }
}
