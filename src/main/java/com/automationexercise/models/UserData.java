package com.automationexercise.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record UserData(
        String name,
        String email,
        String password,
        @JsonProperty("title") String genderTitle,
        @JsonProperty("birth_date") String birthDate,
        @JsonProperty("birth_month") String birthMonth,
        @JsonProperty("birth_year") String birthYear,
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        String company,
        String address1,
        String address2,
        String country,
        String state,
        String city,
        String zipcode,
        @JsonProperty("mobile_number")String mobileNumber
) {
    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='[PROTECTED]'" +
                ", genderTitle='" + genderTitle + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", birthMonth='" + birthMonth + '\'' +
                ", birthYear='" + birthYear + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
