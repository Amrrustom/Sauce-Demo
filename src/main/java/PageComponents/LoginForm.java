package PageComponents;

import org.openqa.selenium.By;
import io.qameta.allure.Step;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Waits;
import utilities.uiElements.Button;
import utilities.uiElements.TextContainer;
import utilities.uiElements.TextInputField;

public class LoginForm<T> {

    private final Class<T> currentPage;

    private final TextInputField usernameField = new TextInputField(By.id("user-name"));
    private final TextInputField passwordField = new TextInputField(By.id("password"));
    private final Button loginButton = new Button(By.id("login-button"));


    private final TextContainer errorMessage = new TextContainer(
            By.cssSelector("#login_button_container > div > form > div.error-message-container.error")
    );

    public LoginForm(Class<T> currentPage) {
        this.currentPage = currentPage;
    }

    @Step("Step 3 - Enter username: {username}")
    public void enterUsername(String username) {
        Waits.waitForElementVisibility(usernameField.getLocator(), 5);
        usernameField.write(username);
        LogsUtils.info("Entered username: " + username);
    }

    @Step("Step 4 - Enter password: {password}")
    public void enterPassword(String password) {
        Waits.waitForElementVisibility(passwordField.getLocator(), 5);
        passwordField.write(password);
        LogsUtils.info("Entered password: " + password);
    }

    @Step("Step 5 - Click on Login button")
    public T clickLogin() {
        Waits.waitForElementToBeClickable(loginButton.getLocator(), 5);
        loginButton.click();
        LogsUtils.info("Clicked on Login button.");

        try {
            return currentPage.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            LogsUtils.error("Couldn't create next page instance. Error: " + e.getMessage());
            return null;
        }
    }

    @Step("Step 3–5 - Perform login using username and password")
    public T login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    @Step("Verify that error message is displayed")
    public boolean isErrorDisplayed() {
        try {
            Waits.waitForElementVisibility(errorMessage.getLocator(), 5);
            boolean visible = errorMessage.isDisplayed();
            LogsUtils.info("Error message is displayed: " + visible);
            return visible;
        } catch (Exception e) {
            LogsUtils.error("Error message not found.");
            return false;
        }
    }

    @Step("Get error message text")
    public String getErrorMessageText() {
        return errorMessage.getText();
    }

}
