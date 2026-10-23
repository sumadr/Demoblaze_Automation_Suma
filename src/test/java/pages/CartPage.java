package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class CartPage {

    private final Page page;

    // Cart
    private final Locator cartLink;
    private final Locator placeOrderBtn;
    private final Locator totalAmount;

    // Order Modal
    private final Locator name;
    private final Locator country;
    private final Locator city;
    private final Locator creditCard;
    private final Locator month;
    private final Locator year;
    private final Locator purchaseBtn;

    // Confirmation
    private final Locator confirmationMessage;
    private final Locator okButton;

    public CartPage(Page page) {

        this.page = page;

        cartLink = page.locator("#cartur");
        placeOrderBtn = page.locator("//button[text()='Place Order']");
        totalAmount = page.locator("#totalp");

        name = page.locator("#name");
        country = page.locator("#country");
        city = page.locator("#city");
        creditCard = page.locator("#card");
        month = page.locator("#month");
        year = page.locator("#year");

        purchaseBtn = page.locator("//button[text()='Purchase']");

        confirmationMessage = page.locator(".sweet-alert h2");
        okButton = page.locator(".confirm");
    }

    /**
     * Open Cart Page
     */
    public void openCart() {

        cartLink.click();

        page.waitForLoadState();

        page.waitForTimeout(2000);
    }

    /**
     * Verify Product Exists
     */
    public boolean productExists(String productName) {

        return page.locator(
                        "//td[text()='" + productName + "']")
                .count() > 0;
    }

    /**
     * Click Place Order
     */
    public void clickPlaceOrder() {

        placeOrderBtn.waitFor();

        placeOrderBtn.click();
    }

    /**
     * Complete Purchase
     */
    public void completePurchase(
            String customerName,
            String customerCountry,
            String customerCity,
            String cardNumber,
            String purchaseMonth,
            String purchaseYear) {

        name.fill(customerName);
        country.fill(customerCountry);
        city.fill(customerCity);
        creditCard.fill(cardNumber);
        month.fill(purchaseMonth);
        year.fill(purchaseYear);

        purchaseBtn.click();
    }

    /**
     * Get Order Confirmation Text
     */
    public String getPurchaseConfirmation() {

        confirmationMessage.waitFor();

        return confirmationMessage.textContent();
    }

    /**
     * Get Confirmation Details
     */
    public String getConfirmationDetails() {

        return page.locator(".sweet-alert p")
                .textContent();
    }

    /**
     * Close Success Popup
     */
    public void clickOk() {

        okButton.click();
    }

    /**
     * Get Cart Total
     */
    public String getTotalAmount() {

        return totalAmount.textContent();
    }

    public int getCartItemCount() {
    page.waitForLoadState();

    return page.locator("#tbodyid tr").count();
    }
}