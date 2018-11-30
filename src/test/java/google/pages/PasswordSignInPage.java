package google.pages;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.Button;
import framework.webdriver.elements.TextBox;
import org.openqa.selenium.By;

public class PasswordSignInPage extends BaseForm {

    private final static String MAIN_LOCATOR = "view_container";
    private final TextBox txbPassword =  new TextBox(By.xpath("//input[@name='password']"),"Password TextBox");
    private final Button btnNext =  new Button(By.id("passwordNext"),"Next Button");

    public PasswordSignInPage() {
        super(By.id(MAIN_LOCATOR),"Password Sign In Page");
    }

    public void setPassword(String password){
        txbPassword.setText(password);
        btnNext.clickViaJS();
    }
}