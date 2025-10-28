package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import utilities.common.LogsUtils;

public class WebDriverManager {
    private WebDriverManager() {}

    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(String browser) {
        switch (browser.toLowerCase()) {
            case "chrome" -> driver.set(new ChromeFactory().createInstance());
            case "firefox" -> driver.set(new FirefoxFactory().createInstance());
            case "edge" -> driver.set(new EdgeFactory().createInstance());
            default -> throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        LogsUtils.info("Closing the Driver");
        WebDriver drv = driver.get();
        if (drv != null) {
            drv.quit();
            driver.remove();
        }
    }
}
