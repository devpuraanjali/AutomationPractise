package com.chegg.automation.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.By;


public class LoginPage extends BasePageObject{
    @FindBy(id = "login-user")
    private WebElement loginUser;

    @FindBy(id = "login-pass")
    private WebElement loginPassword;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement submitButton;

    @FindBy(xpath = "//h4[contains(text(),'The email or password is invalid.')]")
    private WebElement invalidMessage;

    private By homePageLocator = By.id("root");

    public LoginPage(WebDriver webDriver, WebElement rootElement) {
        super(webDriver, rootElement);
    }

    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    public HomePage successfulLogin(String username, String password){
      loginUser.sendKeys(username);
      loginPassword.sendKeys(password);
      submitButton.click();
      WebElement homePageWebElement = getDriver().findElement(homePageLocator);
      return new HomePage(getDriver(),homePageWebElement);
    }

    public LoginPage failedLogin(String username, String password){
        loginUser.sendKeys(username);
        loginPassword.sendKeys(password);
        submitButton.click();
        return new LoginPage(getDriver(), getRootElement());
    }

    public boolean IsinvalidLoginMessageDisplayed(){
        return invalidMessage.isDisplayed();
    }
}