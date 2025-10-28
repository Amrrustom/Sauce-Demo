package testclasses.features;

import com.saucedemo.CartPage;
import com.saucedemo.HomePage;
import PageComponents.AddToCart;
import PageComponents.LoginForm;
import com.saucedemo.LoginPage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.dataproviders.DataProviders;
import utils.models.LoginTestData;

import static utilities.common.assertions.AssertionManager.assertTrue;
import static utilities.common.assertions.AssertionManager.assertEquals;

@Feature("Cart Feature")
@Epic("SauceDemo Cart Scenarios")
public class CartTests {

    @Story("Login and add two items to cart")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in, adds Backpack and Bike Light to cart, verifies cart count and contents.")
    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void loginAndAddTwoItemsToCart(LoginTestData data) {

        // ---------------- LOGIN ----------------
        LoginPage loginPage = new LoginPage();
        loginPage.load();

        LoginForm<HomePage> form = loginPage.getLoginForm();
        HomePage homePage = form.login(data.username, data.password);
        assertTrue(homePage.isLoaded(), "Home page did not load successfully after login.");

        // ---------------- CART ----------------
        AddToCart addToCart = new AddToCart();

        // Add Backpack
        addToCart.addItemById("add-to-cart-sauce-labs-backpack");

        // Add Bike Light
        addToCart.addItemById("add-to-cart-sauce-labs-bike-light");

        CartPage cartPage = new CartPage();

        // Verify cart count
        assertEquals(cartPage.getCartCount(), 2, "Cart count should be 2 after adding two items.");

        // Open cart
        cartPage.openCart();
        assertTrue(cartPage.isCartVisible(), "Cart container should be visible.");

        // Verify items in cart
        assertTrue(cartPage.isItemInCart("Sauce Labs Backpack"), "Backpack should be in the cart.");
        assertTrue(cartPage.isItemInCart("Sauce Labs Bike Light"), "Bike Light should be in the cart.");
    }
}
