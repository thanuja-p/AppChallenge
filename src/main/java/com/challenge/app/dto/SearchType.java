package com.challenge.app.dto;

/**
 * SearchType - This enum holds search types
 */
public enum SearchType {

    USER("users"),
    TICKET("tickets"),
    ORHANIZATION("organisations"),
    DEFAULT("default");

    private String name;

    SearchType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
