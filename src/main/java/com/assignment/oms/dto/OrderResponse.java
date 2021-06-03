package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private Long orderId;
    private String orderStatus;
}
