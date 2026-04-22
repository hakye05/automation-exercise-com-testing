package com.automationexercise.models;

public record PaymentDetails(
        String cardHolderName,
        String cardNumber,
        String cvc,
        String expirationMonth,
        String expirationYear
) {
}
