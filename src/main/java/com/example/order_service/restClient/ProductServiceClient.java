package com.example.order_service.restClient;

import com.example.order_service.dto.response.ProductServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class ProductServiceClient {
    private final RestClient restClient;

    @Value("${product.service.url}")
    private String URL;

    public ProductServiceResponse getProductById(Long id) {
        return restClient.get()
                .uri(URL + "/" + id)
                .retrieve()
                .body(ProductServiceResponse.class);
    }
}
