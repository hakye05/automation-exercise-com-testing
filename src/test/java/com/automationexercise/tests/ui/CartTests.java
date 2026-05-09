package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.models.CartItemDetails;
import com.automationexercise.models.ProductCardDetails;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.shop.CartPage;
import com.automationexercise.pages.shop.ProductDetailsPage;
import com.automationexercise.pages.shop.ProductsPage;
import com.automationexercise.utils.DataConverter;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.math.BigDecimal;
import java.util.List;

@Epic("Cart Functionality")
@Feature("Cart Management")
public class CartTests extends BaseTestUi {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Add Item to Cart")
    @Description("Verify multiple products addition to cart and their correctness within the cart.")
    public void addProductsToCartAndVerify() {
        openPage("");
        int quantity = 1;

        ProductsPage productsPage = new HomePage(getDriver())
                .clickProductsLink();
        List<ProductCardDetails> products = productsPage.getProductCardDetails(0, 1);

        CartPage cartPage = productsPage
                .clickAddToCart(0)
                .dismissViewCartModal()
                .clickAddToCart(1)
                .acceptViewCartModal();
        List<CartItemDetails> cartItems = cartPage
                .waitForCartVisibility()
                .getAllCartItemDetails();

        SoftAssert softAssert = new SoftAssert();
        for (int i = 0; i < products.size(); i++) {
            ProductCardDetails expectedData = products.get(i);
            CartItemDetails actualData = cartItems.get(i);

            Allure.step("Checking product: " + expectedData.name(), () -> {
                softAssert.assertEquals(actualData.name(), expectedData.name(), "Name mismatch for: " + expectedData.name());
                softAssert.assertEquals(actualData.price(), expectedData.price(), "Price mismatch for: " + expectedData.name());
                softAssert.assertEquals(actualData.quantity(), "1", "Expected quantity to be 1 for: " + expectedData.name());

                BigDecimal expectedPrice = DataConverter.convertToDecimalNumber(expectedData.price());
                BigDecimal actualTotal = DataConverter.convertToDecimalNumber(expectedData.price());
                BigDecimal expectedTotal = expectedPrice.multiply(new BigDecimal(quantity));

                softAssert.assertEquals(actualTotal, expectedTotal, "Total price mismatch for: " + expectedData.name());
            });
        }
        softAssert.assertAll();
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("Change Cart Item Quantity")
    @Description("Verify that quantity change is correctly passed from product details page to cart page.")
    public void checkAddedProductQuantity() {
        openPage("");
        int quantity = 4;

        ProductDetailsPage productDetailsPage = new HomePage(getDriver())
                .clickViewProduct(0);
        // check that details r displayed
        CartPage cartPage = productDetailsPage
                .changeProductQuantityBy(quantity)
                .clickAddToCart()
                .acceptViewCartModal();
        int actualQuantity = Integer.parseInt(cartPage.getCartItemQuantity(0));
        Allure.step("Checking the product amount inside the cart", () -> {
            Assert.assertEquals(actualQuantity, quantity, "The amount inside the cart does not match the added product amount.");
        });
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("Remove Cart Items")
    @Description("Verify the removal of added items from cart.")
    public void removeProductsFromCart() {
        openPage("");

        CartPage cartPage = new HomePage(getDriver())
                .clickAddToCart(0)
                .acceptViewCartModal();
        Assert.assertEquals(cartPage.getCartHeader(), CartPage.CART_HEADER, "Expected cart header to be displayed.");

        String emptyCartHeader = cartPage
                .clickDeleteCartItem(0)
                .waitForCartClear()
                .getEmptyCartHeader();
        Allure.step("Verify cart is empty", () -> {
            Assert.assertTrue(emptyCartHeader.contains(CartPage.CART_EMPTY), "Expected empty cart header to be displayed.");
        });
    }
}
