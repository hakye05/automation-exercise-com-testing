package com.automationexercise.pages.auth;

import com.automationexercise.models.UserData;
import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Map;

public class SignupPage extends BasePage {

    private By signupHeader = By.cssSelector(".login-form h2.title");

    private By genderRadio = By.id("id_gender1");
    private By nameInput = By.cssSelector("[data-qa='name']");
    private By emailInput = By.cssSelector("[data-qa='email']");
    private By passwordInput = By.cssSelector("[data-qa='password']");

    private By birthDaySelector = By.cssSelector("[data-qa='days']");
    private By birthMonthSelector = By.cssSelector("[data-qa='months']");
    private By birthYearSelector = By.cssSelector("[data-qa='years']");
    private By newsletterCheckbox = By.id("newsletter");
    private By offerOptionCheckbox = By.id("optin");

    private By firstNameInput = By.cssSelector("[data-qa='first_name']");
    private By lastNameInput = By.cssSelector("[data-qa='last_name']");
    private By companyInput = By.cssSelector("[data-qa='company']");
    private By firstAddressInput = By.cssSelector("[data-qa='address']");
    private By secondAddressInput = By.cssSelector("[data-qa='address2']");
    private By countrySelector = By.cssSelector("[data-qa='country']");
    private By stateInput = By.cssSelector("[data-qa='state']");
    private By cityInput = By.cssSelector("[data-qa='city']");
    private By zipcodeInput = By.cssSelector("[data-qa='zipcode']");
    private By mobileNumberInput = By.cssSelector("[data-qa='mobile_number']");
    private By createAccountButton = By.cssSelector("[data-qa='create-account']");

    public SignupPage(WebDriver driver) {
        super(driver);
    }

    public AccountCreatedPage signupUser(UserData user) {
        click(genderRadio);
        type(passwordInput, user.password());
        selectByTextOf(birthDaySelector, user.birthDate());
        selectByTextOf(birthMonthSelector, user.birthMonth());
        selectByTextOf(birthYearSelector, user.birthYear());
        click(newsletterCheckbox);
        click(offerOptionCheckbox);
        type(firstNameInput, user.firstName());
        type(lastNameInput, user.lastName());
        type(companyInput, user.company());
        type(firstAddressInput, user.address1());
        type(secondAddressInput, user.address2());
        selectByTextOf(countrySelector, user.country());
        type(stateInput, user.state());
        type(cityInput, user.city());
        type(zipcodeInput, user.zipcode());
        type(mobileNumberInput, user.mobileNumber());
        click(createAccountButton);
        return new AccountCreatedPage(driver);
    }

    // Getter Methods
    public Map<String, String> getPrefilledSignupFields() {
        return Map.of(
                "name", getText(nameInput),
                "email", getText(emailInput)
        );
    }

    public String getSignupHeader() {
        return getText(signupHeader);
    }
}
