package com.automationexercise.utils;

import java.math.BigDecimal;

public class DataConverter {

    public static BigDecimal convertToDecimalNumber(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty or null");
        }
        return new BigDecimal(input.replaceAll("[^0-9.]", ""));
    }

    public static String sanitizePrice(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty or null");
        }
        return input.replaceAll("[^0-9.]", "");
    }
}
