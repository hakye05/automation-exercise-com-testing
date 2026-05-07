package com.automationexercise.pages.info;

import com.automationexercise.pages.base.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    public static final String TEST_CASES_HEADER = "TEST CASES";

    private By testCasesHeader = By.cssSelector("h2.title");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public String getTestCasesHeader() {
        return getText(testCasesHeader);
    }
}
