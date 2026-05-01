package com.automationexercise.pages.auth;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    public static final String LOGIN_HEADER = "Login to your account";
    public static final String SIGNUP_HEADER = "New User Signup!";
    public static final String CREDENTIALS_INCORRECT = "Your email or password is incorrect!";
    public static final String EMAIL_IN_USE = "Email Address already exist!";

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

    @Step("Enter login email: '{0}'")
    public LoginPage enterLoginEmail(String email) {
        type(loginEmailInput, email);
        return this;
    }

    @Step("Enter login password: '{0}'")
    public LoginPage enterLoginPassword(String password) {
        type(loginPasswordInput, password);
        return this;
    }

    @Step("Click 'login' button")
    public HomePage clickLogin() {
        click(loginButton);
        return new HomePage(driver);
    }

    @Step("Click 'login' button expecting error")
    public LoginPage clickLoginExpectingError() {
        click(loginButton);
        return this;
    }

    @Step("Fill login form")
    public LoginPage fillLoginForm(String email, String password) {
        enterLoginEmail(email);
        enterLoginPassword(password);
        return this;
    }

    @Step("Enter signup name: '{0}'")
    public LoginPage enterSignupName(String name) {
        type(signupNameInput, name);
        return this;
    }

    @Step("Enter signup email: '{0}'")
    public LoginPage enterSignupEmail(String email) {
        type(signupEmailInput, email);
        return this;
    }

    @Step("Click 'Signup' button")
    public SignupPage clickSignup() {
        click(signupButton);
        return new SignupPage(driver);
    }

    @Step("Click 'Signup' button expecting error")
    public LoginPage clickSignupExpectingError() {
        click(signupButton);
        return this;
    }

    @Step("Fill signup form")
    public LoginPage fillSignUpForm(String name, String email) {
        enterSignupName(name);
        enterSignupEmail(email);
        return this;
    }

    // Getter Methods
    @Step("Get incorrect email or password message")
    public String getIncorrectCredentialsMessage() {
        return getText(incorrectCredentialsMessage);
    }

    @Step("Get email in use message")
    public String getEmailInUseMessage() {
        return getText(emailInUseMessage);
    }

    @Step("Get login form title")
    public String getLoginHeader() {
        return getText(loginHeader);
    }

    @Step("Get signup form title")
    public String getSignupHeader() {
        return getText(signupHeader);
    }
}
