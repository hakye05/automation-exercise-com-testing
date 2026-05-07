package com.automationexercise.api;

import io.restassured.config.LogConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class RestClient {

    private static final Logger log = LoggerFactory.getLogger(RestClient.class);

    protected Response get(String endpoint) {
        log.info("Sending GET request to: {}", endpoint);
        Response response = given()
                .spec(ApiClient.getRequestSpec())
                .when()
                .get(endpoint);
        log.debug("Get response received: Status {}", response.getStatusCode());
        return response;
    }

    protected Response get(String endpoint, Map<String, ?> queryParams) {
        log.info("Sending GET param request to: {} | Query params: {}", endpoint, queryParams);
        Response response = given()
                .spec(ApiClient.getRequestSpec())
                .queryParams(queryParams)
                .when()
                .get(endpoint);
        log.debug("GET param response received: Status {}", response.getStatusCode());
        return response;
    }

    protected Response post(String endpoint) {
        log.info("Sending POST request to: {}", endpoint);
        Response response = given()
                .spec(ApiClient.getRequestSpec())
                .when()
                .post(endpoint);
        log.debug("POST response received: Status {}", response.getStatusCode());
        return response;
    }

    protected Response postForm(String endpoint, Map<String, ?> formData) {
        log.info("Sending POST form request to: {}", endpoint);
        Response response = given()
                .spec(ApiClient.getRequestSpec())
                .formParams(formData)
                .when()
                .post(endpoint);
        log.debug("POST form response received: Status {}", response.getStatusCode());
        return response;
    }

    protected Response deleteForm(String endpoint, Map<String, ?> formData) {
        log.info("Sending DELETE request to {}", endpoint);
        Response response = given()
                .spec(ApiClient.getRequestSpec())
                .formParams(formData)
                .when()
                .delete(endpoint);
        log.debug("DELETE response received: Status {}", response.getStatusCode());
        return response;
    }
}
