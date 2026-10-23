package tests;

import base.BaseTest;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;
import utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AddToCartTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyProductAddedToCart() {

        HomePage homePage = new HomePage(page);

        homePage.open();

        homePage.selectProduct("Nexus 6");

        ProductPage productPage =
                new ProductPage(page);

        productPage.addToCart();

        CartPage cartPage =
                new CartPage(page);

        cartPage.openCart();

        Assert.assertTrue(
                cartPage.productExists("Samsung galaxy s6"),
                "Product is not added to cart");

        Assert.assertEquals(
                cartPage.getCartItemCount(),
                2,
                "Cart item count mismatch");
    }
}