package com.automationexercise.utils;

import java.math.BigDecimal;

/**
 * Utility class for converting and formatting string data types.
 * */
public class DataConverter {

    /**
     * Extracts numeric characters from the string and converts into a {@link BigDecimal} with decimal point.
     * @param input the raw string to convert (e.g. "Rs. 120.20" or "$120.20")
     * @return {@link BigDecimal} numeric representation of a number within the string
     * @throws IllegalArgumentException if input is null or empty
     * */
    public static BigDecimal convertToDecimalNumber(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty or null");
        }
        return new BigDecimal(input.replaceAll("[^0-9.]", ""));
    }

    /**
     * Sanitizes the price string by removing currency symbols and characters.
     * @param input the raw string representing price (e.g. "Rs. 120.20" or "$120.20")
     * @return a string containing only digits and decimal point
     * @throws IllegalArgumentException if input is null or empty
     * */
    public static String sanitizePrice(String input) {
        if (input == null || input.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be empty or null");
        }
        return input.replaceAll("[^0-9.]", "");
    }
}
