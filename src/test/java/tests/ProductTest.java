package tests;

import base.BaseTest;
import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductTest extends BaseTest {

    @Test(retryAnalyzer =
            utils.RetryAnalyzer.class)
    public void verifyProductPageNavigation() {

        HomePage homePage =
                new HomePage(page);

        homePage.open();

        homePage.selectProduct(
                "Samsung galaxy s6");

        Assert.assertTrue(
                page.locator(".name")
                        .textContent()
                        .contains("Samsung"));
    }
}