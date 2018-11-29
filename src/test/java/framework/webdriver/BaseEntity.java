package framework.webdriver;

import framework.utils.Logger;
import framework.utils.PropertyReader;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;

public class BaseEntity {

    private static PropertyReader propertyReader = new PropertyReader("config.properties");
    private static String url = propertyReader.getProperty("url");
    private static int wait = Integer.parseInt(propertyReader.getProperty("wait"));
    protected static WebDriver driver;

    @BeforeClass
    public static void before(){
        Logger.log("Starting tests...");
        driver = BrowserFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(wait, TimeUnit.SECONDS);
        driver.get(url);
    }

    @AfterClass
    public static void after(){
        Logger.log("Ending tests...");
        driver.quit();
    }
}