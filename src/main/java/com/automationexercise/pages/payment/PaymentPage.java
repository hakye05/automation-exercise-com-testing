package com.automationexercise.pages.payment;

import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BasePage {

    private By cardHolderNameInput = By.cssSelector("[data-qa='name-on-card']");
    private By cardNumberInput = By.cssSelector("[data-qa='card-number']");
    private By cardCVCInput = By.cssSelector("[data-qa='cvc']");
    private By cardExpirationMonthInput = By.cssSelector("[data-qa='expiry-month']");
    private By cardExpirationYearInput = By.cssSelector("[data-qa='expiry-year']");
    private By confirmPaymentButton = By.cssSelector("[data-qa='pay-button']");

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter payment card holder name")
    public PaymentPage enterCardHolder(String cardHolderName) {
        type(cardHolderNameInput, cardHolderName);
        return this;
    }

    @Step("Enter payment card number")
    public PaymentPage enterCardNumber(String cardNumber) {
        type(cardNumberInput, cardNumber);
        return this;
    }

    @Step("Enter payment card CVC")
    public PaymentPage enterCardCVC(String cardCVC) {
        type(cardCVCInput, cardCVC);
        return this;
    }

    @Step("Enter payment card expiration month")
    public PaymentPage enterCardExpirationMonth(String cardExpirationMonth) {
        type(cardExpirationMonthInput, cardExpirationMonth);
        return this;
    }

    @Step("Enter payment card expiration year")
    public PaymentPage enterCardExpirationYear(String cardExpirationYear) {
        type(cardExpirationYearInput, cardExpirationYear);
        return this;
    }

    @Step("Click 'Pay and Confirm Order' button")
    public PaymentSuccessPage clickConfirmPaymentButton() {
        click(confirmPaymentButton);
        return new PaymentSuccessPage(driver);
    }
}
