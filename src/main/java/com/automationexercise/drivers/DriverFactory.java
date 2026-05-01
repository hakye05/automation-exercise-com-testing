package com.automationexercise.drivers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;

/**
 * Factory class for managing {@link WebDriver} instances.
 * <p>Uses {@link ThreadLocal} to ensure each thread has its own browser session for parallel execution.</p>
 * */
public class DriverFactory {

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    /**
     * Initializes a new browser session for current thread.
     * @param browser a string of browser name (e.g. "chrome")
     * @throws IllegalArgumentException if provided browser is not supported
     * */
    public static void initDriver(String browser) {
        WebDriver webDriver;

        switch (browser.toLowerCase()) {
            case "chrome" -> webDriver = new ChromeDriver(BrowserOptions.getChromeOptions());
            case "edge" -> webDriver = new EdgeDriver(BrowserOptions.getEdgeOptions());
            default -> throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        webDriver.manage().window().maximize();
        driver.set(webDriver);
    }

    /**
     * Provides the {@link WebDriver} instance of current thread.
     * @return the active thread's {@link WebDriver}
     * */
    public static WebDriver getDriver() {
        return driver.get();
    }

    /**
     * Quits the current thread's {@link WebDriver} and removes it from the thread.
     * Failure to call this method will result in unclosed browser session.
     * */
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
