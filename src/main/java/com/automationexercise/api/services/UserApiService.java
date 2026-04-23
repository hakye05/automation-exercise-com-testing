package com.automationexercise.api.services;

import com.automationexercise.api.RestClient;
import com.automationexercise.models.UserAuthData;
import com.automationexercise.models.UserData;
import io.restassured.response.Response;

import java.util.Map;

import static com.automationexercise.utils.DataMapper.convertToMap;

public class UserApiService extends RestClient {

    private static final String USER_CREATE = "/createAccount";
    private static final String USER_DELETE = "/deleteAccount";
    private static final String USER_POST_VERIFY_LOGIN = "/verifyLogin";
    private static final String USER_GET_DETAILS = "/getUserDetailByEmail";

    public Response createUser(UserData userData) {
        return postForm(USER_CREATE, convertToMap(userData));
    }

    public Response deleteUser(UserAuthData userAuthData) {
        return deleteForm(USER_DELETE, convertToMap(userAuthData));
    }

    public Response verifyLogin(UserAuthData userAuthData) {
        return postForm(USER_POST_VERIFY_LOGIN, convertToMap(userAuthData));
    }

    public Response getUserDetails(String email) {
        return get(USER_GET_DETAILS, Map.of("email", email));
    }
}
