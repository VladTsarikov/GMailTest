package google.tests;

import framework.utils.Logger;
import framework.utils.PropertyReader;
import framework.webdriver.BaseEntity;
import google.Entities.Message;
import google.enums.*;
import google.pages.*;
import google.pages.forms.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class GoogleMailTest extends BaseEntity {

    private static PropertyReader credentialsProperty = new PropertyReader("credentials.properties");
    private Message message = new Message(credentialsProperty.getProperty("username"),"Test message", "Hello!");
    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void googleTest(){
        Logger.logStep(1,"OPENING GMAIL.COM AND ENTERING LOGIN...");
        LoginSignInPage loginSignInPage = new LoginSignInPage();
        loginSignInPage.setLogin(credentialsProperty.getProperty("username"));

        Logger.logStep(2,"ENTERING PASSWORD...");
        PasswordSignInPage passwordSignInPage = new PasswordSignInPage();
        passwordSignInPage.setPassword(credentialsProperty.getProperty("password"));

        Logger.logStep(3,"OPENING MAIL PAGE AND VERIFY IF SUCH PAGE OPENED...");
        MainMailPage mainMailPage = new MainMailPage();
        Assert.assertEquals(credentialsProperty.getProperty("username"),mainMailPage.getCurrentEmailAddress()
                ,"EMail of necessary user has not opened");

        Logger.logStep(4,"WRITING NEW MESSAGE...");
        mainMailPage.clickWriteMessageButton();
        NewMessageForm newMessageForm = new NewMessageForm();
        newMessageForm.writeNewMessage(message.getRecipientEmail(),message.getSubject(),message.getBody());

        Logger.logStep(5,"VERIFYING THAT MESSAGE WAS DELIVERED SUCCESSFULLY...");
        SentMessageStatusForm sentMessageStatusForm = new SentMessageStatusForm();
        sentMessageStatusForm.openSendingMessage();
        MessagePage messagePage = new MessagePage();
        softAssert.assertEquals(message.getRecipientEmail(),messagePage.getMessageRecipientEmail()
                ,"Email is not correct");
        softAssert.assertEquals(message.getSubject(),messagePage.getMessageSubject(),"Subject is not correct");
        softAssert.assertEquals(message.getBody(),messagePage.getMessageBody(),"Body is not correct");

        Logger.logStep(6,"DELETING SENDING MESSAGE...");
        messagePage.mailNavigateMenu.clickMenuItem(NavigateMenuHrefPart.INBOX.getHrefPart());
        mainMailPage.selectMessage(message.getRecipientEmail(),message.getSubject(),message.getBody());
        mainMailPage.messageOptionsMenu.clickMenuItem(MessageOptionsActNumber.DELETE.getAttributeName());

        Logger.logStep(7,"VERIFYING THAT MESSAGE WAS DELETED SUCCESSFULLY...");
        Assert.assertFalse(mainMailPage.isMessageExist(message.getRecipientEmail(),message.getSubject()
                ,message.getBody()),"Message has not deleted");
        softAssert.assertAll();
    }
}