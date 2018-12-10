package framework.webdriver.waitings;

import framework.enums.LogType;
import framework.utils.Logger;
import framework.webdriver.BrowserFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;
import java.util.concurrent.TimeUnit;
import static java.util.concurrent.TimeUnit.SECONDS;

public class Waiting {

    public static void waitFor(ExpectedCondition condition) {
        waitFor(condition, Long.parseLong(BrowserFactory.getDefaultConditionTimeout()));
    }

    private static void waitFor(ExpectedCondition condition, long timeOutInSeconds) {
        int duration = 300;
        Wait<WebDriver> wait = new FluentWait<>((WebDriver) BrowserFactory.getDriver())
                .withTimeout(timeOutInSeconds, SECONDS)
                .pollingEvery(duration, TimeUnit.MILLISECONDS);
        try {
            wait.until(condition);
        } catch (Exception e) {
            Logger.log(LogType.DEBUG, e);
        }
    }

    public static void waitForPageIsReady(){
        WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), Long.parseLong(BrowserFactory
                .getDefaultConditionTimeout()));
        wait.until(Condition.documentNotActiveCondition());
        wait.until(Condition.JQueryLoadCondition());
        wait.until(Condition.JStoLoadCondition());
    }
}