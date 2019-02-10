package com.chegg.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public abstract class BasePageObject {
    private WebDriver driver;

    private WebElement rootElement = null;

    protected static int DEFAULT_TIMEOUT_SECONDS = 60;

    public WebDriver getDriver() {
        return driver;
    }

    public WebElement getRootElement() {
        return rootElement;
    }

    public BasePageObject(WebDriver webDriver, WebElement rootElement) {
        this.driver = webDriver;
        this.rootElement = rootElement;
        PageFactory.initElements(driver, this);
    }

    public void waitUntilVisible(WebElement expectedElement) {
        (new WebDriverWait(getDriver(), DEFAULT_TIMEOUT_SECONDS)).until(ExpectedConditions.visibilityOf(expectedElement));
    }

    public void waitUntill(int waitTimeSeconds) {
        try {
            Thread.sleep(waitTimeSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
