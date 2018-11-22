package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ApaviPage {
    BaseFunctions baseFunctions;

    public static final By APAVI_FILTER = By.xpath(".//div[@id='step3_38']/div[@class='step-item']");
    public static final By APAVI_FILTER_TAG_FINDER = By.xpath(".//*[@id='step3_38']/div[@class='step-item']/label");
    public static final By KRASA_FILTER_TITLE = By.xpath(".//*[contains(@class, 'filters-section')]/div[contains(text(), 'Krāsa')]");
    public static final By KRASAS = By.xpath(".//*[contains(@class, 'filter-colors-item')]/label");
    public static final By STAVOKLIS_FILTER_TITLE = By.xpath(".//*[contains(@class, 'filters-section')]/div[contains(text(), 'Stāvoklis')]");
    public static final By STAVOKLI = By.xpath(".//*[contains(@class, 'filter-condition')]/div/label");
    public static final By PRODUCT = By.xpath(".//div[@class = 'col-xs-6 col-sm-3']");
    public static final By PRODUCT_CLICK = By.xpath(".//div/a[@itemprop='url']");
    public static final By PRODUCT_NAME = By.xpath(".//div[@class='card-info-title']");
    public static final By PRODUCT_PRICE = By.xpath(".//div[@class='card-info-price']/meta[@itemprop='price']");
    public static final By PRODUCT_CURRENCY = By.xpath(".//div[@class='card-info-price']/meta[@itemprop='priceCurrency']");

    private static Logger LOGGER = LogManager.getLogger(ApaviPage.class);

    public ApaviPage(BaseFunctions baseFunctions){
        this.baseFunctions = baseFunctions;
    }

    public void selectKurpes() {
        baseFunctions.isElementPresent(APAVI_FILTER_TAG_FINDER);
        baseFunctions.elementList.clear();
        baseFunctions.elementList = baseFunctions.getElements(APAVI_FILTER_TAG_FINDER);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals("Kurpes")) {
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                Assertions.assertTrue(checkMe.findElements(APAVI_FILTER_TAG_FINDER).isEmpty(), "No tag");
                String checkBoxId = baseFunctions.getElementFromList(baseFunctions.elementList, i).getAttribute("for");
                LOGGER.info("Kurpes id: " + checkBoxId);
                baseFunctions.getElement(By.xpath(".//*[@id='" + checkBoxId + "']")).click();
                baseFunctions.driver.navigate().refresh();
                LOGGER.info("Kurpes Clicked!");
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked='checked']"));
                LOGGER.info("Kurpes filter is applied");
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
            if (baseFunctions.getElementFromList(baseFunctions.elementList, i).getAttribute("data-title").equals(baseFunctions.checkColor)) {
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                String checkBoxId = checkMe.getAttribute("for");
                LOGGER.info("Krasas Id: " + checkBoxId);
                checkMe.click();
                baseFunctions.driver.navigate().refresh();
                LOGGER.info("Melna krasa Clicked!");
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked]"));
                LOGGER.info("Melna krasa filter is applied");
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Melna krasa> filter missed!");
    }

    public void selectNewState() {
        baseFunctions.isElementPresent(STAVOKLIS_FILTER_TITLE);
        LOGGER.info("Stavoklis filter detected");
        baseFunctions.elementList.clear();
        baseFunctions.isElementPresent(STAVOKLI);
        LOGGER.info("Stavokli detected");
        baseFunctions.elementList = baseFunctions.getElements(STAVOKLI);
        boolean statementDetected = false;
        for (int i = 0; i < baseFunctions.elementList.size(); i++) {
            if (baseFunctions.getTextFromList(baseFunctions.elementList, i).equals(baseFunctions.checkState)) {
                WebElement checkMe = baseFunctions.getElementFromList(baseFunctions.elementList, i);
                String checkBoxId = checkMe.getAttribute("for");
                LOGGER.info("Stavokla Id: " + checkBoxId);
                checkMe.click();
                baseFunctions.driver.navigate().refresh();
                LOGGER.info("Jauns stavoklis Clicked!");
                baseFunctions.isElementPresent(By.xpath(".//*[@id='" + checkBoxId + "'][@checked]"));
                LOGGER.info("Jauns stavoklis filter is applied");
                statementDetected = true;
                break;
            }
        }
        Assertions.assertTrue(statementDetected, "<Melna krasa> filter missed!");
    }

    public List<WebElement> collectFirstFiveProducts() {
        baseFunctions.elementList.clear();
        baseFunctions.isElementPresent(PRODUCT);
        LOGGER.info("Products collected");
        return baseFunctions.elementList = baseFunctions.getElements(PRODUCT);
    }

    //not finished
    public WebElement checkProducts(int i) {
        collectFirstFiveProducts();

        checkList(i);

        WebElement element = findElementFromListElement(PRODUCT_CLICK, i);
        LOGGER.info("Product " + (i+1) + " check started");
        return element;
    }

    public ProductPage goToProductPage (WebElement element) {
        element.click();
        return new ProductPage(baseFunctions);

    }

    public void checkFiveProducts() {
        for (int i = 0; i < 5; i++) {
            String apaviPageTitle = baseFunctions.titleGet();
            ProductPage productPage = goToProductPage(checkProducts(i));

            String productPageTitle = baseFunctions.titleGet();
            Assertions.assertNotEquals(apaviPageTitle, productPageTitle, "Page not switched from apaviPage to productPage!");
            productPage.checkProductPage();

            apaviPageTitle = baseFunctions.titleGet();
            Assertions.assertNotEquals(productPageTitle, apaviPageTitle, "Page not switched from productPage to apaviPage!");
        }
    }

    protected void addToProductCheckList (By xPath, int i, String parameter) {
        if (parameter.equals("text")) {
            baseFunctions.productCheckList.add(findElementFromListElement(xPath, i).getText());
        }
        else {
            baseFunctions.productCheckList.add(findElementFromListElement(xPath, i).getAttribute("content"));
        }
    }

    protected WebElement findElementFromListElement (By xPath, int number) {
        Assertions.assertFalse(baseFunctions.getElementFromList(baseFunctions.elementList, number).findElements(xPath).isEmpty());
        return baseFunctions.getElementFromList(baseFunctions.elementList, number).findElement(xPath);
    }

    protected void checkList(int number) {
        baseFunctions.productCheckList.clear();
        addToProductCheckList(PRODUCT_NAME, number, "text");
        LOGGER.info("Checking product name is: " + baseFunctions.productCheckList.get(0));
        addToProductCheckList(PRODUCT_CURRENCY, number, "attribute");
        LOGGER.info("Checking product currency is: " + baseFunctions.productCheckList.get(1));
        addToProductCheckList(PRODUCT_PRICE, number, "attribute");
        LOGGER.info("Checking product price is: " + baseFunctions.productCheckList.get(2));
    }
}
