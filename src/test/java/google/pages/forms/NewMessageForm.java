package google.pages.forms;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.*;
import org.openqa.selenium.By;

public class NewMessageForm extends BaseForm {

    private final static String MAIN_LOCATOR = "//div[@role='dialog']";
    private final TextBox txbTo =  new TextBox(By.xpath("//textarea[@name='to']"),"To TextBox");
    private final TextBox txbSubject =  new TextBox(By.xpath("//input[@name='subjectbox']"),"Subject TextBox");
    private final TextBox txbMessageBody =  new TextBox(By.xpath("//table[@class='iN']//div[@role='textbox']"),"Message Body TextBox");
    private final Button btnSend =  new Button(By.xpath("//div[contains(@data-tooltip,'Enter')]"),"Send Message Button");

    public NewMessageForm() {
        super(By.xpath(MAIN_LOCATOR),"Sign In Form");
    }

    public void writeNewMessage(String recipient,String subject,String message){
        setRecipient(recipient);
        setSubject(subject);
        setMessageBody(message);
        clickSendMessageButton();
    }

    private void setRecipient(String recipient){
        txbTo.setText(recipient);
    }

    private void setSubject(String subject){
        txbSubject.setText(subject);
    }

    private void setMessageBody(String message){
        txbMessageBody.setText(message);
    }

    private void clickSendMessageButton(){
        btnSend.click();
    }
}