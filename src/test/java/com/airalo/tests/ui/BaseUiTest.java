package com.airalo.tests.ui;

import com.airalo.config.DriverFactory;
import com.airalo.pages.HomePage;
import com.airalo.pages.PackageDetail;
import com.airalo.pages.StorePage;
import org.junit.After;
import org.junit.BeforeClass;

public abstract class BaseUiTest {

    protected static HomePage homePage;
    protected static PackageDetail packageDetail;
    protected static StorePage storePage;

    @BeforeClass
    public static void setUp(){
        homePage = new HomePage();
        packageDetail = new PackageDetail();
        storePage = new StorePage();
    }

    @After
    public void tearDown(){
        DriverFactory.getInstance().removeDriver();
    }
}
