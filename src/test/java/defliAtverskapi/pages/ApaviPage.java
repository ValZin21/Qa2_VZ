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

    public static final By APAVI_FILTER = By.xpath(".//*[@id='step3_38']/div");
//    public static final By APAVI_FILTER_TEXT_VALUES = By.xpath(".//*[@id='step3_38']/div");
//    public static final By APAVI_FILTER_VALUE_CHECKBOX = By.xpath(".//*[@id='step3_38']/div/input[@type='checkbox']");

    private static Logger LOGGER = LogManager.getLogger(ApaviPage.class);

    private WebElement elementChecker;


    public ApaviPage(BaseFunctions baseFunctions){
        this.baseFunctions = baseFunctions;
    }

    public void selectKurpes() {

        baseFunctions.isElementPresent(APAVI_FILTER);
        baseFunctions.elementList = baseFunctions.getElements(APAVI_FILTER);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals("Kurpes")) {

                baseFunctions.getElementFromList(baseFunctions.elementList, i).click();
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Kurpes> product missed!");

    }
}
