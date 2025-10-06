package com.orangehrm.utils;

public class DataParser {


    public static boolean parseBooleanStrict(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Expected value cannot be null");
        }
        switch (value.trim().toLowerCase()) {
            case "true":
                return true;
            case "false":
                return false;
            default:
                throw new IllegalArgumentException(
                        "Invalid boolean value in Excel: '" + value + "'. Allowed: true/false"
                );
        }
    }
}
