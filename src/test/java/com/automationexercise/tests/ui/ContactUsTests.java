package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.models.UserData;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.info.ContactUsPage;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Customer Contact")
@Feature("Contact & Communication")
public class ContactUsTests extends BaseTestUi {

    @Test
    @Story("Contact Form Submission")
    @Description("Verify user can send contact us form with attachment and gets notified about success.")
    public void submitContactUsForm() {
        openPage("");
        String filePath = "src/test/resources/testUploadFile.txt";
        UserData user = DataGenerator.createUser();

        ContactUsPage contactUsPage = new HomePage(getDriver())
                .clickContactUsLink();
        Assert.assertEquals(contactUsPage.getContactUsHeader(), "GET IN TOUCH", "Expected contact us header to be displayed");

        String successMessageText = contactUsPage
                .enterName(user.name())
                .enterEmail(user.email())
                .enterSubject("Subject")
                .enterMessage("Message")
                .uploadAttachmentFile(filePath)
                .clickSubmit()
                .acceptSubmitAlert()
                .getContactUsSuccessMessage();
        Assert.assertEquals(successMessageText, "Success! Your details have been submitted successfully.", "The contact success message was not displayed");
    }
}
