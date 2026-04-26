package com.automationexercise.tests.ui;

import com.automationexercise.api.services.ProductApiService;
import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.models.ProductCardDetails;
import com.automationexercise.models.ProductDetails;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.shop.ProductDetailsPage;
import com.automationexercise.pages.shop.ProductsPage;
import com.automationexercise.utils.DataConverter;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;
import java.util.Map;

@Epic("Shop Functionality")
@Feature("Product Search & Discovery")
public class ProductTests extends BaseTestUi {

    @Test
    @Story("Search Products")
    @Description("Verify search engine returns products that are relevant to user's search input.")
    public void searchProduct() {
        openPage("");
        String searchWord = "top";

        ProductsPage productsPage = new HomePage(getDriver())
                .clickProductsLink();
        Assert.assertEquals(productsPage.getProductsHeader(), "ALL PRODUCTS", "Expected products header to be displayed");

        productsPage = productsPage
                .enterSearchWord(searchWord)
                .clickSearchButton();
        Assert.assertEquals(productsPage.getProductsHeader(), "SEARCHED PRODUCTS", "Expected searched products header to be displayed");

        List<String> searchedProductNames = productsPage.getAllProductNames();
        Response response = new ProductApiService().searchProduct(searchWord);
        Map<String, String> apiSearchData = response.jsonPath().getMap("products.collect { [it.name, it.category.category] }.collectEntries()");

        SoftAssert softAssert = new SoftAssert();
        for (String productName : searchedProductNames) {
            String lowerName = productName.toLowerCase();
            String lowerSearch = searchWord.toLowerCase();

            // Check 1: Is the word in the name
            if (lowerName.contains(lowerSearch)) {
                continue;
            }
            // Check 2: If not in name, check the Category from API response
            String category = apiSearchData.get(productName);
            softAssert.assertTrue(category.toLowerCase().contains(lowerSearch),
                    String.format("Product '%s' is invalid. Search word '%s' not found in Name OR Category (%s)",
                            productName, searchWord, category));
        }
        softAssert.assertAll();
    }

    @Test
    @Story("Product Card Integrity")
    @Description("Verify that product list loads correctly and that product details page displays product information accurately.")
    public void checkProductsAndProductDetails() {
        openPage("");

        ProductsPage productsPage = new HomePage(getDriver())
                .clickProductsLink();
        Assert.assertEquals(productsPage.getProductsHeader(), "ALL PRODUCTS", "Expected products header to be displayed");
        Assert.assertFalse(productsPage.getAllProductNames().isEmpty(), "Expected products list not to be empty");

        ProductCardDetails productCardDetails = productsPage.getProductCardDetails(0);
        ProductDetailsPage productDetailsPage = productsPage
                .clickViewProductDetails(0);
        ProductDetails productDetails = productDetailsPage.getProductDetails();

        String cardPrice = DataConverter.sanitizePrice(productCardDetails.price());
        String detailsPrice = DataConverter.sanitizePrice(productDetails.price());

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(productCardDetails.name(), productDetails.name(), "Product name on card does not match details name");
        softAssert.assertEquals(cardPrice, detailsPrice, "Product price on card does not match details price");
        softAssert.assertFalse(productDetails.brand().isBlank(), "Product brand is blank");
        softAssert.assertFalse(productDetails.category().isBlank(), "Product category is blank");
        softAssert.assertFalse(productDetails.availability().isBlank(), "Product availability is blank");
        softAssert.assertFalse(productDetails.condition().isBlank(), "Product condition is blank");
        softAssert.assertAll();
    }
}
