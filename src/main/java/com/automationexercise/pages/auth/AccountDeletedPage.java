package com.automationexercise.pages.auth;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private By accountDeletedHeader = By.cssSelector("h2[data-qa='account-deleted']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    @Step("Click 'Continue' button")
    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }

    @Step("Get deleted account page title")
    public String getAccountDeletedHeader() {
        return getText(accountDeletedHeader);
    }
}
