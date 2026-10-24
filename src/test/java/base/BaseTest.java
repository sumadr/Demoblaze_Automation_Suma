package base;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.*;
import org.testng.ITestResult;
import org.testng.annotations.*;
import utils.ExtentManager;

public class BaseTest {
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.navigate("https://www.demoblaze.com/");
    }

    @AfterMethod
    public void tearDown() {
        browser.close();
        playwright.close();
    }
    @BeforeSuite
    public void startReport() {

        extent = ExtentManager.getInstance();
    }
    @BeforeMethod
    public void createTest(ITestResult result) {

        test =
                extent.createTest(
                        result.getMethod()
                                .getMethodName());
    }
    @AfterSuite
    public void endReport() {

        extent.flush();
    }

}