package com.example.order_service.restClient;

import com.example.order_service.dto.request.WarehouseReservationRequest;
import com.example.order_service.restClientConfig.RestClientConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class WarehouseServiceClient {
    private final RestClientConfig restClientConfig;

    @Value("${warehouse.service.url}")
    private String URL;

    public void reserve(WarehouseReservationRequest warehouseRequest) {
        restClientConfig.restClient()
                .post()
                .uri(URL)
                .body(warehouseRequest)
                .retrieve()
                .toBodilessEntity();
    }
}
