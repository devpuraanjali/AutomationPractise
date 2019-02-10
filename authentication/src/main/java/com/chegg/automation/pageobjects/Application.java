package com.chegg.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Application {
    private final static String LOGIN_PAGE_LOCATOR = "root";

    public static LoginPage init(WebDriver driver, String url) {
        driver.get(url);
        return new LoginPage(driver, driver.findElement(By.id(LOGIN_PAGE_LOCATOR)));
    }
}
