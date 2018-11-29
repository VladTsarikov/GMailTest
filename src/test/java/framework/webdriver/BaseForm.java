package framework.webdriver;

import framework.webdriver.elements.Label;
import org.openqa.selenium.By;

import static org.testng.Assert.assertTrue;

public class BaseForm extends BaseEntity{

    protected By pageLocator;
    protected String name;

    protected BaseForm(By locator, String formTitle) {
        this.pageLocator = locator;
        name = formTitle;
        isOpened();
    }

    private void isOpened(){
        assertTrue(new Label(pageLocator,"Page Locator").isPresent(),String.format("%s has not opened",name));
    }
}