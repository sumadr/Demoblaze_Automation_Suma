package tests;

import base.BaseTest;
import pages.*;
import utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PurchaseFlowTest extends BaseTest {
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void purchase() {
        new LoginPage(page).login("testuser", "password");
        new HomePage(page).selectProduct("Samsung galaxy s6");
        new ProductPage(page).addToCart();
        new CartPage(page).openCart();
        new CheckoutPage(page).purchase();
        Assert.assertTrue(page.locator(".sweet-alert").isVisible());
    }
}