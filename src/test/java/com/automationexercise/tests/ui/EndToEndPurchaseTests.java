package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUiUser;
import com.automationexercise.models.AddressDetails;
import com.automationexercise.models.PaymentDetails;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.auth.AccountCreatedPage;
import com.automationexercise.pages.auth.AccountDeletedPage;
import com.automationexercise.pages.payment.CheckoutPage;
import com.automationexercise.pages.payment.PaymentSuccessPage;
import com.automationexercise.pages.shop.CartPage;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Shop Functionality")
@Feature("Checkout & Order Placement")
public class EndToEndPurchaseTests extends BaseTestUiUser {

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Registration During Checkout and Order Placement")
    @Description("Add product to cart, proceed to checkout, create account, " +
            "verify order details, proceed with payment and delete account.")
    public void registerDuringCheckoutAndOrder() {
        openPage();
        user = DataGenerator.generateUser();
        PaymentDetails paymentData = DataGenerator.generatePaymentData(user.firstName(), user.lastName());
        AddressDetails addressData = DataGenerator.generateAddressData(user);

        CartPage cartPage = new HomePage(getDriver())
                .clickAddToCart(0)
                .acceptViewCartModal();
        Assert.assertEquals(cartPage.getCartHeader(), CartPage.CART_HEADER, "Expected cart header to be displayed");

        AccountCreatedPage accountCreatedPage = cartPage
                .clickCheckoutButtonAsGuest()
                .clickRegisterWithinModal()
                .fillSignUpForm(user.name(), user.email())
                .clickSignup()
                .signupUser(user);
        Assert.assertEquals(accountCreatedPage.getAccountCreatedHeader(), AccountCreatedPage.ACCOUNT_CREATED, "Expected account created header to be displayed");

        HomePage homePage = accountCreatedPage
                .clickContinue();
        Assert.assertEquals(homePage.getLoggedInAsUser(), HomePage.LOGGED_IN_AS + user.name(), "Expected header to display that user is logged in");

        CheckoutPage checkoutPage = homePage
                .clickCartLink()
                .clickCheckoutButton();
        AddressDetails deliveryAddress = checkoutPage.getDeliveryAddress();
        AddressDetails invoiceAddress = checkoutPage.getInvoiceAddress();
        Assert.assertEquals(deliveryAddress, addressData, "Delivery address does not match user address");
        Assert.assertEquals(invoiceAddress, addressData, "Invoice address does not match user address");

        PaymentSuccessPage paymentSuccessPage = checkoutPage
                .clickPlaceOrder()
                .enterCardHolder(paymentData.cardHolderName())
                .enterCardNumber(paymentData.cardNumber())
                .enterCardCVC(paymentData.cvc())
                .enterCardExpirationMonth(paymentData.expirationMonth())
                .enterCardExpirationYear(paymentData.expirationYear())
                .clickConfirmPaymentButton();
        Assert.assertEquals(paymentSuccessPage.getOrderPlacedHeader(), PaymentSuccessPage.ORDER_PLACED, "Expected successful order message to be displayed");

        AccountDeletedPage accountDeletedPage = paymentSuccessPage
                .clickContinue()
                .clickDeleteAccountLink();
        Assert.assertEquals(accountDeletedPage.getAccountDeletedHeader(), AccountDeletedPage.ACCOUNT_DELETED, "Expected account deleted header to be displayed");

        String homeHeaderText = accountDeletedPage
                .clickContinue()
                .getHomeHeader();
        Assert.assertEquals(homeHeaderText, HomePage.HOME_HEADER, "Expected to see a home page title");

        user = null;
    }

    @Test
    @Severity(SeverityLevel.BLOCKER)
    @Story("Registration Before Checkout and Order Placement")
    @Description("Create account, add product to cart, proceed to checkout, " +
            "verify order details, proceed with payment and delete account.")
    public void registerBeforeCheckoutAndOrder() {
        openPage();
        user = DataGenerator.generateUser();
        PaymentDetails paymentData = DataGenerator.generatePaymentData(user.firstName(), user.lastName());
        AddressDetails addressData = DataGenerator.generateAddressData(user);

        AccountCreatedPage accountCreatedPage = new HomePage(getDriver())
                .clickLoginLink()
                .fillSignUpForm(user.name(), user.email())
                .clickSignup()
                .signupUser(user);
        Assert.assertEquals(accountCreatedPage.getAccountCreatedHeader(), AccountCreatedPage.ACCOUNT_CREATED, "Expected account created header to be displayed");

        HomePage homePage = accountCreatedPage
                .clickContinue();
        Assert.assertEquals(homePage.getLoggedInAsUser(), HomePage.LOGGED_IN_AS + user.name(), "Expected header to display that user is logged in");

        CartPage cartPage = homePage
                .clickAddToCart(0)
                .acceptViewCartModal();
        Assert.assertEquals(cartPage.getCartHeader(), CartPage.CART_HEADER, "Expected cart header to be displayed");

        CheckoutPage checkoutPage = homePage
                .clickCartLink()
                .clickCheckoutButton();
        AddressDetails deliveryAddress = checkoutPage.getDeliveryAddress();
        AddressDetails invoiceAddress = checkoutPage.getInvoiceAddress();
        Assert.assertEquals(deliveryAddress, addressData, "Delivery address does not match user address");
        Assert.assertEquals(invoiceAddress, addressData, "Delivery address does not match user address");

        PaymentSuccessPage paymentSuccessPage = checkoutPage
                .clickPlaceOrder()
                .enterCardHolder(paymentData.cardHolderName())
                .enterCardNumber(paymentData.cardNumber())
                .enterCardCVC(paymentData.cvc())
                .enterCardExpirationMonth(paymentData.expirationMonth())
                .enterCardExpirationYear(paymentData.expirationYear())
                .clickConfirmPaymentButton();
        Assert.assertEquals(paymentSuccessPage.getOrderPlacedHeader(), PaymentSuccessPage.ORDER_PLACED, "Expected successful order message to be displayed");

        AccountDeletedPage accountDeletedPage = paymentSuccessPage
                .clickContinue()
                .clickDeleteAccountLink();
        Assert.assertEquals(accountDeletedPage.getAccountDeletedHeader(), AccountDeletedPage.ACCOUNT_DELETED, "Expected account deleted header to be displayed");

        String homeHeaderText = accountDeletedPage
                .clickContinue()
                .getHomeHeader();
        Assert.assertEquals(homeHeaderText, HomePage.HOME_HEADER, "Expected to see a home page title");

        user = null;
    }
}
