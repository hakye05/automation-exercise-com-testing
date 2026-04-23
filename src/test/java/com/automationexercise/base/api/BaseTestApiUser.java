package com.automationexercise.base.api;

import com.automationexercise.api.services.UserApiService;
import org.testng.annotations.BeforeClass;

public class BaseTestApiUser extends BaseTestApi {

    protected UserApiService userApiService;

    @BeforeClass
    public void setupUserServices() {
        userApiService = new UserApiService();
    }
}
