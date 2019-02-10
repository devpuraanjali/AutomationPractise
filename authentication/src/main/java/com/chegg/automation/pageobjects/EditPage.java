package com.chegg.automation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class EditPage extends BasePageObject{
    @FindBy(xpath = "//div[contains(@class, 'CodeMirror cm-s-paper CodeMirror-wrap')]")
    private WebElement editor;

    @FindBy(xpath = "//span[contains(text(),'Save Changes')]/..")
    private WebElement saveChangesButton;

    @FindBy(xpath = "//span[contains(text(),'Discard')]/..")
    private WebElement discardButton;

    private By discardPopupButtonLocator = By.xpath("//a[contains(@class, 'button is-orange') and contains(text(), 'Discard')]");
    private By loggedInUserPageLocator = By.id("root");

    public EditPage(WebDriver webDriver, WebElement rootElement) {
        super(webDriver, rootElement);
    }

    public void enterContent(String content) {
        waitUntilVisible(editor);
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("arguments[0].CodeMirror.setValue(\"" + content + "\");", editor);
    }

    public UserPage saveChanges() {
        waitUntill(5);
        saveChangesButton.click();
        waitUntill(5);
        waitUntilVisible(getDriver().findElement(loggedInUserPageLocator));
        WebElement userPage = getDriver().findElement(loggedInUserPageLocator);
        return new UserPage(getDriver(),userPage);
    }

    public UserPage discardChanges() {
        waitUntill(5);
        discardButton.click();
        WebElement discardPopupButton = getDriver().findElement(discardPopupButtonLocator);
        waitUntilVisible(discardPopupButton);
        discardPopupButton.click();
        WebElement userPage = getDriver().findElement(loggedInUserPageLocator);
        return new UserPage(getDriver(),userPage);
    }
}
