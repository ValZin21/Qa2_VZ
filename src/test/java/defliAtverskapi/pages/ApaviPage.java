package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ApaviPage {
    BaseFunctions baseFunctions;

    private static final By APAVI_FILTER_TITLE = By.xpath(".//div[@id='step2_28'][@checked='checked']/div/input[@id='cat_38'][contains(@checked, 'checked')]/../ label[text()='Apavi']");
    private static final By APAVI_FILTER_TAG_FINDER = By.xpath(".//*[@id='step3_38']/div[@class='step-item']/label");
    private static final By KRASA_FILTER_TITLE = By.xpath(".//*[contains(@class, 'filters-section')]/div[contains(text(), 'Krāsa')]");
    private static final By KRASAS = By.xpath(".//*[contains(@class, 'filter-colors-item')]/label");
    private static final By STAVOKLIS_FILTER_TITLE = By.xpath(".//*[contains(@class, 'filters-section')]/div[contains(text(), 'Stāvoklis')]");
    private static final By STAVOKLI = By.xpath(".//*[contains(@class, 'filter-condition')]/div/label");
    private static final By PRODUCT = By.xpath(".//div[@class = 'col-xs-6 col-sm-3']");
    private static final By PRODUCT_CLICK = By.xpath(".//div/a[@itemprop='url']");
    private static final By PRODUCT_NAME = By.xpath(".//div[@class='card-info-title']");
    private static final By PRODUCT_PRICE = By.xpath(".//div[@class='card-info-price']/meta[@itemprop='price']");
    private static final By PRODUCT_CURRENCY = By.xpath(".//div[@class='card-info-price']/meta[@itemprop='priceCurrency']");

    private static Logger LOGGER = LogManager.getLogger(ApaviPage.class);

    private List<String> filterIdCollector = new ArrayList<String>();

    public ApaviPage(BaseFunctions baseFunctions){
        this.baseFunctions = baseFunctions;
    }

    public void selectKurpes() {
        selectFilter(APAVI_FILTER_TITLE, APAVI_FILTER_TAG_FINDER, "text", baseFunctions.checkShoes);
    }

    public void selectBlackColor() {
        selectFilter(KRASA_FILTER_TITLE, KRASAS, "attribute", baseFunctions.checkColor);
    }

    public void selectNewState() {
        selectFilter(STAVOKLIS_FILTER_TITLE, STAVOKLI, "text", baseFunctions.checkState);
    }

    private void selectFilter(By filterName, By filterElementName, String mode, String checkValue) {
        baseFunctions.isElementPresent(filterName);
        baseFunctions.elementList.clear();
        baseFunctions.isElementPresent(filterElementName);
        baseFunctions.elementList = baseFunctions.getElements(filterElementName);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (workScriptDetector(i, mode).equals(checkValue)) {
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                String checkBoxId = checkMe.getAttribute("for");
                filterIdCollector.add(checkBoxId);
                checkMe.click();
                baseFunctions.driver.navigate().refresh();
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked]"));
                LOGGER.info("<" + baseFunctions.textGet(filterName) + " " + checkValue + "> filter is applied");
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<" + baseFunctions.textGet(filterName) + " " + checkValue + "> filter missed!");
    }

    private String workScriptDetector(int number, String mode) {
        if (mode.equals("text")) {
            return baseFunctions.getTextFromList(baseFunctions.elementList, number);
        }
        else {
            return baseFunctions.getElementFromList(baseFunctions.elementList, number).getAttribute("data-title");
        }
    }

    private List<WebElement> collectFirstFiveProducts() {
        baseFunctions.elementList.clear();
        baseFunctions.isElementPresent(PRODUCT);
        LOGGER.info("Products collected");
        return baseFunctions.elementList = baseFunctions.getElements(PRODUCT);
    }

    private WebElement checkProducts(int i) {
        collectFirstFiveProducts();

        checkList(i);

        WebElement element = findElementFromElementList(PRODUCT_CLICK, i);
        LOGGER.info("Product " + (i+1) + " check started");
        return element;
    }

    private ProductPage goToProductPage (WebElement element) {
        element.click();
        return new ProductPage(baseFunctions);
    }

    public void checkFiveProducts() {
        for (int i = 0; i < baseFunctions.productCountForCheck; i++) {
            String apaviPageTitle = baseFunctions.titleGet();
            ProductPage productPage = goToProductPage(checkProducts(i));

            String productPageTitle = baseFunctions.titleGet();
            Assertions.assertNotEquals(apaviPageTitle, productPageTitle, "Page not switched from apaviPage to productPage!");
            productPage.checkProductPage(i);

            if (i != (baseFunctions.productCountForCheck - 1)) {
                apaviPageTitle = baseFunctions.titleGet();
                Assertions.assertNotEquals(productPageTitle, apaviPageTitle, "Page not switched from productPage to apaviPage!");

                isFiltersRemainingAppliedCheck();
            }
        }
    }

    private void addToProductCheckList (By xPath, int i, String parameter) {
        if (parameter.equals("text")) {
            baseFunctions.productCheckList.add(findElementFromElementList(xPath, i).getText());
        }
        else {
            baseFunctions.productCheckList.add(findElementFromElementList(xPath, i).getAttribute("content"));
        }
    }

    private WebElement findElementFromElementList (By xPath, int number) {
        Assertions.assertFalse(baseFunctions.getElementFromList(baseFunctions.elementList, number).findElements(xPath).isEmpty());
        return baseFunctions.getElementFromList(baseFunctions.elementList, number).findElement(xPath);
    }

    private void checkList(int number) {
        baseFunctions.productCheckList.clear();
        addToProductCheckList(PRODUCT_NAME, number, "text");
        LOGGER.info("Checking product name is: " + baseFunctions.productCheckList.get(0));
        addToProductCheckList(PRODUCT_CURRENCY, number, "attribute");
        LOGGER.info("Checking product currency is: " + baseFunctions.productCheckList.get(1));
        addToProductCheckList(PRODUCT_PRICE, number, "attribute");
        LOGGER.info("Checking product price is: " + baseFunctions.productCheckList.get(2));
    }

    private void isFiltersRemainingAppliedCheck() {
        for (int i = 0; i < filterIdCollector.size(); i++) {
            baseFunctions.isElementPresent(By.xpath(".//*[@id='" + filterIdCollector.get(i) + "'][@checked]"));
        }
        LOGGER.info("apaviPage filters remaining applied");
    }
}
