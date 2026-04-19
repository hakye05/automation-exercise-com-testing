package com.automationexercise.api.services;

import com.automationexercise.api.RestClient;
import com.automationexercise.models.UserData;
import io.restassured.response.Response;

import java.util.Map;

import static com.automationexercise.utils.DataMapper.convertToMap;

public class UserApiService extends RestClient {

    private static final String USER_CREATE = "/createAccount";
    private static final String USER_DELETE = "/deleteAccount";

    public Response createUser(UserData userData) {
        return postForm(USER_CREATE, convertToMap(userData));
    }

    public Response deleteUser(String email, String password) {
        return delete(USER_DELETE, Map.of("email", email, "password", password));
    }
}
