package com.automationexercise.api;

import com.automationexercise.config.ConfigReader;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;

public class ApiClient {

    private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();
    private static final String API_URL = ConfigReader.getProperty("api.url");

    public static RequestSpecification getRequestSpec() {
        if (requestSpec.get() == null) {
            RequestSpecification spec = new RequestSpecBuilder()
                    .setBaseUri(API_URL)
                    .addFilter(new RequestLoggingFilter())
                    .addFilter(new ResponseLoggingFilter())
                    .build();
            requestSpec.set(spec);
        }
        return requestSpec.get();
    }

    public static void cleanup() {
        requestSpec.remove();
    }
}
