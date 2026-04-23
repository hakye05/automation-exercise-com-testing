package com.automationexercise.base.api;

import com.automationexercise.api.ApiClient;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestApi {

    @BeforeMethod
    public void setup() {
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        ApiClient.cleanup();
    }
}
