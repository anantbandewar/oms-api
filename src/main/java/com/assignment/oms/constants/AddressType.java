package com.assignment.oms.constants;

import java.util.stream.Stream;

public enum AddressType {
    HOME("Home"),
    OFFICE("Office");

    private String value;

    AddressType(String value) {
        this.value = value;
    }

    public static AddressType of(String addressType) {
        return Stream.of(AddressType.values())
                .filter(x -> x.value.equalsIgnoreCase(addressType))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
