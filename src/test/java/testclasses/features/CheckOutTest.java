package testclasses.features;

import com.saucedemo.CartPage;
import com.saucedemo.HomePage;
import com.saucedemo.LoginPage;
import PageComponents.AddToCart;
import PageComponents.CheckOut;
import PageComponents.LoginForm;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.dataproviders.DataProviders;
import utils.models.CheckoutTestData;
import utils.models.LoginTestData;

import java.util.List;

import static utilities.common.assertions.AssertionManager.assertTrue;
import static utilities.common.assertions.AssertionManager.assertEquals;

@Feature("Checkout Feature")
@Epic("SauceDemo Checkout Scenarios")
public class CheckOutTest {

    @Story("Login, add items to cart, checkout and confirm order")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in, adds Backpack and Bike Light to cart, fills checkout info, confirms order, and verifies completion.")
    @Test(
            dataProvider = "validLoginAndCheckoutData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void checkoutScenario(LoginTestData loginData, CheckoutTestData checkoutData) {

        // ---------------- LOGIN ----------------
        LoginPage loginPage = new LoginPage();
        loginPage.load();

        LoginForm<HomePage> form = loginPage.getLoginForm();
        HomePage homePage = form.login(loginData.username, loginData.password);
        assertTrue(homePage.isLoaded(), "Home page did not load successfully after login.");

        // ---------------- ADD TO CART ----------------
        AddToCart addToCart = new AddToCart();
        addToCart.addItemById("add-to-cart-sauce-labs-backpack");
        addToCart.addItemById("add-to-cart-sauce-labs-bike-light");

        CartPage cartPage = new CartPage();
        assertEquals(cartPage.getCartCount(), 2, "Cart count should be 2 after adding two items.");
        cartPage.openCart();
        assertTrue(cartPage.isCartVisible(), "Cart container should be visible.");

        // ---------------- CHECKOUT ----------------
        CheckOut checkout = new CheckOut();
        checkout.clickCheckout();

        // Fill checkout form using data from DataProvider
        checkout.fillUserDetails(
                checkoutData.firstName,
                checkoutData.lastName,
                checkoutData.postalCode
        );

        // Verify summary page
        assertTrue(checkout.isSummaryVisible(), "Checkout summary should be visible.");

        List<String> itemsInSummary = checkout.getSummaryItems();
        assertTrue(itemsInSummary.contains("Sauce Labs Backpack"), "Backpack should be in checkout summary.");
        assertTrue(itemsInSummary.contains("Sauce Labs Bike Light"), "Bike Light should be in checkout summary.");

        // Finish checkout
        checkout.clickFinish();
        assertTrue(checkout.isCheckoutCompleteVisible(), "Checkout complete page should be visible.");

        // Back to products
        checkout.clickBackToProducts();
        assertTrue(checkout.isProductsPageVisible(), "Should be back to Products page.");
    }
}
