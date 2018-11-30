package google.pages.menu;

import framework.webdriver.elements.Label;
import org.openqa.selenium.By;

public class MessageOptionsMenu {

    private static String formatMenuLabelLocator = "//div[contains(@class,'bzn')]//div[@act='%s']//div[contains(@class,'asa')]";

    public void clickMenuItem(int actNumber) {
        new Label(By.xpath(String.format(formatMenuLabelLocator,actNumber)),"Menu Item").clickAndWait();
    }
}