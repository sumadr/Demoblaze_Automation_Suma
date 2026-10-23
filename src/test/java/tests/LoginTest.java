package tests;

import base.BaseTest;
import pages.LoginPage;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test
    public void login() {
        new LoginPage(page).login("testuser", "password");
    }
}