package com.automationexercise.tests.ui;

import com.automationexercise.base.ui.BaseTestUi;
import com.automationexercise.pages.HomePage;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("Website Navigation & Information")
@Feature("Basic Navigation")
public class BasicNavigationTests extends BaseTestUi {

    @Test
    @Story("Verify Test Cases Page")
    @Description("Ensure user can navigate to test cases page")
    public void navigateToTestCasesPage() {
        openPage("");

        String testCasesTitle = new HomePage(getDriver())
                .clickTestCasesLink()
                .getTestCasesHeader();
        Assert.assertEquals(testCasesTitle, "TEST CASES", "Expected test cases header to be visible");
    }
}
