package com.assignment.oms.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UpdateOrderRequest {
    @JsonProperty("status")
    private String status;
}
