package com.automationexercise.pages.info;

import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactUsPage extends BasePage {

    private By contactUsHeader = By.cssSelector(".col-sm-12 h2.title");

    private By nameInput = By.cssSelector("[data-qa='name']");
    private By emailInput = By.cssSelector("[data-qa='email']");
    private By subjectInput = By.cssSelector("[data-qa='subject']");
    private By messageInput = By.cssSelector("[data-qa='message']");
    private By fileUploadInput = By.name("upload_file");
    private By submitButton = By.cssSelector("[data-qa='submit-button']");

    private By contactUsSuccessMessage = By.cssSelector(".contact-form .alert-success");

    public ContactUsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Enter contact name: '{0}'")
    public ContactUsPage enterName(String name) {
        type(nameInput, name);
        return this;
    }

    @Step("Enter contact email: '{0}'")
    public ContactUsPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    @Step("Enter contact subject: '{0}'")
    public ContactUsPage enterSubject(String subject) {
        type(subjectInput, subject);
        return this;
    }

    @Step("Enter contact message: '{0}'")
    public ContactUsPage enterMessage(String message) {
        type(messageInput, message);
        return this;
    }

    @Step("Upload contact file")
    public ContactUsPage uploadAttachmentFile(String filePath) {
        uploadFile(fileUploadInput, filePath);
        return this;
    }

    @Step("Click 'Submit' button")
    public ContactUsPage clickSubmit() {
        click(submitButton);
        return this;
    }

    @Step("Click 'OK' button inside alert")
    public ContactUsPage acceptSubmitAlert() {
        acceptAlert();
        return this;
    }

    // Getter Methods
    @Step("Get contact form title")
    public String getContactUsHeader() {
        return getText(contactUsHeader);
    }

    @Step("Get successful contact form submit message")
    public String getContactUsSuccessMessage() {
        return getText(contactUsSuccessMessage);
    }
}
