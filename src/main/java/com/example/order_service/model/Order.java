package com.example.order_service.model;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Entity
@Table(name = "customer_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long clientId;
    private LocalDateTime createdAt;
    private BigDecimal totalPrice;
    private BigDecimal discount;
    private BigDecimal shippingCost;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> items = new HashSet<>();

    public void countryShippingCost(){
        this.shippingCost = BigDecimal.valueOf(15.99);
    }

    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
    public void calculateTotalPrice() {
        BigDecimal itemsTotal = items.stream()
                .map(item -> item.getPriceAtPurchase()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal discountValue = resolveDiscount();
        if (discountValue.compareTo(itemsTotal) > 0) {
            discountValue = itemsTotal;
        }
        BigDecimal shippingValue = shippingCost != null ? shippingCost : BigDecimal.ZERO;
        this.totalPrice = itemsTotal.subtract(discountValue).add(shippingValue);
        if (totalPrice.compareTo(BigDecimal.valueOf(500)) > 0) {
            this.totalPrice = totalPrice.subtract(totalPrice.multiply(BigDecimal.valueOf(0.05)));
        }
    }

    private BigDecimal resolveDiscount() {
        return discount != null ? discount : BigDecimal.ZERO;
    }
}

