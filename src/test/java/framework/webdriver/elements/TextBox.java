package framework.webdriver.elements;

import framework.webdriver.waitings.Waiting;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class TextBox extends BaseElement{

    public TextBox(final By xpath,final String name){
        super(xpath,name);
    }

    public void setText(String text){
        Waiting.waitFor(ExpectedConditions.presenceOfElementLocated(locator));
        Waiting.waitFor(ExpectedConditions.visibilityOfElementLocated(locator));
        getElement().sendKeys(text);
    }
}