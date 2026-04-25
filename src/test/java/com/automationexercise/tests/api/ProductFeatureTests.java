package com.automationexercise.tests.api;

import com.automationexercise.base.api.BaseTestApiProduct;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.everyItem;
import static org.hamcrest.Matchers.not;

@Epic("API Testing")
@Feature("Product Features")
public class ProductFeatureTests extends BaseTestApiProduct {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Products list fetching")
    @Description("Verify that we get products list and each item is valid")
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
    @Severity(SeverityLevel.CRITICAL)
    @Story("Fetching list of product brands")
    @Description("Verify that we get brands of products")
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
    @Severity(SeverityLevel.CRITICAL)
    @Story("Product search with search word")
    @Description("Verify that search feature returns the list of products that relate to search word")
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
    @Severity(SeverityLevel.MINOR)
    @Story("Product search without any search words")
    @Description("Verify that search feature requests the search word when it is missing")
    public void shouldFailSearchOfProductsWithoutKeyword() {
        Response response = productApiService.searchProductWithoutParam();

        response.then()
                .statusCode(200)
                .body("responseCode", equalTo(400))
                .body("message", equalTo("Bad request, search_product parameter is missing in POST request."));
    }
}
