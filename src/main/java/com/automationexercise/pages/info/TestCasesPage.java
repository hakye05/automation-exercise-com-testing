package com.automationexercise.pages.info;

import com.automationexercise.pages.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class TestCasesPage extends BasePage {

    private By testCasesHeader = By.cssSelector("h2.title");

    public TestCasesPage(WebDriver driver) {
        super(driver);
    }

    public String getTestCasesHeader() {
        return getText(testCasesHeader);
    }
}
