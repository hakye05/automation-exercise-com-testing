package com.automationexercise.models;

public record AddressDetails(
        String fullName,
        String company,
        String address1,
        String address2,
        String cityStateZip,
        String country,
        String mobileNumber
) {
}
