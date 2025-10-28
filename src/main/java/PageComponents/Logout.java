package PageComponents;

import com.saucedemo.LoginPage;
import com.saucedemo.HomePage;
import io.qameta.allure.Step;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.Waits;
import org.openqa.selenium.By;

public class Logout {

    private final By burgerMenuBtn = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    @Step("Step - Perform logout from home page")
    public LoginPage logout() {
        // افتح القائمة
        BrowserActions.click(burgerMenuBtn);
        Waits.waitForElementVisibility(logoutLink, 5);

        // اضغط على logout
        BrowserActions.click(logoutLink);
        Waits.waitForPageToLoad(5);

        return new LoginPage(); // بعد logout يرجع للـ Login Page
    }
}
