package google.pages;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.Button;
import framework.webdriver.elements.TextBox;
import org.openqa.selenium.By;

public class LoginSignInPage extends BaseForm {

    private final static String MAIN_LOCATOR = "view_container";
    private final TextBox txbLogin =  new TextBox(By.id("identifierId"),"Login TextBox");
    private final Button btnNext =  new Button(By.id("identifierNext"),"Next Button");

    public LoginSignInPage() {
        super(By.id(MAIN_LOCATOR),"Sign In Page");
    }

    public void setLogin(String login){
        txbLogin.setText(login);
        btnNext.click();
    }
}