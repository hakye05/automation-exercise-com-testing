package com.automationexercise.api;

import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestClient {

    protected Response get(String endpoint) {
        return given()
                .spec(ApiClient.getRequestSpec())
                .when()
                .get(endpoint);
    }

    protected Response get(String endpoint, Map<String, ?> queryParams) {
        return given()
                .spec(ApiClient.getRequestSpec())
                .queryParams(queryParams)
                .when()
                .get(endpoint);
    }

    protected Response post(String endpoint) {
        return given()
                .spec(ApiClient.getRequestSpec())
                .when()
                .post(endpoint);
    }

    protected Response postForm(String endpoint, Map<String, ?> formData) {
        return given()
                .spec(ApiClient.getRequestSpec())
                .formParams(formData)
                .when()
                .post(endpoint);
    }

    protected Response delete(String endpoint, Map<String, ?> queryParams) {
        return given()
                .spec(ApiClient.getRequestSpec())
                .queryParams(queryParams)
                .when()
                .delete(endpoint);
    }
}
