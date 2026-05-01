package com.automationexercise.pages.payment;

import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentSuccessPage extends BasePage {

    public static final String ORDER_PLACED = "ORDER PLACED!";

    private By orderPlacedHeader = By.cssSelector("[data-qa='order-placed']");

    private By downloadInvoiceButton = By.cssSelector("a[href*='/download_invoice/']");
    private By continueButton = By.cssSelector("[data-qa='continue-button']");

    public PaymentSuccessPage(WebDriver driver) {
        super(driver);
    }

    public HomePage clickContinue() {
        click(continueButton);
        return new HomePage(driver);
    }

    @Step("Click 'Download Invoice' button")
    public PaymentSuccessPage clickDownloadInvoice() {
        click(downloadInvoiceButton);
        return this;
    }

    // Getter Methods
    @Step("Get payment success page title")
    public String getOrderPlacedHeader() {
        return getText(orderPlacedHeader);
    }
}
