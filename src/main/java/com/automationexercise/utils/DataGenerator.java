package com.automationexercise.utils;

import com.automationexercise.models.AddressDetails;
import com.automationexercise.models.PaymentDetails;
import com.automationexercise.models.UserData;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating randomized test data.
 * */
public class DataGenerator {

    private static String getRandomUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    /**
     * Generates a new {@link UserData} object with unique email and phone number.
     * Each execution uses a unique suffix to prevent identical data across tests.
     * @return a populated {@link UserData} instance
     * */
    public static UserData generateUser() {
        String suffix = getRandomUUID();
        return new UserData(
                "John", "john" + suffix + "@example.com",
                "pass123", "Mr", "10", "February", "2000",
                "John", "Whiskers", "John Software inc",
                "Bay Street 12", "Building 22",
                "Canada", "Toronto State", "Toronto", "929919",
                "555" + getRandomInt(100000, 999999)
        );
    }

    /**
     * Generates a new {@link PaymentDetails} object based on the provided user.
     * @param firstName a string of user's first name
     * @param lastName a string of user's last name
     * @return a populated {@link PaymentDetails} instance
     * */
    public static PaymentDetails generatePaymentData(String firstName, String lastName) {
        return new PaymentDetails(
                lastName + firstName,
                "1234123412341234",
                "1234",
                "03",
                "2028"
        );
    }

    /**
     * Generates a new {@link AddressDetails} object based on the provided user.
     * @param user an existing {@link UserData} instance
     * @return a populated {@link AddressDetails} instance
     * */
    public static AddressDetails generateAddressData(UserData user) {
        return new AddressDetails(
                user.genderTitle() + ". " + user.firstName() + " " + user.lastName(),
                user.company(),
                user.address1(),
                user.address2(),
                user.city() + " " + user.state() + " " + user.zipcode(),
                user.country(),
                user.mobileNumber()
        );
    }
}
