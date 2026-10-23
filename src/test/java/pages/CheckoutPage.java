package pages;

import com.microsoft.playwright.*;

public class CheckoutPage {
    private Page page;

    public CheckoutPage(Page p) {
        page = p;
    }

    public void purchase() {
        page.locator("button:has-text('Place Order')").click();
        page.locator("#name").fill("John");
        page.locator("#country").fill("India");
        page.locator("#city").fill("Bengaluru");
        page.locator("#card").fill("123456789");
        page.locator("#month").fill("12");
        page.locator("#year").fill("2028");
        page.locator("button:has-text('Purchase')").click();
    }
}