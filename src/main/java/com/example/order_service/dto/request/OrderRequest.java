package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record OrderRequest(
        @NotNull(message = "Client is required")
        Long clientId,
        @NotNull(message = "Products is required")
        Set<OrderItemRequest> orderItemsRequest
) {
}
