package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDetails {

    @JsonProperty("orderItemId")
    private Long orderItemId;

    @JsonProperty("product")
    private ProductDetails product;

    @JsonProperty("quantity")
    private Float quantity;
}
