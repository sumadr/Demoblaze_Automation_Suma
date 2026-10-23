package tests;


import base.BaseTest;
import pages.HomePage;
import utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePageTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void verifyHomePageLoads() {

    HomePage homePage = new HomePage(page);

        homePage.open();

        Assert.assertTrue(
                homePage.getTitle().contains("STORE"),
                "Home page not loaded correctly");
    }
}