package com.assignment.oms.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.concurrent.TimeUnit;

@Data
@AllArgsConstructor
@Builder
public class DelayConfig {
    private long delay;
    private TimeUnit timeUnit;
}
