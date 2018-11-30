package framework.webdriver.elements;

import framework.enums.LogType;
import framework.utils.Logger;
import framework.webdriver.BaseEntity;
import framework.webdriver.BrowserFactory;
import framework.webdriver.waitings.Waiting;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.testng.Assert.assertTrue;

public class BaseElement extends BaseEntity {

    protected String name;
    protected By locator;

    protected BaseElement(final By locator, String name) {
        this.locator = locator;
        this.name = name;
    }

    public WebElement getElement() {
        return driver.findElement(locator);
    }

    public void click(){
        Waiting.waitFor(ExpectedConditions.elementToBeClickable(locator));
        assertTrue(isPresent());
        Logger.log(String.format("Clicking on %s", name));
        getElement().click();
    }

    public void clickAndWait(){
        click();
        Waiting.waitForPageIsReady();
    }

    public void clickViaJS() {
        Waiting.waitForPageIsReady();
        Waiting.waitFor(ExpectedConditions.elementToBeClickable(locator));
        assertIsPresent();
        Logger.log(String.format("Clicking on %s", name));
        ((JavascriptExecutor) BrowserFactory.getDriver()).executeScript("arguments[0].click();", getElement());
    }

    public String getText(){
        Waiting.waitForPageIsReady();
        Logger.log(String.format("Getting text of %s", name));
        return getElement().getText();
    }

    public int getSuchElementsCount(){
        assertIsPresent();
        return driver.findElements(locator).size();
    }

    public String getAttribute(String attributeName){
        assertIsPresent();
        Logger.log(String.format("Getting attribute '%s' of %s", attributeName, name));
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

    private void assertIsPresent(){
        assertTrue(isPresent(),String.format("Element '%s' has not found", name));
    }
}