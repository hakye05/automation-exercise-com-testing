package com.automationexercise.tests.api;

import com.automationexercise.base.api.BaseTestApiUser;
import com.automationexercise.models.UserAuthData;
import com.automationexercise.models.UserData;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

@Epic("API Testing")
@Feature("User Management")
public class UserManagementTests extends BaseTestApiUser {

    private UserData user;

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("User creation with valid data")
    @Description("Verify that a user can create an account with registration data.")
    public void shouldCreateUser() {
        user = DataGenerator.generateUser();

        Response response = userApiService.createUser(user);

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(201))
                .body("message", equalTo("User created!"));
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("User deletion")
    @Description("Verify that a user can delete account with email.")
    public void shouldDeleteUser() {
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        Response response = userApiService.deleteUser(new UserAuthData(user.email(), user.password()));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("Account deleted!"));

        user = null;
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("User login verification with valid data")
    @Description("Verify that user is able to login with correct authentication credentials.")
    public void shouldVerifyLoginUserWithCorrectCredentials() {
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        Response response = userApiService.verifyLogin(new UserAuthData(user.email(), user.password()));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("User exists!"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("User login verification fail without email")
    @Description("Verify that a user can not login without providing email.")
    public void shouldFailToLoginUserWithoutEmail() {
        user = DataGenerator.generateUser();

        Response response = userApiService.verifyLogin(new UserAuthData("", user.password()));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(400))
                .body("message", equalTo("Bad request, email or password parameter is missing in POST request."));

        user = null;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("User login verification fail with invalid password")
    @Description("Verify that a user can not login with incorrect password.")
    public void shouldFailToLoginUserWithInvalidPassword() {
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        Response response = userApiService.verifyLogin(new UserAuthData(user.email(), "invalidPassword123"));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(404))
                .body("message", equalTo("User not found!"));
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("User details fetching using email")
    @Description("Verify the method that gets the user's data using email.")
    public void shouldGetUserDetails() {
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        Response response = userApiService.getUserDetails(user.email());

        response.then().statusCode(200)
                .body("responseCode", equalTo(200))
                .body("user.name", equalTo(user.name()));
    }

    @AfterMethod(alwaysRun = true)
    public void cleanupUser() {
        try {
            if (user != null) {
                userApiService.deleteUser(new UserAuthData(user.email(), user.password()));;
            }
        } catch(Exception e) {
            System.err.println("CLEANUP FAILURE: failed to delete user: " + user.email() + " Cause: " + e.getMessage());
        } finally {
            user = null;
        }
    }
}
