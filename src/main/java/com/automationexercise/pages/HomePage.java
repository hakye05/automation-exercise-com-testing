package com.automationexercise.pages;

import com.automationexercise.pages.auth.AccountDeletedPage;
import com.automationexercise.pages.auth.LoginPage;
import com.automationexercise.pages.base.BasePage;
import com.automationexercise.pages.info.ContactUsPage;
import com.automationexercise.pages.info.TestCasesPage;
import com.automationexercise.pages.shop.CartPage;
import com.automationexercise.pages.shop.ProductDetailsPage;
import com.automationexercise.pages.shop.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    private By homeHeader = By.cssSelector(".item.active h2");
    private By loggedInAsHeader = By.cssSelector(".shop-menu a:has(.fa-user)");

    // Product Elements
    private By productCard = By.cssSelector(".product-image-wrapper");
    private By addToCartButton = By.cssSelector(".productinfo .add-to-cart");
    private By viewProductButton = By.cssSelector(".choose a[href*='/product_details/']");
    private By continueModal = By.id("cartModal");
    private By viewCartButton = By.cssSelector("#cartModal .modal-body a");

    // Navigation Elements
    private By productsLink = By.cssSelector("a[href='/products']");
    private By cartLink = By.cssSelector("a[href='/view_cart']");
    private By testCasesLink = By.cssSelector("a[href='/test_cases']");
    private By contactUsLink = By.cssSelector("a[href='/contact_us']");
    private By loginLink = By.cssSelector("a[href='/login']");
    private By deleteAccountLink = By.cssSelector("a[href='/delete_account']");
    private By logoutLink = By.cssSelector("a[href='/logout']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickLoginLink() {
        clickAvoidingVignette(loginLink);
        return new LoginPage(driver);
    }

    public LoginPage clickLogoutLink() {
        clickAvoidingVignette(logoutLink);
        return new LoginPage(driver);
    }

    public AccountDeletedPage clickDeleteAccountLink() {
        clickAvoidingVignette(deleteAccountLink);
        return new AccountDeletedPage(driver);
    }

    public ProductsPage clickProductsLink() {
        clickAvoidingVignette(productsLink);
        return new ProductsPage(driver);
    }

    public CartPage clickCartLink() {
        clickAvoidingVignette(cartLink);
        return new CartPage(driver);
    }

    public TestCasesPage clickTestCasesLink() {
        clickAvoidingVignette(testCasesLink);
        return new TestCasesPage(driver);
    }

    public ContactUsPage clickContactUsLink() {
        clickAvoidingVignette(contactUsLink);
        return new ContactUsPage(driver);
    }

    public HomePage clickAddToCart(int index) {
        List<WebElement> products = findAll(productCard);
        WebElement targetProduct = products.get(index);

        WebElement cartButton = findNested(targetProduct, addToCartButton);
        scrollToElement(cartButton);
        click(cartButton);
        return this;
    }

    public ProductDetailsPage clickViewProduct(int index) {
        List<WebElement> products = findAll(productCard);
        WebElement targetProduct = products.get(index);

        WebElement viewDetailsButton = findNested(targetProduct, viewProductButton);
        scrollToElement(viewDetailsButton);
        click(viewDetailsButton);
        return new ProductDetailsPage(driver);
    }

    public CartPage acceptViewCartModal() {
        waitForVisibilityOf(continueModal);
        clickAvoidingVignette(viewCartButton);
        return new CartPage(driver);
    }

    // Getter Methods
    public String getLoggedInAsUser() {
        return getText(loggedInAsHeader);
    }

    public String getHomeHeader() {
        return getText(homeHeader);
    }
}
