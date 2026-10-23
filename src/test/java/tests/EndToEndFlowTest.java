package tests;

import base.BaseTest;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndToEndFlowTest extends BaseTest {

    @Test
    public void verifyCompletePurchaseFlow() {

        HomePage homePage =
                new HomePage(page);

        homePage.open();

        homePage.selectProduct(
                "Samsung galaxy s6");

        ProductPage productPage =
                new ProductPage(page);

        productPage.addToCart();

        pages.CartPage cartPage =
                new pages.CartPage(page);

        cartPage.openCart();

        cartPage.clickPlaceOrder();

        cartPage.completePurchase(
                "Automation User",
                "India",
                "Bangalore",
                "4111111111111111",
                "12",
                "2029");

        Assert.assertTrue(
                cartPage.getPurchaseConfirmation()
                        .contains("Thank you"));
    }

    /**
     * Returns number of products present in cart
     */
    public int getCartItemCount() {
        page.waitForLoadState();

        return page.locator("#tbodyid tr").count();
    }
}