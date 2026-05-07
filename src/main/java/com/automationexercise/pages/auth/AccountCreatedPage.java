package com.automationexercise.pages.auth;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    public static final String ACCOUNT_CREATED = "ACCOUNT CREATED!";

    private By accountCreatedHeader = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Continue' button")
    public HomePage clickContinue() {
        clickAvoidingVignette(continueButton);
        return new HomePage(driver);
    }

    public String getAccountCreatedHeader() {
        return getText(accountCreatedHeader);
    }
}
