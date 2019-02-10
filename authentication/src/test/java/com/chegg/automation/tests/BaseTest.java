package com.chegg.automation.tests;

import com.chegg.automation.utility.BrowserFactory;
import com.chegg.automation.utility.BrowserFactory.BrowserType;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    @Before
    public void setUp() {
        this.driver = BrowserFactory.createDriver(BrowserType.CHROME);
    }

    @After
    public void tearDown(){
        this.driver.quit();
    }
}
