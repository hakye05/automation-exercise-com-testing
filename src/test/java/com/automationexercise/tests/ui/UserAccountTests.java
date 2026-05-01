package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUiUser;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.auth.AccountCreatedPage;
import com.automationexercise.pages.auth.AccountDeletedPage;
import com.automationexercise.pages.auth.LoginPage;
import com.automationexercise.pages.auth.SignupPage;
import com.automationexercise.utils.DataGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

@Epic("User Management")
@Feature("Authentication & Lifecycle")
public class UserAccountTests extends BaseTestUiUser {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Registration")
    @Description("Verify the E2E flow from registration to deletion.")
    public void createAccountThenDelete() {
        openPage("");
        user = DataGenerator.generateUser();

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getSignupHeader(), LoginPage.SIGNUP_HEADER, "Expected signup header to be displayed");

        SignupPage signupPage = loginPage
                .enterSignupName(user.name())
                .enterSignupEmail(user.email())
                .clickSignup();
        Assert.assertEquals(signupPage.getSignupHeader(), SignupPage.SIGNUP_HEADER, "Expected signup header to be displayed");

        Map<String, String> prefilledInfo = signupPage.getPrefilledSignupFields();
        Assert.assertEquals(prefilledInfo.get("name"), user.name(), "Expected prefilled name to match user name");
        Assert.assertEquals(prefilledInfo.get("email"), user.email(), "Expected prefilled email to match user email");

        AccountCreatedPage accountCreatedPage = signupPage
                .signupUser(user);
        Assert.assertEquals(accountCreatedPage.getAccountCreatedHeader(), AccountCreatedPage.ACCOUNT_CREATED, "Expected account created header to be displayed");

        HomePage homePage = accountCreatedPage
                .clickContinue();
        Assert.assertEquals(homePage.getLoggedInAsUser(), HomePage.LOGGED_IN_AS + user.name(), "Expected header to display that user is logged in");

        AccountDeletedPage accountDeletedPage = homePage
                .clickDeleteAccountLink();
        Assert.assertEquals(accountDeletedPage.getAccountDeletedHeader(), AccountDeletedPage.ACCOUNT_DELETED, "Expected account deleted header to be displayed");

        String homeHeaderText = accountDeletedPage
                .clickContinue()
                .getHomeHeader();
        Assert.assertEquals(homeHeaderText, HomePage.HOME_HEADER, "Expected to see a home page title");
        user = null;
    }

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @Story("User Login")
    @Description("Verify valid credentials grant access to account.")
    public void loginAccountWithCorrectCredentials() {
        openPage("");
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), LoginPage.LOGIN_HEADER, "Expected login header to be displayed");

        String loggedInAsUserText = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword(user.password())
                .clickLogin()
                .getLoggedInAsUser();
        Assert.assertEquals(loggedInAsUserText, HomePage.LOGGED_IN_AS + user.name(), "Expected header to display that user is logged in");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("User Login")
    @Description("Verify user gets notified and can not login with incorrect email/password")
    public void loginAccountWithIncorrectCredentials() {
        openPage("");
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), LoginPage.LOGIN_HEADER, "Expected login header to be displayed");

        String incorrectLoginMessage = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword("incorrect123")
                .clickLoginExpectingError()
                .getIncorrectCredentialsMessage();
        Assert.assertEquals(incorrectLoginMessage, LoginPage.CREDENTIALS_INCORRECT, "Expected incorrect credentials message to be displayed");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("User Login")
    @Description("Verify user can log out of account after successful login")
    public void loginAccountThenLogout() {
        openPage("");
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getLoginHeader(), LoginPage.LOGIN_HEADER, "Expected login header to be displayed");

        HomePage homePage = loginPage
                .enterLoginEmail(user.email())
                .enterLoginPassword(user.password())
                .clickLogin();
        Assert.assertEquals(homePage.getLoggedInAsUser(), HomePage.LOGGED_IN_AS + user.name(), "Expected header to display that user is logged in");

        String loginHeaderText = homePage
                .clickLogoutLink()
                .getLoginHeader();
        Assert.assertEquals(loginHeaderText, LoginPage.LOGIN_HEADER, "Expected login header to be displayed");
    }

    @Test
    @Severity(SeverityLevel.NORMAL)
    @Story("User Login")
    @Description("Verify user gets notified and can not create account with existing account's email")
    public void attemptCreatingExistingAccount() {
        openPage("");
        user = DataGenerator.generateUser();
        userApiService.createUser(user);

        LoginPage loginPage = new HomePage(getDriver())
                .clickLoginLink();
        Assert.assertEquals(loginPage.getSignupHeader(), LoginPage.SIGNUP_HEADER, "Expected signup header to be displayed");

        String emailInUseText = loginPage
                .enterSignupName(user.name())
                .enterSignupEmail(user.email())
                .clickSignupExpectingError()
                .getEmailInUseMessage();
        Assert.assertEquals(emailInUseText, LoginPage.EMAIL_IN_USE, "Expected email in use message to be displayed");
    }
}
