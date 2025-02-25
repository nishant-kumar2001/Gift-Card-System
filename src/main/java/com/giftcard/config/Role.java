package com.giftcard.config;

public enum Role {
    ADMIN, CUSTOMER;

    public static Role fromString(String role) {
        return Role.valueOf(role.toUpperCase());
    }
}

