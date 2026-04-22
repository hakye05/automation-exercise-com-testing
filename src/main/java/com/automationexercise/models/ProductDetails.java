package com.automationexercise.models;

public record ProductDetails(
        String name,
        String brand,
        String category,
        String price,
        String availability,
        String condition
) {
}
