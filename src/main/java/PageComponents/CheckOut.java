package PageComponents;

import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.Waits;
import utilities.common.LogsUtils;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class CheckOut {

    // Locators
    private final By checkoutBtn = By.id("checkout");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueBtn = By.id("continue");
    private final By finishBtn = By.id("finish");
    private final By backToProductsBtn = By.id("back-to-products");
    private final By productsTitle = By.cssSelector("#header_container > div.header_secondary_container > span");
    private final By checkoutSummaryContainer = By.cssSelector("#checkout_summary_container > div > div.summary_info > div:nth-child(3)");
    private final By checkoutCompleteContainer = By.cssSelector("#checkout_complete_container > h2");

    @Step("Click Checkout button")
    public void clickCheckout() {
        BrowserActions.click(checkoutBtn);
        Waits.waitForPageToLoad(5);
        LogsUtils.info("Checkout page opened.");
    }

    @Step("Fill user details for checkout")
    public void fillUserDetails(String firstName, String lastName, String postalCode) {
        BrowserActions.type(firstNameField, firstName);
        BrowserActions.type(lastNameField, lastName);
        BrowserActions.type(postalCodeField, postalCode);
        LogsUtils.info("Checkout form filled with: " + firstName + " " + lastName + " " + postalCode);
        BrowserActions.click(continueBtn);
        Waits.waitForPageToLoad(5);
    }

    @Step("Verify checkout summary is visible")
    public boolean isSummaryVisible() {
        return BrowserActions.find(checkoutSummaryContainer).isDisplayed();
    }

    @Step("Get all items in checkout summary")
    public List<String> getSummaryItems() {
        return BrowserActions.findAll(By.cssSelector(".inventory_item_name"))
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    @Step("Finish checkout")
    public void clickFinish() {
        BrowserActions.click(finishBtn);
        Waits.waitForPageToLoad(5);
        LogsUtils.info("Checkout finished.");
    }

    @Step("Verify checkout complete page is visible")
    public boolean isCheckoutCompleteVisible() {
        return BrowserActions.find(checkoutCompleteContainer).isDisplayed();
    }

    @Step("Click Back to Products")
    public void clickBackToProducts() {
        BrowserActions.click(backToProductsBtn);
        Waits.waitForPageToLoad(5);
    }

    @Step("Verify Products page is visible")
    public boolean isProductsPageVisible() {
        return BrowserActions.find(productsTitle).isDisplayed();
    }
}
