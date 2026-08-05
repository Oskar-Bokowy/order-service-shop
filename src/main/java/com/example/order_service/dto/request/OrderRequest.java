package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record OrderRequest(
        @NotNull
        Long clientId,
        @NotNull
        Set<OrderItemRequest> orderItemsRequest
) {
}
