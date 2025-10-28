package testclasses.features;

import com.saucedemo.LoginPage;
import com.saucedemo.HomePage;
import PageComponents.LoginForm;
import PageComponents.Logout;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.dataproviders.DataProviders;
import utils.models.LoginTestData;

import static utilities.common.assertions.AssertionManager.assertTrue;

@Feature("Logout Feature")
@Epic("SauceDemo Logout Scenarios")
public class LogoutTest {

    @Story("Login and logout")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Logs in with valid credentials and verifies that logout works correctly.")
    @Test(
            dataProvider = "validLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void loginAndLogout(LoginTestData data) {

        // ---------------- LOGIN ----------------
        LoginPage loginPage = new LoginPage();
        loginPage.load();

        LoginForm<HomePage> form = loginPage.getLoginForm();
        HomePage homePage = form.login(data.username, data.password);

        assertTrue(homePage.isLoaded(), "Home page did not load successfully after login.");

        // ---------------- LOGOUT ----------------
        Logout logout = new Logout();
        loginPage = logout.logout();

        assertTrue(loginPage.isLoaded(), "Login page should be visible after logout.");
    }
}
