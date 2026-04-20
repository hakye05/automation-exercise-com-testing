package com.automationexercise.pages.auth;

import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountDeletedPage extends BasePage {

    private By accountDeletedHeader = By.cssSelector("h2[data-qa='account-deleted']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountDeletedPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage clickContinue() {
        click(continueButton);
        return new LoginPage(driver);
    }

    public String getAccountDeletedHeader() {
        return getText(accountDeletedHeader);
    }
}
