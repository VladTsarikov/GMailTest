package google.pages;

import framework.utils.RegExpFinder;
import framework.webdriver.BaseForm;
import framework.webdriver.elements.Button;
import framework.webdriver.elements.CheckBox;
import framework.webdriver.elements.Label;
import google.enums.Attribute;
import google.enums.RegExp;
import org.openqa.selenium.By;

public class MainMailPage extends BaseForm {

    private final static String MAIN_LOCATOR = "//a[contains(@href,'inbox')]//img";
    private final Button btnWriteMessage =  new Button(By.xpath("//div[contains(@class,'aic')]//div[@role='button']"),"Write Message Button");
    private final Button btnUserAccountInfo =  new Button(By.xpath("//a[contains(@href,'SignOut')]"),"User Account Info Button");
    private final Label lblMessagesList =  new Label(By.xpath("//div[@class='UI']//tr"),"Message Label");
    private final Button btnDeleteMessage =  new Button(By.xpath("//div[@act='10']//div"),"Delete Selected Messages Button");
    private final Button btnLook =  new Button(By.xpath("//span[@id='link_vsm']"),"Delete Selected Messages Button");
    private final static String FormatMessageSelectLocator = "(//div[@class='UI']//div[@role='checkbox'])[%s]";
    private final static String FormatMessageEmailLocator = "(//span[@class='bA4']//span)[%s]";
    private final static String FormatMessageSubjectLocator = "(//span[@class='bog']//span)[%s]";
    private final static String FormatMessageBodyLocator = "(//span[@class='y2'])[%s]";


    public MainMailPage() {
        super(By.xpath(MAIN_LOCATOR),"Main User Mail Page");
    }

    public void clickWriteMessageButton(){
        btnWriteMessage.click();
    }

    public String getCurrentEmailAddress(){
        return RegExpFinder.findByRegularExp(btnUserAccountInfo.getAttribute(Attribute.ARIA_LABEL.getAttributeName())
                , RegExp.CURRENT_MAIL.getRegExp());
    }

    public boolean isMessageExist(String recipientEmail, String subject, String body) {
        boolean bool = false;
        if (lblMessagesList.isPresent()) {
            for (int i = 1; i <= lblMessagesList.getCurrentElementCount(); i++) {
                if (getMessageRecipientEmail(i).equals(recipientEmail)
                && getMessageSubject(i).equals(subject)
                && getMessageBody(i).equals(body)) {
                    bool = true;
                }
            }
        }
        return bool;
    }

    public void deleteMessage(String recipientEmail, String subject, String body){
        if (lblMessagesList.isPresent()) {
            for (int i = 1; i <= lblMessagesList.getCurrentElementCount(); i++) {
                if (getMessageRecipientEmail(i).equals(recipientEmail)
                        && getMessageSubject(i).equals(subject)
                        && getMessageBody(i).equals(body)) {
                    selectMessage(i);
                    btnDeleteMessage.click();
                }
            }
        }
    }

    private String getMessageRecipientEmail(int messageIndex){
        return new Label(By.xpath(String.format(FormatMessageEmailLocator, messageIndex))
                , "Message Recipient Email Label").getAttribute(Attribute.EMAIL.getAttributeName());
    }

    private void selectMessage(int messageIndex){
        new CheckBox(By.xpath(String.format(FormatMessageSelectLocator, messageIndex)),"Message Select CheckBox")
                .click();
    }

    private String getMessageSubject(int messageIndex){
        return new Label(By.xpath(String.format(FormatMessageSubjectLocator, messageIndex))
                , "Message Subject Locator").getText();
    }

    private String getMessageBody(int messageIndex){
        String MessageBodyReplacement = "";
        String MessageBodyReplacementTarget = "-";
        return new Label(By.xpath(String.format(FormatMessageBodyLocator, messageIndex)), "Message Body Locator")
                .getText().replace(MessageBodyReplacementTarget,MessageBodyReplacement)
                .replaceAll(RegExp.MESSAGE_BODY.getRegExp(), MessageBodyReplacement);
    }
}