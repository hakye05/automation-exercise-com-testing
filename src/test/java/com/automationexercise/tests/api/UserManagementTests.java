package com.automationexercise.tests.api;

import com.automationexercise.base.api.BaseTestApiUser;
import com.automationexercise.models.UserAuthData;
import com.automationexercise.models.UserData;
import com.automationexercise.utils.DataGenerator;
import io.restassured.response.Response;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.equalTo;

public class UserManagementTests extends BaseTestApiUser {

    private UserData user;

    @Test
    public void shouldCreateUser() {
        user = DataGenerator.createUser();

        Response response = userApiService.createUser(user);

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(201))
                .body("message", equalTo("User created!"));
    }

    @Test
    public void shouldDeleteUser() {
        user = DataGenerator.createUser();
        userApiService.createUser(user);

        Response response = userApiService.deleteUser(new UserAuthData(user.email(), user.password()));

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("message", equalTo("Account deleted!"));

        user = null;
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
