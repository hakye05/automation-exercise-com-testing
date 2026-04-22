package com.automationexercise.pages.payment;

import com.automationexercise.pages.base.BasePage;
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

    public PaymentPage enterCardHolder(String cardHolderName) {
        type(cardHolderNameInput, cardHolderName);
        return this;
    }

    public PaymentPage enterCardNumber(String cardNumber) {
        type(cardNumberInput, cardNumber);
        return this;
    }

    public PaymentPage enterCardCVC(String cardCVC) {
        type(cardCVCInput, cardCVC);
        return this;
    }

    public PaymentPage enterCardExpirationMonth(String cardExpirationMonth) {
        type(cardExpirationMonthInput, cardExpirationMonth);
        return this;
    }

    public PaymentPage enterCardExpirationYear(String cardExpirationYear) {
        type(cardExpirationYearInput, cardExpirationYear);
        return this;
    }

    public PaymentSuccessPage clickConfirmPaymentButton() {
        click(confirmPaymentButton);
        return new PaymentSuccessPage(driver);
    }
}
