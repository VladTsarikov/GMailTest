package google.pages;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.Label;
import google.enums.Attribute;
import google.pages.menu.MailNavigateMenu;
import org.openqa.selenium.By;

public class MessagePage extends BaseForm {

    private final static String MAIN_LOCATOR = "//div[contains(@class,'amn')]";
    private final Label lblMessageSubject =  new Label(By.xpath("//h2[contains(@class,'hP')]"),"Message Subject Label");
    private final Label lblMessageRecipient =  new Label(By.xpath("//span[contains(@class,'gD')]"),"Message Recipient Email Label");
    private final Label lblMessageBody =  new Label(By.xpath("//div[contains(@class,'gt')]//div[@dir='ltr']"),"Message Body Label");
    public MailNavigateMenu mailNavigateMenu = new MailNavigateMenu();

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