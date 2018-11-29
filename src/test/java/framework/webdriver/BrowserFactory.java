package framework.webdriver;

import framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.*;
import java.nio.file.FileSystems;
import java.util.Hashtable;
import java.util.Map;

public class BrowserFactory {

    private static WebDriver driver;
    private static PropertyReader driversProperty = new PropertyReader("drivers.properties");
    private final static String OPERATING_SYSTEM = driversProperty.getProperty("operating_system");
    private final static String CHROME_PROPERTY = driversProperty.getProperty("chrome_property");
    private final static String FIREFOX_PROPERTY = driversProperty.getProperty("firefox_property");
    private final static String DRIVER_PATH = driversProperty.getProperty("driver_path");
    private final static String CHROME_DRIVER_NAME = driversProperty.getProperty("chrome_driver_name");
    private final static String FIREFOX_DRIVER_NAME = driversProperty.getProperty("firefox_driver_name");
    private final static String WINDOWS_DRIVER_EXT = driversProperty.getProperty("windows_driver_extension");
    private final static String LINUX_DRIVER_EXT = driversProperty.getProperty("linux_driver_extension");

    private BrowserFactory() { }

    /**
     * The driver is selected, based on operating system type.
     *
     */
    public static WebDriver getDriver() {
        if (driver == null) {
            switch (OPERATING_SYSTEM) {
                case "windows":
                    switchBrowser(WINDOWS_DRIVER_EXT);
                    break;
                case "linux":
                    switchBrowser(LINUX_DRIVER_EXT);
                    break;
            }
        }
        return driver;
    }

    /**
     * The driver is selected, based on browser type.
     *
     */
    private static WebDriver switchBrowser(String extension){
        switch (driversProperty.getProperty("browser")) {
            case "chrome":
                setSystemProperty(CHROME_DRIVER_NAME,extension,CHROME_PROPERTY);
                driver = new ChromeDriver(getChromeCapabilities());
                break;
            case "firefox":
                setSystemProperty(FIREFOX_DRIVER_NAME,extension,FIREFOX_PROPERTY);
                driver = new FirefoxDriver(getFirefoxCapabilities());
                break;
        }
        return driver;
    }

    private static void setSystemProperty(String driverName, String driverExtension, String property){
        String formatDriverPath = String.format(DRIVER_PATH, OPERATING_SYSTEM, driverName, driverExtension);
        String absoluteDriverPath = FileSystems.getDefault().getPath(formatDriverPath).normalize().toAbsolutePath().toString();
        System.setProperty(property, absoluteDriverPath);
    }

    private static FirefoxOptions getFirefoxCapabilities() {
        int folderList = 2;
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.folderList", folderList);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-gzip, " +
                "application/octet-stream, application/zip");
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;
    }

    private static ChromeOptions getChromeCapabilities() {
        int popupsCountSettings = 0;
        Map<String, Object> preferences = new Hashtable<>();
        preferences.put("profile.default_content_settings.popups", popupsCountSettings);
        preferences.put("safebrowsing.enabled", "true");
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", preferences);
        return options;
    }
}