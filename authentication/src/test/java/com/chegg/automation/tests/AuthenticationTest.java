package com.chegg.automation.tests;

import com.chegg.automation.pageobjects.*;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticationTest extends BaseTest {
    /**
     *  Test covers redirecting to login page from landing page url , validates failed login attempt
     *  and successful login attempt . Returns the home page of the application
     */
    @Test
    public void validateuserLoginFlow(){
        LoginPage loginPage = Application.init(this.getDriver(), "https://chegg-qa-challenge.herokuapp.com/");

        String expectedRedirectedUrl = "https://chegg-qa-challenge.herokuapp.com/login";
        Assert.assertEquals("Fail: The URL redirect is not working", expectedRedirectedUrl, loginPage.getUrl());

        loginPage = loginPage.failedLogin("test","test");
        Assert.assertTrue("Fail: The invalid login message is not displayed.",loginPage.IsinvalidLoginMessageDisplayed());

        HomePage homePage = loginPage.successfulLogin("testuser+je8ds0@example.com","testje8ds0");
    }

    /**
     *Test covers validating the edit and create button existence on the screen. An attempt to validate saved changes
     * are persisted. When the user discards the changes , the content should not be saved
     */
    @Test
    public void validateUserPageEditFlowSuccess(){
        LoginPage loginPage = Application.init(this.getDriver(), "https://chegg-qa-challenge.herokuapp.com/");
        HomePage homePage = loginPage.successfulLogin("testuser+je8ds0@example.com","testje8ds0");
        UserPage loggedInUserPage = homePage.goToUserPage("Testuser Je 8 Ds 0 Dfd");
        Assert.assertTrue("Fail: Edit button is not visible",loggedInUserPage.isEditButtonDisplayed());
        Assert.assertTrue("Fail: Create button is not visible",loggedInUserPage.isCreateButtonDisplayed());

        // for saving changes
        EditPage editPage = loggedInUserPage.selectEditButton();
        String newContentToBeSaved = String.format("Content at this Time:%s", System.currentTimeMillis());
        editPage.enterContent(newContentToBeSaved);
        loggedInUserPage = editPage.saveChanges();
        Assert.assertEquals("Fail: The saved content is not persisted", newContentToBeSaved, loggedInUserPage.getContent());

        // for discard changes
        editPage = loggedInUserPage.selectEditButton();
        String contentToBeDiscarded = "Discard ME";
        editPage.enterContent(contentToBeDiscarded);
        loggedInUserPage = editPage.discardChanges();
        Assert.assertNotEquals("Fail: New change is persisted", contentToBeDiscarded, loggedInUserPage.getContent());
    }
}
