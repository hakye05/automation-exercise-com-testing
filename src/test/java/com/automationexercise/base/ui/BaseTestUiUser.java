package com.automationexercise.base.ui;

import com.automationexercise.api.ApiClient;
import com.automationexercise.api.services.UserApiService;
import com.automationexercise.models.UserAuthData;
import com.automationexercise.models.UserData;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseTestUiUser extends BaseTestUi {

    protected UserData user;
    protected UserApiService userApiService;

    @BeforeSuite
    public void setupParser() {
        RestAssured.registerParser("text/html", Parser.JSON);
    }

    @BeforeClass
    public void setupUserService() {
        userApiService = new UserApiService();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDownUser() {
        try {
            if (user != null) {
                userApiService.deleteUser(new UserAuthData(user.email(), user.password()));
            }
        } catch(Exception e) {
            System.err.println("CLEANUP FAILURE: failed to delete user: " + user.email() + " Cause: " + e.getMessage());
        } finally {
            ApiClient.cleanup();
            user = null;
        }
    }
}
