package com.automationexercise.pages.info;

import com.automationexercise.pages.base.BasePage;
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

    public ContactUsPage enterName(String name) {
        type(nameInput, name);
        return this;
    }

    public ContactUsPage enterEmail(String email) {
        type(emailInput, email);
        return this;
    }

    public ContactUsPage enterSubject(String subject) {
        type(subjectInput, subject);
        return this;
    }

    public ContactUsPage enterMessage(String message) {
        type(messageInput, message);
        return this;
    }

    public ContactUsPage uploadAttachmentFile(String filePath) {
        uploadFile(fileUploadInput, filePath);
        return this;
    }

    public ContactUsPage clickSubmit() {
        click(submitButton);
        return this;
    }

    public ContactUsPage acceptSubmitAlert() {
        acceptAlert();
        return this;
    }

    // Getter Methods
    public String getContactUsHeader() {
        return getText(contactUsHeader);
    }

    public String getContactUsSuccessMessage() {
        return getText(contactUsSuccessMessage);
    }
}
