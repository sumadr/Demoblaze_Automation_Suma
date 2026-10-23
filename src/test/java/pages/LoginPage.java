package pages;

import com.microsoft.playwright.*;

public class LoginPage {
    private Page page;

    public LoginPage(Page p) {
        page = p;
    }

    public void login(String u, String p) {
        page.locator("#login2").click();
        page.locator("#loginusername").fill(u);
        page.locator("#loginpassword").fill(p);
        page.locator("button:has-text('Log in')").click();
    }
}