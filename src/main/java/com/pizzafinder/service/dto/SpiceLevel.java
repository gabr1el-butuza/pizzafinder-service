package com.pizzafinder.service.dto;

public enum SpiceLevel {

    NONE("NONE"),

    HOT("HOT"),

    CRAZY("CRAZY");

    private String value;

    SpiceLevel(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
