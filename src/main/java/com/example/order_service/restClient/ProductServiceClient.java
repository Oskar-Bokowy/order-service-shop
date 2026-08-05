package com.example.order_service.restClient;

import com.example.order_service.dto.response.ProductServiceResponse;
import com.example.order_service.restClientConfig.RestClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestClientConfig restClientConfig;

    @Value("${product.service.url}")
    private String URL;

    public ProductServiceResponse getProductById(Long id) {
        return restClientConfig.restClient().get()
                .uri(URL + "/" + id)
                .retrieve()
                .body(ProductServiceResponse.class);
    }
}
