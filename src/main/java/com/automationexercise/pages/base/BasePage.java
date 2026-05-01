package com.automationexercise.pages.base;

import com.automationexercise.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(ConfigReader.getLongProperty("timeout.seconds")));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void click(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    protected void clickAvoidingVignette(By locator) {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
        clickAvoidingVignette(element);
    }

    protected void clickAvoidingVignette(WebElement element) {
        String destination = element.getAttribute("href");
        element.click();
        if (driver.getCurrentUrl().contains("#google_vignette") && destination != null) {
            driver.get(destination);
        }
    }

    protected void type(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    protected String getTextFromField(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator)).getAttribute("value");
    }

    protected void selectByTextOf(By locator, String text) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        Select select = new Select(element);
        select.selectByVisibleText(text);
    }

    protected void uploadFile(By locator, String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IllegalArgumentException("File not found at " + filePath);
        }
        type(locator, file.getAbsolutePath());
    }

    protected void acceptAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();
    }

    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected WebElement findNested(WebElement parent, By childLocator) {
        return wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(parent, childLocator));
    }

    protected List<WebElement> findAll(By locator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    protected List<WebElement> findAllNested(WebElement parent, By locator) {
        return parent.findElements(locator);
    }

    protected void waitForVisibilityOf(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForInvisibilityOf(By locator) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    protected void scrollTo(By locator) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block: 'center'});", element);
    }
}
