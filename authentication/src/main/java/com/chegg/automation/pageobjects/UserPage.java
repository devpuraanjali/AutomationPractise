package com.chegg.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class UserPage extends BasePageObject {
    @FindBy(xpath = "//span[contains(text(),'Edit')]")
    private WebElement editButton;

    @FindBy(xpath = "//span[contains(text(),'Create')]")
    private WebElement createButton;

    @FindBy(xpath = "//div[contains(@class, 'content mkcontent is-code-dark')]/p")
    private WebElement pageContent;

    private By editPageLocator = By.id("root");

    public UserPage(WebDriver webDriver, WebElement rootElement) {
        super(webDriver, rootElement);
        waitUntilVisible(pageContent);
    }

    public boolean isEditButtonDisplayed(){
        return editButton.isDisplayed();
    }

    public boolean isCreateButtonDisplayed(){
        return createButton.isDisplayed();
    }

    public EditPage selectEditButton(){
        waitUntill(5);
        editButton.click();
        WebElement editPageWebElement = getDriver().findElement(editPageLocator);
        return new EditPage(getDriver(),editPageWebElement);
    }

    public String getContent(){
        return pageContent.getText();
    }
}