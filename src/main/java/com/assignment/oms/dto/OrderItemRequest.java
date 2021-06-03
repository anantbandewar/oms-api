package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequest {

    @JsonProperty("productId")
    private Long productId;

    @JsonProperty("quantity")
    private Float quantity;
}
