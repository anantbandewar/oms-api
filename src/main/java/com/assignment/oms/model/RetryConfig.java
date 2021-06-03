package com.assignment.oms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
@Builder
public class RetryConfig {
    private Long maxAttempts;
    private Set<Class<?>> retryOn;
    private DelayConfig delayConfig;
    private boolean retryIfNull;
    private boolean increaseDelay;
    private Integer delayExponent;
}
