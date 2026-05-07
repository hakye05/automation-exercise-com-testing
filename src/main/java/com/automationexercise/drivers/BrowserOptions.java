package com.automationexercise.drivers;

import com.automationexercise.utils.ConfigReader;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;

public class BrowserOptions {

    public static ChromeOptions getChromeOptions() {
        ChromeOptions options = new ChromeOptions();
        String headlessMode = ConfigReader.getProperty("headless").toLowerCase();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-notifications",
                "--disable-popup-blocking"
        );
        switch (headlessMode) {
            case "new":
                options.addArguments("--headless");
                break;
            case "true":
                options.addArguments("--headless=new");
                break;
            case "false":
                break;
        }
        return options;
    }

    public static EdgeOptions getEdgeOptions() {
        EdgeOptions options = new EdgeOptions();
        String headlessMode = ConfigReader.getProperty("headless").toLowerCase();
        options.addArguments(
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-notifications",
                "--disable-popup-blocking"
        );
        switch (headlessMode) {
            case "new":
                options.addArguments("--headless");
                break;
            case "true":
                options.addArguments("--headless=new");
                break;
            case "false":
                break;
        }
        return options;
    }
}
