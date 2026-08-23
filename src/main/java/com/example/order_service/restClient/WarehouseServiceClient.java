package com.example.order_service.restClient;

import com.example.order_service.dto.request.WarehouseReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@RequiredArgsConstructor
@Service
public class WarehouseServiceClient {
    private final RestClient restClient;

    @Value("${warehouse.service.url}")
    private String URL;

    public void reserve(WarehouseReservationRequest warehouseRequest) {
        restClient
                .post()
                .uri(URL)
                .body(warehouseRequest)
                .retrieve()
                .toBodilessEntity();
    }
}
