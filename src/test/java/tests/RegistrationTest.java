package com.demoblaze.tests;

import base.BaseTest;
import com.demoblaze.pages.RegistrationPage;
import utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RegistrationTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyUserRegistration() {

        page.navigate("https://www.demoblaze.com");

        RegistrationPage registrationPage =
                new RegistrationPage(page);

        registrationPage.openSignUpPopup();

        String username =
                "user" + System.currentTimeMillis();

        String alertMsg =
                registrationPage.registerUser(
                        username,
                        "Password123");

        Assert.assertEquals(
                alertMsg,
                "Sign up successful.");
    }
}
