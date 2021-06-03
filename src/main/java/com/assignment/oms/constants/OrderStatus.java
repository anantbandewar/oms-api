package com.assignment.oms.constants;

import java.util.stream.Stream;

public enum OrderStatus {
    PLACED("Placed"),
    APPROVED("Approved"),
    CANCELLED("Cancelled"),
    IN_DELIVERY("In Delivery"),
    COMPLETED("Completed");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public static OrderStatus of(String orderStatus) {
        return Stream.of(OrderStatus.values())
                .filter(x -> x.value.equalsIgnoreCase(orderStatus))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
