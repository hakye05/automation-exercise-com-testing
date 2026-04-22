package com.automationexercise.pages.payment;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentSuccessPage extends BasePage {

    private By orderPlacedHeader = By.cssSelector("[data=qa='order-placed']");

    private By downloadInvoiceButton = By.cssSelector("a[href*='/download_invoice/']");
    private By continueButton = By.cssSelector("[data-qa='continue-button']");

    public PaymentSuccessPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }

    public PaymentSuccessPage clickDownloadInvoice() {
        click(downloadInvoiceButton);
        return this;
    }

    // Getter Methods
    public String getOrderPlacedHeader() {
        return getText(orderPlacedHeader);
    }
}
