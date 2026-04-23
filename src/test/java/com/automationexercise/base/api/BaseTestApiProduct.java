package com.automationexercise.base.api;

import com.automationexercise.api.services.ProductApiService;
import org.testng.annotations.BeforeClass;

public class BaseTestApiProduct extends  BaseTestApi {

    protected ProductApiService productApiService;

    @BeforeClass
    public void setupProductService() {
        productApiService = new ProductApiService();
    }
}
