package com.assignment.oms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoreAttributes {
    String correlationId;
    Long userId;
    String role;
    String api;
    Long startTime;
}
