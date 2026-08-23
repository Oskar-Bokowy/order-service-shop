package com.example.order_service.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.util.Set;

@Builder
public record WarehouseReservationRequest (
        @NotNull
        Set<WarehouseServiceRequest> warehouseServiceRequests
){
}
