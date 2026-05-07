package com.automationexercise.api;

import com.automationexercise.utils.ConfigReader;
import com.automationexercise.utils.allure.MaskingAllureFilter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Global API configuration class.
 * <p>Provides thread-safe {@link RequestSpecification} using {@link ThreadLocal} to support parallel execution.</p>
 * <p>All API requests need to include specification returned by {@link #getRequestSpec()}. The initialized specs
 * must be removed by using {@link #cleanup()} during tear-down to avoid memory leak</p>
 * */
public class ApiClient {

    private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();
    private static final String API_URL = ConfigReader.getProperty("api.url");
    private static final Logger log = LoggerFactory.getLogger(ApiClient.class);

    /**
     * Provides a new thread specific {@link RequestSpecification} if it has not been initialized for current thread,
     * otherwise returns the existing spec.
     * @return the thread specific {@link RequestSpecification}
     * */
    public static RequestSpecification getRequestSpec() {
        if (requestSpec.get() == null) {
            log.debug("Initializing new RequestSpecification for Thread: {}", Thread.currentThread().getName());
            RequestSpecification spec = new RequestSpecBuilder()
                    .setBaseUri(API_URL)
                    .addFilter(new MaskingAllureFilter())
                    .build();
            requestSpec.set(spec);
        }
        return requestSpec.get();
    }

    /**
     * Removes the thread specific {@link RequestSpecification}. Failure to call this method will
     * result memory leaks.
     * */
    public static void cleanup() {
        log.debug("Cleaning up RequestSpecification for Thread: {}", Thread.currentThread().getName());
        requestSpec.remove();
    }
}
