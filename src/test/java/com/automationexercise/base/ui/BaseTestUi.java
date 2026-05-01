package com.automationexercise.base.ui;

import com.automationexercise.utils.ConfigReader;
import com.automationexercise.drivers.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTestUi {

    protected WebDriver getDriver() {
        return DriverFactory.getDriver();
    }

    @BeforeMethod
    public void setup() {
        String browser = ConfigReader.getProperty("browser");
        DriverFactory.initDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverFactory.quitDriver();
    }

    public void openPage() {
        getDriver().get(ConfigReader.getProperty("ui.url"));
    }

    public void openPage(String path) {
        getDriver().get(ConfigReader.getProperty("ui.url" + path));
    }
}
