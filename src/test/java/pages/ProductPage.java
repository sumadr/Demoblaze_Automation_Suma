package pages;

import com.microsoft.playwright.*;

public class ProductPage {
    private Page page;

    public ProductPage(Page p) {
        page = p;
    }

    public void addToCart() {
        page.onceDialog(d -> d.accept());
        page.locator("a:has-text('Add to cart')").click();
    }
}