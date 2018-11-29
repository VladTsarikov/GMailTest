package google.pages;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.Label;
import google.enums.Attribute;
import google.pages.menu.MessageOptionsMenu;
import org.openqa.selenium.By;

public class MessagePage extends BaseForm {

    private final static String MAIN_LOCATOR = "//div[@class='amn']";
    private final Label lblMessageSubject =  new Label(By.xpath("//h2[@class='hP']"),"Message Subject Label");
    private final Label lblMessageRecipient =  new Label(By.xpath("//span[@class='gD']"),"Write Message Button");
    private final Label lblMessageBody =  new Label(By.xpath("//div[contains(@class,'gt')]//div[@dir='ltr']"),"User Account Info Button");
    public MessageOptionsMenu messageOptionsMenu = new MessageOptionsMenu();

    public MessagePage() {
        super(By.xpath(MAIN_LOCATOR),"Message Page");
    }

    public String getMessageRecipientEmail(){
        return lblMessageRecipient.getAttribute(Attribute.EMAIL.getAttributeName());
    }

    public String getMessageSubject(){
        return lblMessageSubject.getText();
    }

    public String getMessageBody(){
        return lblMessageBody.getText();
    }
}