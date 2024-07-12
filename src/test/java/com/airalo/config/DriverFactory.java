package com.airalo.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    private DriverFactory() { }

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance() {
        return instance;
    }
    private final ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        String browserType = EnvironmentProperties.get().browser;

        if (browserType == null)
            browserType = EnvironmentProperties.get().getBrowser();

        switch (browserType) {
            case "chrome":
                return createChromeDriver();
            case "firefox":
                return createFirefoxDriver();
            default:
                throw new RuntimeException("Unknown WebDriver browser: " + browserType);
        }
    });

    public WebDriver getDriver() {
        return driver.get();
    }

    public void reloadPage() {
        driver.get().navigate().refresh();
    }

    public void removeDriver() {
        driver.get().quit();
        driver.remove();
    }

    private WebDriver createFirefoxDriver() {
//        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();

        return driver;
    }

    private WebDriver createChromeDriver() {
//        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--remote-allow-origins=*");
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        return new ChromeDriver(chromeOptions);
    }
}
