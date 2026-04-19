package com.automationexercise.utils;

import com.automationexercise.models.UserData;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class DataGenerator {

    private static String getRandomUUID() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    private static int getRandomInt(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max);
    }

    public static UserData createUser() {
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
}
