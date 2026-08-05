package com.example.order_service.dto.request;

import lombok.Builder;

@Builder
public record WarehouseServiceRequest (
        Long productId,
        Integer quantity
) {
}
