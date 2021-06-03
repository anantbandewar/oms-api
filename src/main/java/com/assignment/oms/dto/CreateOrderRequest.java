package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @JsonProperty("orderItems")
    private List<OrderItemRequest> orderItems;

    @JsonProperty("addressId")
    private Long addressId;
}
