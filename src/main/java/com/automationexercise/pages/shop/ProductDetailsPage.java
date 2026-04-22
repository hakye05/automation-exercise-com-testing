package com.automationexercise.pages.shop;

import com.automationexercise.models.ProductDetails;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductDetailsPage extends BasePage {

    private By productName = By.cssSelector(".product-information h2");
    private By productCategory = By.cssSelector(".product-information p:nth-of-type(1)");
    private By productPrice = By.cssSelector(".product-information span span");
    private By productAvailability = By.xpath("//p[b[contains(text(),'Availability')]]");
    private By productCondition = By.xpath("//p[b[contains(text(),'Condition')]]");
    private By productBrand = By.xpath("//p[b[contains(text(),'Brand')]]");

    private By productQuantityInput = By.id("quantity");
    private By addToCartButton = By.cssSelector(".product-information .cart");
    private By cartModal = By.id("cartModal");
    private By viewCartButton = By.cssSelector("#cartModal .modal-body a");

    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }

    public ProductDetailsPage changeProductQuantityBy(int amount) {
        type(productQuantityInput, String.valueOf(amount));
        return this;
    }

    public ProductDetailsPage clickAddToCart() {
        click(addToCartButton);
        return this;
    }

    public CartPage acceptViewCartModal() {
        waitForVisibilityOf(cartModal);
        clickAvoidingVignette(viewCartButton);
        return new CartPage(driver);
    }

    // Getter Methods
    public ProductDetails getProductDetails() {
        return new ProductDetails(
                getText(productName),
                getText(productBrand).replace("Brand: ", ""),
                getText(productCategory).replace("Category: ", ""),
                getText(productPrice),
                getText(productAvailability).replace("Availability: ", ""),
                getText(productCondition).replace("Condition: ", "")
        );
    }
}
