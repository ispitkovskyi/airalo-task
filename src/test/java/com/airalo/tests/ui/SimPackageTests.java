package com.airalo.tests.ui;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class SimPackageTests extends BaseUiTest{

    @Test
    public void testJapaneseSimPackage(){
        String countryName = "Japan";
        homePage.goTo();
        homePage.searchByText(countryName);
        homePage.assertCountryExist(countryName);
        homePage.selectCountry(countryName);
        storePage.assertSomeSimPackagesExist();
        storePage.clickSimPackage();
        packageDetail.assertVisible();
        packageDetail.assertPackageDetails("Moshi Moshi", countryName, "1 GB", "7 Days", "$4.50 USD");
        packageDetail.close();
    }
}
