package com.automationexercise.pages.payment;

import com.automationexercise.models.AddressDetails;
import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckoutPage extends BasePage {

    private By deliveryAddressBox = By.id("address_delivery");
    private By invoiceAddressBox = By.id("address_invoice");

    private By nameLine = By.cssSelector(".address_firstname");
    private By addressLines = By.cssSelector(".address_address1");
    private By cityStateZipLine = By.cssSelector(".address_city");
    private By countryLine = By.cssSelector(".address_country_name");
    private By phoneNumberLine = By.cssSelector(".address_phone");

    private By orderMessageField = By.cssSelector("#ordermsg textarea");
    private By orderButton = By.cssSelector("a[href='/payment']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter optional order message")
    public CheckoutPage enterOrderMessage(String text) {
        type(orderMessageField, text);
        return this;
    }

    @Step("Click 'Place Order' button")
    public PaymentPage clickPlaceOrder() {
        clickAvoidingVignette(orderButton);
        return new PaymentPage(driver);
    }

    // Getter Methods
    @Step("Get delivery address fields from table")
    public AddressDetails getDeliveryAddress() {
        WebElement deliveryBox = find(deliveryAddressBox);
        return getAddressDetailsFrom(deliveryBox);
    }

    @Step("Get invoice address fields from table")
    public AddressDetails getInvoiceAddress() {
        WebElement invoiceBox = find(invoiceAddressBox);
        return getAddressDetailsFrom(invoiceBox);
    }

    // Helper Methods
    private AddressDetails getAddressDetailsFrom(WebElement element) {
        List<WebElement> firstAddressElements = findAllNested(element, addressLines);

        String fullName = getText(findNested(element, nameLine));
        String company = getText(firstAddressElements.get(0));
        String address1 = getText(firstAddressElements.get(1));
        String address2 = getText(firstAddressElements.get(2));
        String cityStateZip = getText(findNested(element, cityStateZipLine));
        String country = getText(findNested(element, countryLine));
        String mobileNumber = getText(findNested(element, phoneNumberLine));
        return new AddressDetails(fullName, company, address1, address2, cityStateZip, country, mobileNumber);
    }
}
