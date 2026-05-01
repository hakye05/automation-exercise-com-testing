package com.automationexercise.api.services;

import com.automationexercise.api.RestClient;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import java.util.Map;

/**
 * Service class for Products related API operations.
 * */
public class ProductApiService extends RestClient {

    private static final String PRODUCT_GET_LIST = "/productsList";
    private static final String PRODUCT_GET_BRANDS_LIST = "/brandsList";
    private static final String PRODUCT_POST_SEARCH = "/searchProduct";

    /**
     * Sends GET request to fetch all products.
     * @return the API {@link Response} containing status and the list of products with details
     * */
    @Step("API: Get a list of all products")
    public Response getAllProductsList() {
        return get(PRODUCT_GET_LIST);
    }

    /**
     * Sends GET request to fetch all brands.
     * @return the API {@link Response} containing status and the list of brands
     * */
    @Step("API: Get a list of all brands")
    public Response getAllProductsBrandList() {
        return get(PRODUCT_GET_BRANDS_LIST);
    }

    /**
     * Sends POST request to search products based on keyword.
     * @param searchWord a string that needs to be searched
     * @return the API {@link Response} containing status and the list of products with details
     * */
    @Step("API: Search for product that contains: {0}")
    public Response searchProduct(String searchWord) {
        return postForm(PRODUCT_POST_SEARCH, Map.of("search_product", searchWord));
    }

    /**
     * Sends POST request to search products without providing any parameter.
     * @return the API {@link Response} containing status and the response message
     * */
    @Step("API: Search for product without providing word")
    public Response searchProductWithoutParam() {
        return post(PRODUCT_POST_SEARCH);
    }
}
