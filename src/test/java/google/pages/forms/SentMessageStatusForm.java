package google.pages.forms;

import framework.webdriver.BaseForm;
import framework.webdriver.elements.Button;
import org.openqa.selenium.By;

public class SentMessageStatusForm extends BaseForm {

    private final static String MAIN_LOCATOR = "//span[contains(@class,'bAq')]";
    private final Button btnOpenMessage =  new Button(By.id("link_vsm"),"Delete Selected Messages Button");

    public SentMessageStatusForm() {
        super(By.xpath(MAIN_LOCATOR),"Sent Message Status Form");
    }

    public void openSendingMessage(){
        btnOpenMessage.click();
    }
}