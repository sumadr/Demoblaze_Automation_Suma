package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class HomePage {

    private final Page page;

    // Header
    private final Locator homeLink;
    private final Locator cartLink;
    private final Locator loginLink;
    private final Locator signUpLink;

    // Categories
    private final Locator phonesCategory;
    private final Locator laptopsCategory;
    private final Locator monitorsCategory;

    public HomePage(Page page) {

        this.page = page;

        homeLink = page.locator("a.nav-link", new Page.LocatorOptions());
        cartLink = page.locator("#cartur");
        loginLink = page.locator("#login2");
        signUpLink = page.locator("#signin2");

        phonesCategory = page.locator("//a[text()='Phones']");
        laptopsCategory = page.locator("//a[text()='Laptops']");
        monitorsCategory = page.locator("//a[text()='Monitors']");
    }

    /**
     * Navigate to DemoBlaze Home Page
     */
    public void open() {

        page.navigate("https://www.demoblaze.com/");

        page.waitForLoadState();
    }

    /**
     * Returns page title
     */
    public String getTitle() {

        return page.title();
    }

    /**
     * Opens Login modal
     */
    public void clickLogin() {

        loginLink.click();
    }

    /**
     * Opens SignUp modal
     */
    public void clickSignUp() {

        signUpLink.click();
    }

    /**
     * Opens Cart page
     */
    public void clickCart() {

        cartLink.click();
    }

    /**
     * Select Phones category
     */
    public void clickPhonesCategory() {

        phonesCategory.click();

        page.waitForTimeout(2000);
    }

    /**
     * Select Laptops category
     */
    public void clickLaptopsCategory() {

        laptopsCategory.click();

        page.waitForTimeout(2000);
    }

    /**
     * Select Monitors category
     */
    public void clickMonitorsCategory() {

        monitorsCategory.click();

        page.waitForTimeout(2000);
    }

    /**
     * Select product by name
     */
    public void selectProduct(String productName) {

        page.locator("//a[text()='" + productName + "']")
                .click();
    }

    /**
     * Verify product displayed on home page
     */
    public boolean isProductDisplayed(String productName) {

        return page.locator(
                        "//a[text()='" + productName + "']")
                .isVisible();
    }

    /**
     * Verify user logged in
     */
    public boolean isUserLoggedIn() {

        return page.locator("#nameofuser")
                .isVisible();
    }

    /**
     * Get logged-in username
     */
    public String getLoggedInUser() {

        return page.locator("#nameofuser")
                .textContent();
    }

    /**
     * Get product count
     */
    public int getProductCount() {

        return page.locator(".card-block").count();
    }
}