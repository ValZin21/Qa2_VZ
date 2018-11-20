package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ApaviPage {
    BaseFunctions baseFunctions;

    public static final By APAVI_FILTER = By.xpath(".//div[@id='step3_38']/div[@class='step-item']");
//    public static final By APAVI_FILTER_TEXT_VALUES = By.xpath(".//*[@id='step3_38']/div");
    public static final By APAVI_FILTER_TAG_FINDER = By.xpath(".//*[@id='step3_38']/div[@class='step-item']/label");
//    public static final By APAVI_FILTER_VALUE_CHECKBOX = By.xpath(".//*[@id='step3_38']/div/*[@id='tag_205']");
//    public static final By APAVI_FILTER_VALUE_CHECKBOX = By.xpath(".//*[@id='tag_205']");
    public static final By KRASA_FILTER_TITLE = By.xpath(".//*[contains(@class, 'filters-section')]/div[contains(text(), 'Krāsa')]");
    public static final By KRASAS = By.xpath(".//*[contains(@class, 'filter-colors-item')]/label");

    private static Logger LOGGER = LogManager.getLogger(ApaviPage.class);

    private WebElement elementChecker;


    public ApaviPage(BaseFunctions baseFunctions){
        this.baseFunctions = baseFunctions;
    }

    public void selectKurpes() {

        baseFunctions.isElementPresent(APAVI_FILTER_TAG_FINDER);  // by plan - APAVI_FILTER
        baseFunctions.elementList.clear();
        baseFunctions.elementList = baseFunctions.getElements(APAVI_FILTER_TAG_FINDER);  // by plan - APAVI_FILTER
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals("Kurpes")) {
//                baseFunctions.isElementPresent(APAVI_FILTER_TAG_FINDER);
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
//                LOGGER.info(checkMe);
                Assertions.assertTrue(checkMe.findElements(APAVI_FILTER_TAG_FINDER).isEmpty(), "No tag");
//                String a = baseFunctions.getElement(APAVI_FILTER_TAG_FINDER).getAttribute("for");
//                String a = checkMe.findElement(APAVI_FILTER_TAG_FINDER).getAttribute("for");
                String checkBoxId = baseFunctions.getElementFromList(baseFunctions.elementList, i).getAttribute("for"); //working, APAVI_FILTER_TAG_FINDER for all locators
//                String a = baseFunctions.getElementFromList(baseFunctions.elementList, i).findElement(APAVI_FILTER_TAG_FINDER).getAttribute("for");
                LOGGER.info("Kurpes id: " + checkBoxId);
                baseFunctions.getElement(By.xpath(".//*[@id='" + checkBoxId + "']")).click();
//                baseFunctions.getElementFromList(baseFunctions.elementList, i)
                baseFunctions.driver.navigate().refresh();
                LOGGER.info("Kurpes Clicked!");
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked='checked']"));
                LOGGER.info("Kurpes filter validation pass");
                //ADD CHECK THAT CHECKBOX IS SELECTED (.//*[@id='tag_205'][@checked='checked'] exists)
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Kurpes> product missed!");
    }

    public void selectBlackColor() {
        baseFunctions.isElementPresent(KRASA_FILTER_TITLE);
        LOGGER.info("Krasas filter detected");
        baseFunctions.elementList.clear();
        baseFunctions.isElementPresent(KRASAS);
        LOGGER.info("Krasas detected");
        baseFunctions.elementList = baseFunctions.getElements(KRASAS);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getElementFromList(baseFunctions.elementList, i).getAttribute("data-title").equals("Melna")) {
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                String checkBoxId = checkMe.getAttribute("for");
                LOGGER.info("Krasas Id: " + checkBoxId);
                checkMe.click();
                baseFunctions.driver.navigate().refresh();
                LOGGER.info("Melna krasa Clicked!");
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked]"));
                LOGGER.info("Melna karasa filter validation pass");
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Melna krasa> filter missed!");
    }
}
