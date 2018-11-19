package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    BaseFunctions baseFunctions;

    public static final By DROP_DOWN_MENU_SUMMARY = By.xpath(".//li[@class='dropdown']/a");
    public static final By DROP_DOWN_MENU_OPEN = By.xpath(".//li[@class='dropdown open']");
    public static final By DROP_DOWN_MUENU_ELEMENTS = By.xpath(".//ul[@class='dropdown-menu']/li/a");

    public List<WebElement> elementList;

    private static Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage (BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public void findAndOpenVirieshiDropDownMenu() {
        elementList = baseFunctions.getElements(DROP_DOWN_MENU_SUMMARY);
        boolean statementDetected = false;
        for (int i = 0; i < elementList.size(); i++) {
            if (baseFunctions.getTextFromList(elementList, i).equals("Vīriešiem")) {
                baseFunctions.getElementFromList(elementList, i).click();
                baseFunctions.isElementPresent(DROP_DOWN_MENU_OPEN);
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Vīriešiem> menu missed!");
    }

    public void findAndClickOnApaviProduct() {
        elementList = baseFunctions.getElements(DROP_DOWN_MUENU_ELEMENTS);
        boolean statementDetected = false;
        for (int i = 0; i < elementList.size(); i++) {
            if (baseFunctions.getTextFromList(elementList, i).equals("Apavi")) {
                goToApaviPage(elementList, i);
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Apavi> product missed!");
    }

    private ApaviPage goToApaviPage (List<WebElement> list, int number) {
        baseFunctions.getElementFromList(list, number).click();
        return new ApaviPage(baseFunctions);
    }
}
