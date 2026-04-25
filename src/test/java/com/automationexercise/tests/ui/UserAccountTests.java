package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUiUser;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.auth.AccountCreatedPage;
import com.automationexercise.pages.auth.AccountDeletedPage;
import com.automationexercise.pages.auth.LoginPage;
import com.automationexercise.pages.auth.SignupPage;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("User Management")
@Feature("Authentication & Lifecycle")
public class UserAccountTests extends BaseTestUiUser {

    @Test
    @Story("User Registration")
    @Description("Verify the E2E flow from registration to deletion.")
    public void createAccountThenDelete() {
        openPage("");
        user = DataGenerator.createUser();

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getSignupHeader(), "New User Signup!", "Expected signup header to be displayed");

        SignupPage signupPage = loginPage
                .enterSignupName(user.name())
                .enterSignupEmail(user.email())
                .clickSignup();
        Assert.assertEquals(signupPage.getSignupHeader(), "ENTER ACCOUNT INFORMATION", "Expected signup header to be displayed");

        AccountCreatedPage accountCreatedPage = signupPage
                .signupUser(user);
        Assert.assertEquals(accountCreatedPage.getAccountCreatedHeader(), "ACCOUNT CREATED!", "Expected account created header to be displayed");

        HomePage homePage = accountCreatedPage
                .clickContinue();
        Assert.assertEquals(homePage.getLoggedInAsUser(), "Logged in as " + user.name(), "Expected header to display that user is logged in");

        AccountDeletedPage accountDeletedPage = homePage
                .clickDeleteAccountLink();
        Assert.assertEquals(accountDeletedPage.getAccountDeletedHeader(), "ACCOUNT DELETED!", "Expected account deleted header to be displayed");

        String homeHeaderText = accountDeletedPage
                .clickContinue()
                .getHomeHeader();
        Assert.assertEquals(homeHeaderText, "Full-Fledged practice website for Automation Engineers", "Expected to see a home page title");
        user = null;
    }

    @Test
    @Story("User Login")
    @Description("Verify valid credentials grant access to account.")
    public void loginAccountWithCorrectCredentials() {
        openPage("");
        user = DataGenerator.createUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), "Login to your account", "Expected login header to be displayed");

        String loggedInAsUserText = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword(user.password())
                .clickLogin()
                .getLoggedInAsUser();
        Assert.assertEquals(loggedInAsUserText, "Logged in as " + user.name(), "Expected header to display that user is logged in");
    }

    @Test
    @Story("User Login")
    @Description("Verify user gets notified and can not login with incorrect email/password")
    public void loginAccountWithIncorrectCredentials() {
        openPage("");
        user = DataGenerator.createUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), "Login to your account", "Expected login header to be displayed");

        String incorrectLoginMessage = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword("incorrect123")
                .clickLoginExpectingError()
                .getIncorrectCredentialsMessage();
        Assert.assertEquals(incorrectLoginMessage, "Your email or password is incorrect!", "Expected incorrect credentials message to be displayed");
    }

    @Test
    @Story("User Login")
    @Description("Verify user can log out of account after successful login")
    public void loginAccountThenLogout() {
        openPage("");
        user = DataGenerator.createUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), "Login to your account", "Expected login header to be displayed");

        HomePage homePage = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword(user.password())
                .clickLogin();
        Assert.assertEquals(homePage.getLoggedInAsUser(), "Logged in as " + user.name(), "Expected header to display that user is logged in");

        String loginHeaderText = homePage
                .clickLogoutLink()
                .getLoginHeader();
        Assert.assertEquals(loginHeaderText, "Login to your account", "Expected login header to be displayed");
    }

    @Test
    @Story("User Login")
    @Description("Verify user gets notified and can not create account with existing account's email")
    public void attemptCreatingExistingAccount() {
        openPage("");
        user = DataGenerator.createUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getSignupHeader(), "New User Signup!", "Expected signup header to be displayed");

        String emailInUseText = loginPage
                .enterSignupName(user.name())
                .enterSignupEmail(user.email())
                .clickSignupExpectingError()
                .getEmailInUseMessage();
        Assert.assertEquals(emailInUseText, "Email Address already exist!", "Expected email in use message to be displayed");
    }
}
