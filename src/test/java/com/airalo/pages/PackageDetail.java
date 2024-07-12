package com.airalo.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.By;

@FindBy(css = "[data-testid='package-detail']")
public class PackageDetail extends BasePage {

    @FindBy(css = "[class*='sim-detail-close']")
    public WebElement closeIcon;

    @FindBy(css = "[data-testid='sim-detail-operator-title']")
    public WebElement packageTitle;

    @FindBy(css = "[data-testid='COVERAGE-value']")
    public WebElement coverageValue;

    @FindBy(css = "[data-testid='DATA-value']")
    public WebElement dataValue;

    @FindBy(css = "[data-testid='VALIDITY-value']")
    public WebElement validityValue;

    @FindBy(css = "[data-testid='PRICE-value']")
    public WebElement priceValue;

    public void assertVisible(){
        By packageLocator = getClassCssLocator();
        waitForCondition(ExpectedConditions.visibilityOfElementLocated(packageLocator));
        Assert.assertTrue("Package detail is not visible", packageTitle.isDisplayed());
    }

    public void close(){
        closeIcon.click();
    }

    public void assertPackageDetails(String title, String coverage, String data, String validity, String price){
        Assert.assertEquals(title, packageTitle.getText().trim());
        Assert.assertEquals(coverage, coverageValue.getText().trim());
        Assert.assertEquals(data, dataValue.getText().trim());
        Assert.assertEquals(validity, validityValue.getText().trim());
        Assert.assertEquals(price, priceValue.getText().trim());
    }
}
