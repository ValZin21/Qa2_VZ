package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    BaseFunctions baseFunctions;

    public static final By DROP_DOWN_MENU_SUMMARY = By.xpath(".//li[@class='dropdown']/a");
    public static final By DROP_DOWN_MENU_OPEN = By.xpath(".//li[@class='dropdown open']");
    public static final By DROP_DOWN_MUENU_ELEMENTS = By.xpath(".//ul[@class='dropdown-menu']/li/a");

    private static Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage (BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public void findAndOpenVirieshiDropDownMenu() {
        baseFunctions.elementList = baseFunctions.getElements(DROP_DOWN_MENU_SUMMARY);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals("Vīriešiem")) {
                baseFunctions.getElementFromList(baseFunctions.elementList, i).click();
                baseFunctions.isElementPresent(DROP_DOWN_MENU_OPEN);
                statementDetected = true;
            }
        }
        Assertions.assertTrue(statementDetected, "<Vīriešiem> menu missed!");
    }

    public WebElement findApaviProduct() {
        baseFunctions.elementList = baseFunctions.getElements(DROP_DOWN_MUENU_ELEMENTS);
        WebElement result = null;
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals("Apavi")) {
                result = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Apavi> product missed!");
        return result;
    }

    public ApaviPage goToApaviPage (WebElement element) {
        element.click();
        return new ApaviPage(baseFunctions);
    }
}
