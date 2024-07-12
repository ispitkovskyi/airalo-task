package com.airalo.pages;

import com.airalo.config.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public abstract class BasePage {

    protected WebDriver driver;
    protected BasePage(){
        driver = DriverFactory.getInstance().getDriver();
        PageFactory.initElements(driver, this);
    }

    protected By getFieldCssLocator(String fieldName) {
        try {
            String locatorStr = this.getClass().getField(fieldName).getAnnotation(FindBy.class).css();
            return By.cssSelector(locatorStr);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    protected By getClassCssLocator() {
        try {
            String locatorStr = this.getClass().getAnnotation(FindBy.class).css();
            return By.cssSelector(locatorStr);
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void waitForPageLoaded() {
        ExpectedCondition condition = ExpectedConditions.jsReturnsValue("return document.readyState==\"complete\";");
        String timeoutMessage = "Page didn't load after timeout seconds.";
        waitForCondition(condition, timeoutMessage, 10);
    }
    protected void waitForCondition(ExpectedCondition<?> condition){
        waitForCondition(condition, "Element not found until the timeout", 20);
    }
    protected void waitForCondition(ExpectedCondition<?> condition, String timeoutMessage, int timeoutSeconds){
        FluentWait wait = new FluentWait(driver)
                .pollingEvery(Duration.ofMillis(400))
                .withTimeout(Duration.ofSeconds(timeoutSeconds))
                .withMessage(timeoutMessage);
        wait.until(condition);
    }
}
