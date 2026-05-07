package com.automationexercise.models;

public record UserAuthData(
        String email,
        String password
) {
    @Override
    public String toString() {
        return "UserAuthData{" +
                "email='" + email + '\'' +
                ", password='[PROTECTED]'}";
    }
}
