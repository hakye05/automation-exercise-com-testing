package com.automationexercise.api.services;

import com.automationexercise.api.RestClient;
import io.restassured.response.Response;

import java.util.Map;

public class ProductApiService extends RestClient {

    private static final String PRODUCT_GET_LIST = "/productsList";
    private static final String PRODUCT_GET_BRANDS_LIST = "/brandsList";
    private static final String PRODUCT_POST_SEARCH = "/searchProduct";

    public Response getAllProductsList() {
        return get(PRODUCT_GET_LIST);
    }

    public Response getAllProductsBrandList() {
        return get(PRODUCT_GET_BRANDS_LIST);
    }

    public Response searchProduct(String searchWord) {
        return postForm(PRODUCT_POST_SEARCH, Map.of("search_product", searchWord));
    }

    public Response searchProductWithoutParam() {
        return post(PRODUCT_POST_SEARCH);
    }
}
