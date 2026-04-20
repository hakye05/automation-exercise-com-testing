package com.automationexercise.pages.auth;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    private By loginHeader = By.cssSelector(".login-form h2");
    private By signupHeader = By.cssSelector(".signup-form h2");

    private By loginEmailInput = By.cssSelector("[data-qa='login-email']");
    private By loginPasswordInput = By.cssSelector("[data-qa='login-password']");
    private By loginButton = By.cssSelector("[data-qa='login-button']");

    private By signupNameInput = By.cssSelector("[data-qa='signup-name']");
    private By signupEmailInput = By.cssSelector("[data-qa='signup-email']");
    private By signupButton = By.cssSelector("[data-qa='signup-button']");

    private By incorrectCredentialsMessage = By.cssSelector(".login-form p[style*='color: red']");
    private By emailInUseMessage = By.cssSelector(".signup-form p[style*='color: red']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterLoginEmail(String email) {
        type(loginEmailInput, email);
        return this;
    }

    public LoginPage enterLoginPassword(String password) {
        type(loginPasswordInput, password);
        return this;
    }

    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage(driver);
    }

    public LoginPage clickLoginExpectingError() {
        click(loginButton);
        return this;
    }

    public LoginPage enterSignupName(String name) {
        type(signupNameInput, name);
        return this;
    }

    public LoginPage enterSignupEmail(String email) {
        type(signupEmailInput, email);
        return this;
    }

    public SignupPage clickSignup() {
        click(signupButton);
        return new SignupPage(driver);
    }

    public LoginPage clickSignupExpectingError() {
        click(signupButton);
        return this;
    }

    // Getter Methods
    public String getIncorrectCredentialsMessage() {
        return getText(incorrectCredentialsMessage);
    }

    public String getEmailInUseMessage() {
        return getText(emailInUseMessage);
    }

    public String getLoginHeader() {
        return getText(loginHeader);
    }

    public String getSignupHeader() {
        return getText(signupHeader);
    }
}
