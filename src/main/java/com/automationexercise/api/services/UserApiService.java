package com.automationexercise.api.services;

import com.automationexercise.api.RestClient;
import com.automationexercise.models.UserAuthData;
import com.automationexercise.models.UserData;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

import static com.automationexercise.utils.DataMapper.convertToMap;

/**
 * Service class for User related API operations.
 * */
public class UserApiService extends RestClient {

    private static final String USER_CREATE = "/createAccount";
    private static final String USER_DELETE = "/deleteAccount";
    private static final String USER_POST_VERIFY_LOGIN = "/verifyLogin";
    private static final String USER_GET_DETAILS = "/getUserDetailByEmail";

    /**
     * Sends POST request to register a new user account.
     * @param userData a populated {@link UserData} instance
     * @return the API {@link Response} containing status and response message
     * */
    @Step("API: Create new user with email: {userData.email}")
    public Response createUser(UserData userData) {
        return postForm(USER_CREATE, convertToMap(userData));
    }

    /**
     * Sends DELETE request to delete user account.
     * @param userAuthData a populated {@link UserAuthData} instance
     * @return the API {@link Response} containing status and response message
     * */
    @Step("API: Delete user with email: {userAuthData.email}")
    public Response deleteUser(UserAuthData userAuthData) {
        return deleteForm(USER_DELETE, convertToMap(userAuthData));
    }

    /**
     * Sends POST request to check login of user account.
     * @param userAuthData a populated {@link UserAuthData} instance
     * @return the API {@link Response} containing status and response message
     * */
    @Step("API: Verify login for user with email: {userAuthData.email}")
    public Response verifyLogin(UserAuthData userAuthData) {
        return postForm(USER_POST_VERIFY_LOGIN, convertToMap(userAuthData));
    }

    /**
     * Sends GET request to fetch user account's details.
     * @param email a string of user's email
     * @return the API {@link Response} containing status and user details
     * */
    @Step("API: Get user details with email: {0}")
    public Response getUserDetails(String email) {
        return get(USER_GET_DETAILS, Map.of("email", email));
    }
}
