package com.automationexercise.pages;

import com.automationexercise.pages.auth.LoginPage;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage extends BasePage {

    private By homeHeader = By.cssSelector(".item.active h2");
    private By loggedInAsHeader = By.cssSelector(".shop-menu a:has(.fa-user)");

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

    // Getter Methods
    public String getLoggedInAsUser() {
        return getText(loggedInAsHeader);
    }

    public String getHomeHeader() {
        return getText(homeHeader);
    }
}
