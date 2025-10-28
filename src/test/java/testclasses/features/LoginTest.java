package testclasses.features;

import com.saucedemo.LoginPage;
import com.saucedemo.HomePage;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import utils.dataproviders.DataProviders;
import utils.models.LoginTestData;
import PageComponents.LoginForm;

import static utilities.common.assertions.AssertionManager.assertTrue;
import static utilities.common.assertions.AssertionManager.assertEquals;

@Feature("Login Feature")
@Epic("SauceDemo Login Scenarios")
public class LoginTest {

    @Story("Login with valid credentials")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that user can log in successfully using valid credentials read from JSON file.")
    @Test(
            priority = 1,
            dataProvider = "validLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void verifyValidLogin(LoginTestData data) {
        LoginPage loginPage = new LoginPage();
        loginPage.load();

        LoginForm<HomePage> form = loginPage.getLoginForm();
        HomePage homePage = form.login(data.username, data.password);

        assertTrue(homePage.isLoaded(), "Home page did not load successfully after valid login.");
    }

    @Story("Login with invalid credentials")
    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that an error message is displayed when invalid credentials are used.")
    @Test(
            priority = 2,
            dataProvider = "invalidLoginData",
            dataProviderClass = DataProviders.class,
            groups = {"Smoke"}
    )
    public void verifyInvalidLogin(LoginTestData data) {
        LoginPage loginPage = new LoginPage();
        loginPage.load();

        LoginForm<HomePage> form = loginPage.getLoginForm();
        form.enterUsername(data.username);
        form.enterPassword(data.password);
        form.clickLogin();

        assertTrue(form.isErrorDisplayed(), "Error message should appear for invalid login.");
        String expectedError = "Epic sadface: Username and password do not match any user in this service";
        assertEquals(form.getErrorMessageText().trim(), expectedError, "Error message mismatch for invalid login.");
    }
}
