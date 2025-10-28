package utilities.selenium.helperClasses;

import io.qameta.allure.Step;
import org.apache.logging.log4j.ThreadContext;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebDriver;
import utilities.common.DateTime;
import utilities.common.LogsUtils;
import utilities.selenium.driver.WebDriverManager;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static utilities.common.AllureUtils.attachPng;

public class ScreenShotUtils {

    public static final String SCREEN_SHOTS_PATH = "test_outputs/screenshots/";

    private ScreenShotUtils() {}

    @Step("Taking screenshot: {fileName}")
    public static File takeScreenShot(String fileName) {
        File targetFile = null;
        LogsUtils.info("Taking screenshot for: " + fileName);

        try {
            WebDriver driver = WebDriverManager.getDriver();
            if (driver == null) {
                LogsUtils.error("Driver instance is null. Cannot take screenshot.");
                return null;
            }

            // Take screenshot
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Create directories if not exist
            Path dirPath = Path.of(SCREEN_SHOTS_PATH);
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            // Set file name format
            String browser = ThreadContext.get("browser") != null ? ThreadContext.get("browser") + "_" : "";
            String timestamp = DateTime.getDateTime().replace(":", "-").replace(" ", "_");
            targetFile = new File(SCREEN_SHOTS_PATH + browser + fileName + "_" + timestamp + ".png");

            // Copy screenshot to target path
            Files.copy(screenshot.toPath(), targetFile.toPath());

            LogsUtils.info("Screenshot saved: " + targetFile.getAbsolutePath());

            // Attach to Allure report
            attachPng(targetFile);

            return targetFile;

        } catch (UnhandledAlertException e) {
            LogsUtils.warn("Skipping screenshot due to open alert: " + e.getAlertText());
        } catch (Exception e) {
            LogsUtils.error("Failed to take screenshot: " + e.getMessage());
        }

        return null;
    }
}
