package com.airalo.pages;

import com.airalo.config.EnvironmentProperties;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    @FindBy(css = "[data-testid='search-input']")
    public WebElement searchField;

    @FindBy (css = "ul[class*='countries-list']")
    public WebElement searchResults;

    @FindBy (css = "[class*='modal-dialog']")
    public WebElement advertModal;


    public HomePage goTo(){
        driver.get(EnvironmentProperties.get().getHomeUrl());
        waitForPageLoaded();
        if(advertModal.isDisplayed()) {
            advertModal.findElement(By.cssSelector("[data-testid='close-button']")).click();
        }
        return this;
    }
    public HomePage searchByText(String text){
        searchField.sendKeys(text);
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(getFieldCssLocator("searchResults")));
        return this;
    }

    public void assertCountryExist(String countryName){
        Assert.assertTrue("Cound not find country" + countryName, driver.findElement(By.cssSelector("li > [data-testid='" + countryName + "-name']")).isDisplayed());
    }

    public void selectCountry(String countryName){
        driver.findElement(By.cssSelector("li > [data-testid='" + countryName + "-name']")).click();
    }
}
