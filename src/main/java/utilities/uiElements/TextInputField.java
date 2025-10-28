package utilities.uiElements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import utilities.common.LogsUtils;
import utilities.selenium.helperClasses.Gets;
import utilities.selenium.helperClasses.JsExecutor;
import utilities.selenium.helperClasses.SimpleElementActions;
import utilities.selenium.helperClasses.Waits;

import static utilities.selenium.helperClasses.SimpleElementActions.find;

public class TextInputField extends UiElement {

    // Constructor using a single locator
    public TextInputField(By locator) {
        super(locator);
    }

    // Constructor using parent and relative locator
    public TextInputField(By parentLocator, By relativeLocator) {
        super(parentLocator, relativeLocator);
    }

    // Get the text from the input field
    public String getText() {
        if (isDisplayed()) {
            return Gets.getAttribute(locator, "value");
        } else {
            LogsUtils.error("Couldn't get text for " + locator.toString());
            return "";
        }
    }

    // Write text into the input field
    public void write(String text) {
        try {
            if (text == null || text.isEmpty() || text.equalsIgnoreCase("null")) {
                text = "";
            }

            Waits.waitForElementToBeClickable(locator, 3);

            // Ensure the element has focus before typing
            find(locator).click();
            LogsUtils.info("Clicked on input field before writing text.");

            SimpleElementActions.set(locator, text);
            LogsUtils.info("Text written successfully: " + text);

        } catch (WebDriverException ex) {
            try {
                JsExecutor.writeUsingJs(text, locator);
                LogsUtils.warn("Selenium SendKeys failed, JsExecutor used.");
            } catch (Exception e) {
                LogsUtils.error(e.getMessage());
                throw new RuntimeException(e);
            }
        }
    }
}
