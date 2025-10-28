package utilities.selenium.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utilities.common.PropertiesUtils;

import java.util.HashMap;
import java.util.Map;

public class ChromeFactory implements DriverFactory<ChromeOptions> {
    private ChromeOptions options;

    @Override
    public ChromeOptions initializeOptions() {
        options = new ChromeOptions();
        options.setAcceptInsecureCerts(true);
        options.addArguments("--window-size=1920,1080");
        options.addArguments(
                "--disable-notifications",
                "--disable-infobars",
                "--start-maximized",
                "--disable-blink-features=AutomationControlled"
        );

        // 🔒 Disable Google Password Manager popups (e.g., data breach alerts)
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);

        // Run in headless mode if configured
        if ("true".equalsIgnoreCase(PropertiesUtils.getProperty("headless"))) {
            options.addArguments("--headless=new"); // Use new headless mode for Chrome 109+
        }

        return options;
    }

    @Override
    public WebDriver createInstance() {
        options = initializeOptions();
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();

        // Open the base URL from properties file
        String baseUrl = PropertiesUtils.getProperty("baseUrl");
        if (baseUrl != null && !baseUrl.isEmpty()) {
            driver.get(baseUrl);
        }

        return driver;
    }
}
