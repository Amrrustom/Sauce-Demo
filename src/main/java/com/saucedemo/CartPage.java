package com.saucedemo;

import io.qameta.allure.Step;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.Waits;
import org.openqa.selenium.By;

import java.util.List;
import java.util.stream.Collectors;

public class CartPage {

    private final By cartContainer = By.cssSelector("#cart_contents_container");
    private final By itemNames = By.cssSelector(".inventory_item_name");
    private final By cartCount = By.cssSelector("#shopping_cart_container > a > span");
    private final By cartIcon = By.cssSelector("#shopping_cart_container > a");

    @Step("Open cart page")
    public void openCart() {
        BrowserActions.click(cartIcon);
        Waits.waitForPageToLoad(5);
    }

    @Step("Get all item names in cart")
    public List<String> getItemsNames() {
        return BrowserActions.findAll(itemNames)
                .stream()
                .map(e -> e.getText())
                .collect(Collectors.toList());
    }

    @Step("Get cart count from icon")
    public int getCartCount() {
        String text = BrowserActions.find(cartCount).getText();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    @Step("Check if cart container is visible")
    public boolean isCartVisible() {
        return BrowserActions.find(cartContainer).isDisplayed();
    }

    @Step("Check if item '{0}' is in the cart")
    public boolean isItemInCart(String itemName) {
        return getItemsNames().contains(itemName);
    }
}
