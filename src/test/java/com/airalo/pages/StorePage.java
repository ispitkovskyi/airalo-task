package com.airalo.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@FindBy(className = "store-container")
public class StorePage extends BasePage{

    @FindBy(css = "a[data-testid='sim-package-item']")
    public WebElement simPackage;

    public StorePage assertSomeSimPackagesExist(){
            By locator = getFieldCssLocator("simPackage");
            waitForCondition(ExpectedConditions.numberOfElementsToBeMoreThan(locator, 0));
            Assert.assertTrue(driver.findElements(locator).size() > 0);
        return this;
    }

    public void clickSimPackage(){
        simPackage.click();
    }
}
