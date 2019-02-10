package com.chegg.automation.pageobjects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePageObject {
    @FindBy(xpath = "//span[contains(text(),'All Pages')]")
    private WebElement sidebarAllPages;

    private static String USER_PAGE_SIDEBAR = "//span[contains(text(),'%s')]";


    private By LoggedInUserPageLocator = By.id("root");

    public HomePage(WebDriver webDriver, WebElement rootElement) {
        super(webDriver, rootElement);
    }

    public UserPage goToUserPage(String pageName){
        sidebarAllPages.click();
        waitUntill(5);
        WebElement loggedInUserSidebar = getDriver().findElement(By.xpath(String.format(USER_PAGE_SIDEBAR, pageName)));
        loggedInUserSidebar.click();
        WebElement userPage = getDriver().findElement(LoggedInUserPageLocator);
        return new UserPage(getDriver(),userPage);
    }
}