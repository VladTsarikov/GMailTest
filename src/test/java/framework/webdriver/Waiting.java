package framework.webdriver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.*;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

public class Waiting {

    public static <T> T waitFor(ExpectedCondition<T> condition) {
        return waitFor(condition, Long.parseLong("15"));
    }

    /**
     * Wait for some object from condition with timeout. Wait until it's not false or null.
     * @param condition Condition for waiting {@link ExpectedCondition}
     * @param timeOutInSeconds Timeout in seconds
     * @param <T> Object for waiting
     * @return Object from condition
     */
    public static <T> T waitFor(ExpectedCondition<T> condition, long timeOutInSeconds) {
        BrowserFactory.getDriver().manage().timeouts().implicitlyWait(0L, TimeUnit.MILLISECONDS);
        Wait<WebDriver> wait = new FluentWait<>((WebDriver) BrowserFactory.getDriver())
                .withTimeout(timeOutInSeconds, SECONDS)
                .pollingEvery(300, TimeUnit.MILLISECONDS);

        try {
            return wait.until(condition);
        } catch (Exception | AssertionError e) {
            //logger.debug("SmartWait.waitFor", e);
        } finally {
            BrowserFactory.getDriver().manage().timeouts().implicitlyWait(Long.parseLong("10"), SECONDS);
        }
        return null;
    }

    public static Boolean fluentWait(long timeOutInSeconds){

        ExpectedCondition<Boolean> javascriptDone = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                try{//window.setTimeout executes only when browser is idle,
                    //introduces needed wait time when javascript is running in browser
                    return  ((Boolean) ((JavascriptExecutor) d).executeAsyncScript(

                            " var callback =arguments[arguments.length - 1]; " +
                                    " var count=42; " +
                                    " setTimeout( collect, 0);" +
                                    " function collect() { " +
                                    " if(count-->0) { "+
                                    " setTimeout( collect, 0); " +
                                    " } "+
                                    " else {callback(" +
                                    "    true" +
                                    " );}"+
                                    " } "
                    ));
                }catch (Exception e) {
                    return Boolean.FALSE;
                }
            }
        };
        WebDriverWait w = new WebDriverWait( BrowserFactory.getDriver(), timeOutInSeconds);
        return w.until(javascriptDone);
       // w=null;




      //  WebDriverWait dynamicElement = (new WebDriverWait( BrowserFactory.getDriver(), timeOutInSeconds));
       // return dynamicElement.until(ExpectedConditions.presenceOfElementLocated(element));

       // dynamicElement.until(
          //      webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));
    }

    public static boolean waitForJStoLoad() {

        WebDriverWait wait = new WebDriverWait(BrowserFactory.getDriver(), 30);

        // wait for jQuery to load
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor)driver).executeScript("return jQuery.active") == 0);
                }
                catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor)driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return wait.until(jQueryLoad) && wait.until(jsLoad);
    }
}
