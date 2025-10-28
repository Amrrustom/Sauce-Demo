package PageComponents;

import io.qameta.allure.Step;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.Waits;

public class AddToCart {

    @Step("Add item to cart by its AddToCart button ID: {0}")
    public void addItemById(String addToCartId) {
        BrowserActions.clickById(addToCartId);
        Waits.waitForPageToLoad(2);
    }
}
