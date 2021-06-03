package com.assignment.oms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "OrderItem")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OrderItemId", unique = true, nullable = false, updatable = false)
    private Long orderItemId;

    @Column(name = "OrderId", updatable = false, nullable = false)
    private Long orderId;

    @Column(name = "ProductId", updatable = false, nullable = false)
    private Long productId;

    @Column(name = "Quantity", nullable = false)
    private Float quantity;
}
