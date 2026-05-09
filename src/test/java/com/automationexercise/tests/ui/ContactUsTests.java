package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.models.UserData;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.info.ContactUsPage;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Customer Contact")
@Feature("Contact & Communication")
public class ContactUsTests extends BaseTestUi {

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Contact Form Submission")
    @Description("Verify user can send contact us form with attachment and gets notified about success.")
    public void submitContactUsForm() {
        openPage("");
        String filePath = "src/test/resources/testUploadFile.txt";
        UserData user = DataGenerator.generateUser();

        ContactUsPage contactUsPage = new HomePage(getDriver())
                .clickContactUsLink();
        Assert.assertEquals(contactUsPage.getContactUsHeader(), ContactUsPage.CONTACT_US_HEADER, "Expected contact us header to be displayed.");

        String successMessageText = contactUsPage
                .enterName(user.name())
                .enterEmail(user.email())
                .enterSubject("Subject")
                .enterMessage("Message")
                .uploadAttachmentFile(filePath)
                .clickSubmit()
                .acceptSubmitAlert()
                .getContactUsSuccessMessage();
        Allure.step("Verify contact success message is displayed", () -> {
            Assert.assertEquals(successMessageText, ContactUsPage.CONTACT_SUCCESSFUL, "The contact success message was not displayed.");
        });
    }
}
