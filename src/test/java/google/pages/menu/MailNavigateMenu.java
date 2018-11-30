package google.pages.menu;

import framework.webdriver.elements.Label;
import org.openqa.selenium.By;

public class MailNavigateMenu {

    private static String formatMenuLabelLocator = "//div[@role='navigation']//a[contains(@href,'%s')]";

    public void clickMenuItem(String hrefPart) {
        new Label(By.xpath(String.format(formatMenuLabelLocator,hrefPart)),String.format("Menu Item '%s'",hrefPart))
                .click();
    }
}