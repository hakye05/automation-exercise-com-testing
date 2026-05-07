package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.pages.HomePage;
import com.automationexercise.pages.info.TestCasesPage;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Website Navigation & Information")
@Feature("Basic Navigation")
public class BasicNavigationTests extends BaseTestUi {

    @Test
    @Severity(SeverityLevel.MINOR)
    @Story("Verify Test Cases Page")
    @Description("Ensure user can navigate to test cases page")
    public void navigateToTestCasesPage() {
        openPage("");

        String testCasesTitle = new HomePage(getDriver())
                .clickTestCasesLink()
                .getTestCasesHeader();
        Allure.step("Verify test cases header was displayed", () -> {
            Assert.assertEquals(testCasesTitle, TestCasesPage.TEST_CASES_HEADER, "Expected test cases header to be visible");
        });
    }
}
