package com.automationexercise.utils;

import com.automationexercise.drivers.DriverFactory;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult testResult) {
        WebDriver driver = DriverFactory.getDriver();

        if (driver != null) {
            saveScreenshot(driver);
            savePageSource(driver);
        }
    }

    @Attachment(value = "Failure Screenshot", type = "image/png")
    public byte[] saveScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Page Source", type = "text/html")
    public byte[] savePageSource(WebDriver driver) {
        return driver.getPageSource().getBytes();
    }
}
