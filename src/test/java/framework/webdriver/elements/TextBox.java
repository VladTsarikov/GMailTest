package framework.webdriver.elements;

import org.openqa.selenium.By;

public class TextBox extends BaseElement{

    public TextBox(final By xpath,final String name){
        super(xpath,name);
    }

    public void setText(String text){
        getElement().sendKeys(text);
    }
}