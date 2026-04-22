package com.automationexercise.models;

public record CartItemDetails(
        String name,
        String price,
        String quantity,
        String totalPrice
) {
}
