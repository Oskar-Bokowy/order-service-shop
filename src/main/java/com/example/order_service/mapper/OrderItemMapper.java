package com.example.order_service.mapper;

import com.example.order_service.dto.request.OrderItemRequest;
import com.example.order_service.dto.response.OrderItemResponse;
import com.example.order_service.dto.response.ProductServiceResponse;
import com.example.order_service.model.OrderItem;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class OrderItemMapper {

    public OrderItemResponse toResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .productId(item.getProductId())
                .productName(item.getProductName())
                .priceAtPurchase(item.getPriceAtPurchase())
                .quantity(item.getQuantity())
                .build();
    }

    public Set<OrderItemResponse> toResponseSet(Set<OrderItem> items){
        return items.stream()
                .map(this::toResponse)
                .collect(Collectors.toSet());
    }

    public OrderItem toEntity(OrderItemRequest orderItemRequest){
        return OrderItem.builder()
                .productId(orderItemRequest.productId())
                .quantity(orderItemRequest.quantity())
                .build();
    }

    public OrderItem toEntityFromServiceProduct(ProductServiceResponse response){
        return OrderItem.builder()
                .priceAtPurchase(response.price())
                .productId(response.productId())
                .productName(response.productName())
                .build();
    }



}
