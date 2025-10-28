package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.common.PropertiesUtils;
import utilities.selenium.driver.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BrowserActions {

    @Step("Navigating to Base URL from properties file")
    public static void navigateToBaseURL() {
        String baseUrl = PropertiesUtils.getProperty("baseUrl");

        if (baseUrl == null || baseUrl.isEmpty()) {
            LogsUtils.error("Base URL not found in properties file!");
            throw new RuntimeException("Base URL not found in properties file!");
        }

        WebDriverManager.getDriver().get(baseUrl);
        LogsUtils.info("Navigated to Base URL: " + baseUrl);
    }

    @Step("Navigating to custom URL: {url}")
    public static void navigateToURL(String url) {
        WebDriverManager.getDriver().get(url);
        LogsUtils.info("Navigated to URL: " + url);
    }

    @Step("Getting current URL")
    public static String getCurrentURL() {
        LogsUtils.info("Current URL: " + WebDriverManager.getDriver().getCurrentUrl());
        return WebDriverManager.getDriver().getCurrentUrl();
    }

    @Step("Getting page title")
    public static String getCurrentPageTitle() {
        LogsUtils.info("Page title: " + WebDriverManager.getDriver().getTitle());
        return WebDriverManager.getDriver().getTitle();
    }

    @Step("Refreshing the page")
    public static void refreshPage() {
        LogsUtils.info("Refreshing the page");
        WebDriverManager.getDriver().navigate().refresh();
    }

    @Step("Closing the window in focus")
    public static void closeWindow() {
        LogsUtils.info("Closing the window in focus");
        WebDriverManager.getDriver().close();
    }

    // ----------------- إضافات لازم ل CartPage و ItemPage -----------------

    @Step("Find element: {locator}")
    public static WebElement find(By locator) {
        WebElement element = WebDriverManager.getDriver().findElement(locator);
        LogsUtils.info("Found element: " + locator);
        return element;
    }

    @Step("Find all elements: {locator}")
    public static List<WebElement> findAll(By locator) {
        List<WebElement> elements = WebDriverManager.getDriver().findElements(locator);
        LogsUtils.info("Found " + elements.size() + " elements: " + locator);
        return elements;
    }

    @Step("Click on element: {locator}")
    public static void click(By locator) {
        find(locator).click();
        LogsUtils.info("Clicked on element: " + locator);
    }

    @Step("Send keys '{text}' to element: {locator}")
    public static void sendKeys(By locator, String text) {
        find(locator).sendKeys(text);
        LogsUtils.info("Sent keys '" + text + "' to element: " + locator);
    }

    @Step("Click element by ID: {0}")
    public static void clickById(String id) {
        try {
            WebElement element = WebDriverManager.getDriver().findElement(By.id(id));
            element.click();
            LogsUtils.info("Clicked element with ID: " + id);
        } catch (Exception e) {
            LogsUtils.error("Failed to click element with ID: " + id + " | " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
    @Step("Type '{text}' into element located by {locator}")
    public static void type(By locator, String text) {
        try {
            Waits.waitForElementToBeClickable(locator, 5);
            WebElement element = WebDriverManager.getDriver().findElement(locator);
            element.clear(); // نفضي الحقل قبل الكتابة
            element.sendKeys(text);
            LogsUtils.info("Typed text '" + text + "' into element: " + locator);
        } catch (Exception e) {
            LogsUtils.error("Failed to type text '" + text + "' into element: " + locator + " | " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
