package com.assignment.oms.dto;

import com.assignment.oms.constants.OrderStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    @JsonProperty("orderId")
    private Long orderId;

    @JsonProperty("user")
    private UserDetails user;

    @JsonProperty("orderItems")
    private List<OrderItemDetails> orderItems;

    @JsonProperty("shippingAddress")
    private ShippingAddressDetails shippingAddress;

    @JsonProperty("dueDate")
    private Timestamp dueDate;

    @JsonProperty("total")
    private Double total;

    @JsonProperty("status")
    private OrderStatus orderStatus;
}
