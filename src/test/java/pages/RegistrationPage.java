package com.demoblaze.pages;

import com.microsoft.playwright.Dialog;
import com.microsoft.playwright.Page;

public class RegistrationPage {

    private final Page page;

    private final String signUpMenu = "#signin2";
    private final String userName = "#sign-username";
    private final String password = "#sign-password";
    private final String signUpButton = "button[onclick='register()']";

    public RegistrationPage(Page page) {
        this.page = page;
    }

    public void openSignUpPopup() {

        page.click(signUpMenu);

        page.waitForSelector(userName);
    }

    public String registerUser(String username, String pwd) {

        final String[] alertMessage = new String[1];

        page.onceDialog(dialog -> {
            alertMessage[0] = dialog.message();
            dialog.accept();
        });

        page.fill(userName, username);
        page.fill(password, pwd);

        page.click(signUpButton);

        page.waitForTimeout(2000);

        return alertMessage[0];
    }

    public boolean isSignUpPopupDisplayed() {

        return page.locator(userName).isVisible();
    }
}