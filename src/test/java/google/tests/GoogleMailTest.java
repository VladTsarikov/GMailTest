package google.tests;

import framework.utils.Logger;
import framework.utils.PropertyReader;
import framework.webdriver.BaseEntity;
import google.enums.MessageOptionsActNumber;
import google.pages.MainMailPage;
import google.pages.MessagePage;
import google.pages.PasswordSignInPage;
import google.pages.LoginSignInPage;
import google.pages.forms.NewMessageForm;
import google.pages.forms.SentMessageStatusForm;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleMailTest extends BaseEntity {

    private static PropertyReader credentialsProperty = new PropertyReader("credentials.properties");
    private String messageSubject = "Test message";
    private String messageBody = "Hello!";
    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void tutByTest(){
        Logger.logStep(1,"OPENING GMAIL.COM AND ENTERING LOGIN...");
        LoginSignInPage loginSignInPage = new LoginSignInPage();
        loginSignInPage.setLogin(credentialsProperty.getProperty("username"));

        Logger.logStep(2,"ENTERING PASSWORD...");
        PasswordSignInPage passwordSignInPage = new PasswordSignInPage();
        passwordSignInPage.setPassword(credentialsProperty.getProperty("password"));

        Logger.logStep(3,"OPENING MAIL PAGE AND VERIFY IS SUCH PAGE OPENED...");
        MainMailPage mainMailPage = new MainMailPage();
        Assert.assertEquals(credentialsProperty.getProperty("username"),mainMailPage.getCurrentEmailAddress(),"Mail of necessary user has not opened");

        Logger.logStep(3,"WRITING NEW MESSAGE...");
        mainMailPage.clickWriteMessageButton();
        NewMessageForm newMessageForm = new NewMessageForm();
        newMessageForm.writeNewMessage(credentialsProperty.getProperty("username"),messageSubject,messageBody);

        Logger.logStep(3,"VERIFYING THAT MESSAGE WAS DELIVERED SUCCESSFULLY...");
        SentMessageStatusForm sentMessageStatusForm = new SentMessageStatusForm();
        sentMessageStatusForm.openSendingMessage();
        MessagePage messagePage = new MessagePage();

      //  System.out.println("!!!!" + messagePage.getMessageRecipientEmail());
       // System.out.println("!!!!" + messagePage.getMessageSubject());
      //  System.out.println("!!!!" + messagePage.getMessageBody());
        Assert.assertEquals(credentialsProperty.getProperty("username"),messagePage.getMessageRecipientEmail(),"");
        Assert.assertEquals("Test message",messagePage.getMessageSubject());
        Assert.assertEquals("Hello!",messagePage.getMessageBody());
       // softAssert.assertAll();

        Logger.logStep(4,"DELETING SENDING MESSAGE...");
        messagePage.messageOptionsMenu.clickMenuItem(MessageOptionsActNumber.DELETE.getAttributeName());

        Logger.logStep(3,"VERIFYING THAT MESSAGE WAS DELETED SUCCESSFULLY...");
        Assert.assertFalse(mainMailPage.isMessageExist(credentialsProperty.getProperty("username"),messageSubject,messageBody),"Message has not deleted");
    }
}