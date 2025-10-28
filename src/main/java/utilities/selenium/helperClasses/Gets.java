package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import utilities.common.LogsUtils;

import static utilities.selenium.helperClasses.SimpleElementActions.find;

public class Gets {

    @Step("Fetch inner text form element located by: {locator}")
    public static String getInnerText(By locator) {
        LogsUtils.info("Fetching inner text for element located by: " + locator);
        String text = find(locator).getText();
        LogsUtils.info("Inner text fetched: " + text);
        return text;
    }

    @Step("Fetch attribute '{attributeName}' form element located by: {locator}")
    public static String getAttribute( By locator, String attributeName) {
        LogsUtils.info("Fetching attribute '" + attributeName + "' for element located by: " + locator);
        String attributeValue = find(locator).getDomAttribute(attributeName);
        LogsUtils.info("Attribute value fetched: " + attributeValue);
        return attributeValue;
    }
}
