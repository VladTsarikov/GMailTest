package framework.webdriver.elements;

import framework.enums.LogType;
import framework.utils.Logger;
import framework.webdriver.BaseEntity;
import framework.webdriver.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class BaseElement extends BaseEntity {

    protected String name;
    protected By locator;
    protected RemoteWebElement element;

    protected BaseElement(final By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public WebElement getElement() {
        return driver.findElement(locator);
    }

    public void click(){
        assertTrue(isPresent());
        Logger.log(String.format("Clicking on %s", name));
        getElement().click();
    }

    public void clickViaAction() {
        assertTrue(isPresent());
        Actions action = new Actions(driver);
        action.click(getElement());
        action.perform();
    }

    public void clickViaJS() {

        assertIsPresent();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getElement());
    }

    public void moveMouseToElement() {

        Actions action = new Actions(driver);
        action.moveToElement(getElement());
        action.perform();
    }

    public String getText(){
       // waitForIsPresent(30);
        isPrt();
        isPrt1();
        return getElement().getText();
    }

    public int getCurrentElementCount(){
        assertIsPresent();
        return driver.findElements(locator).size();
    }

    public String getAttribute(String attributeName){
       // assertIsPresent();
        return getElement().getAttribute(attributeName);
    }

    public boolean isPresent() {
        boolean status = false;
        try{
            if(getElement().isEnabled()){
                status = true;
            }
        }catch (Exception e){
            status = false;
            Logger.log(LogType.DEBUG,e);
        }
        return status;
    }

    public boolean isPrt() {
        return Waiting.waitForJStoLoad();
      // return Waiting.fluentWait(30);
    }

    public boolean isPrt1() {
        return Waiting.fluentWait(30);
        // return Waiting.fluentWait(30);
    }

    private void assertIsPresent(){
        assertTrue(isPresent(),String.format("Element '%s' has not found", name));
    }

    public void waitForExists() {
        ExpectedCondition condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                Boolean isExist = !driver.findElements(locator).isEmpty();
                if (isExist) {
                    element = (RemoteWebElement) driver.findElement(locator);
                }
                return isExist;
            }
        };
        Waiting.waitFor(condition);
    }

    public void waitq() {
        Waiting.fluentWait(30);
    }

    private boolean waitForIsPresent(long timeout) {
        ExpectedCondition<Boolean> condition = driver -> {
            try {
                List<WebElement> elems = driver.findElements(locator);
                for (WebElement elem : elems) {
                    if (elem.isDisplayed()) {
                        element = (RemoteWebElement) elem;
                        return true;
                    }
                }
                return false;
            } catch (Exception | AssertionError e) {
                //logger.debug(this, e);
                return false;
            }
        };
        return Waiting.waitFor(condition, timeout);
    }
}