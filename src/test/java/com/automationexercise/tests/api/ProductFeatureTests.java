package com.automationexercise.tests.api;

import com.automationexercise.base.api.BaseTestApiProduct;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;

public class ProductFeatureTests extends BaseTestApiProduct {

    @Test
    public void shouldGetAllProducts() {
        Response response = productApiService.getAllProductsList();

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("products", is(not(empty())))
                .body("products.name", everyItem(not(emptyOrNullString())))
                .body("products.price", everyItem(not(emptyOrNullString())))
                .body("products.brand", everyItem(not(emptyOrNullString())));
    }

    @Test
    public void shouldGetAllProductBrands() {
        Response response = productApiService.getAllProductsBrandList();

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(200))
                .body("brands", is(not(empty())))
                .body("brands.id", everyItem(not(emptyOrNullString())))
                .body("brands.brand", everyItem(not(emptyOrNullString())));
    }

    @Test
    public void shouldGetSearchedProduct() {
        String searchString = "top";

        Response response = productApiService.searchProduct(searchString);

        response.then()
                .statusCode(200)
                .body("products", is(not(empty())))
                .body("products.every { it.name.toLowerCase().contains('" + searchString.toLowerCase() + "') || " +
                        "it.category.category.toLowerCase().contains('" + searchString.toLowerCase() + "') }", is(true));
    }

    @Test
    public void shouldFailSearchOfProductsWithoutKeyword() {
        Response response = productApiService.searchProductWithoutParam();

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(400))
                .body("message", equalTo("Bad request, search_product parameter is missing in POST request."));
    }
}
