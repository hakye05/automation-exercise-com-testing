package com.automationexercise.pages.auth;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountCreatedPage extends BasePage {

    private By accountCreatedHeader = By.cssSelector("h2[data-qa='account-created']");
    private By continueButton = By.cssSelector("a[data-qa='continue-button']");

    public AccountCreatedPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }

    public String getAccountCreatedHeader() {
        return getText(accountCreatedHeader);
    }
}
