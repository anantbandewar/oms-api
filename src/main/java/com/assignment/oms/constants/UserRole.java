package com.assignment.oms.constants;

import java.util.stream.Stream;

public enum UserRole {
    ADMINISTRATOR("Administrator"),
    BUYER("Buyer");

    private String value;

    UserRole(String value) {
        this.value = value;
    }

    public static UserRole of(String userRoleType) {
        return Stream.of(UserRole.values())
                .filter(x -> x.value.equalsIgnoreCase(userRoleType))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
