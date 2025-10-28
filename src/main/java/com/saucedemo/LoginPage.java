package com.saucedemo;

import PageComponents.LoginForm;
import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.selenium.helperClasses.BrowserActions;
import utilities.selenium.helperClasses.Waits;
import org.openqa.selenium.By;

public class LoginPage {

    private final By usernameField = By.id("user-name");
    private final By loginButton = By.id("login-button");

    @Step("Open SauceDemo login page")
    public void load() {
        BrowserActions.navigateToBaseURL();
        Waits.waitForPageToLoad(10);

        String baseUrl = PropertiesUtils.getProperty("baseUrl");
        LogsUtils.info("Login page loaded successfully: " + baseUrl);
    }

    @Step("Initialize Login form component")
    public LoginForm<HomePage> getLoginForm() {
        return new LoginForm<>(HomePage.class);
    }

    @Step("Verify that Login Page is loaded")
    public boolean isLoaded() {
        try {
            Waits.waitForElementVisibility(usernameField, 5);
            Waits.waitForElementVisibility(loginButton, 5);
            LogsUtils.info("Login page is loaded and visible.");
            return true;
        } catch (Exception e) {
            LogsUtils.error("Login page did not load properly.");
            return false;
        }
    }
}
