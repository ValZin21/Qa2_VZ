package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPage {

    BaseFunctions baseFunctions;

    protected List<WebElement> criteriaCollector = new ArrayList<WebElement>();

    private static final By KURPES = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By PRODUCT_CRITERIAS = By.xpath(".//table[@class='table']/tbody/tr");
    private static final By PRODUCT_COLOR_ROW_DETECTION = By.xpath(".//tr[@class]/td[contains(text(), 'Krāsa')]");
    private static final By PRODUCT_STATE_ROW_DETECTION = By.xpath(".//tr[@class]/td[contains(text(), 'Stāvoklis')]");
    private static final By PRODUCT_CRITERIA_VALUE = By.xpath(".//tr[@class]/td/b");

    private static final By TABLE_ROWS_COUNT = By.xpath(".//table[@class='table']/tbody/tr");
    private static final By TABLE_COLUMNS_COUNT = By.xpath(".//table[@class='table']/tbody/tr/td");


    private static final By PRODUCT_NAME_CHECK = By.xpath(".//h1[@itemprop='name']");
    private static final By PRODUCT_PRICE_CHECK = By.xpath(".//div[contains(@class, 'col-sm-6 price')]/meta[@itemprop='price']");
    private static final By PRODUCT_CURRENCY_CHECK = By.xpath(".//div[contains(@class, 'col-sm-6 price')]/meta[@itemprop='priceCurrency']");

    private static Logger LOGGER = LogManager.getLogger(ProductPage.class);

    public ProductPage(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public void goBack() {
        LOGGER.info("Going back to ApaviPage");
        baseFunctions.driver.navigate().back();

    }

    public void isKurpes() {
        baseFunctions.isElementPresent(KURPES);
        LOGGER.info("Is kurpes");
    }

    public void checkProductPage() {
        isCorrectProductSelected();
        isKurpes();
        LOGGER.info("RowCount: " + getRowCount());
//        LOGGER.info("ColumnCount: " + getColumnCount());
        checkColor();
        checkState();

        goBack();
    }

    public void isCorrectProductSelected() {
        Assertions.assertEquals(baseFunctions.productCheckList.get(0), baseFunctions.textGet(PRODUCT_NAME_CHECK), "Product name mismatch!");
        LOGGER.info("Product name check succeed");
        Assertions.assertEquals(baseFunctions.productCheckList.get(1), baseFunctions.attributeGet(PRODUCT_CURRENCY_CHECK, "content"), "Product currency mismatch!");
        LOGGER.info("Product currency check succeed");
        Assertions.assertTrue(baseFunctions.attributeGet(PRODUCT_PRICE_CHECK, "content").contains(baseFunctions.productCheckList.get(2)));
        LOGGER.info("Product price check succeed");
    }

    public void checkColor() {
        criteriaCheck("Krāsa", baseFunctions.checkColor);
        LOGGER.info("Product color checked successfully");
    }

    public void checkState() {
        criteriaCheck("Stāvoklis", baseFunctions.checkState);
        LOGGER.info("Product state checked successfully");
    }

//    private void criteriaCheck(By criteriaNameLocator, String criteriaNameCheck, String criteriaValueCheck) {
//        criteriaCollector.clear();
//        baseFunctions.isElementPresent(By.xpath(".//table[@class='table']"));
//        baseFunctions.isElementPresent(PRODUCT_CRITERIAS);
//        criteriaCollector = baseFunctions.getElements(PRODUCT_CRITERIAS);
//        for (int i = 0; i < criteriaCollector.size(); i++) {
//            baseFunctions.isElementPresent(criteriaNameLocator);
//            Assertions.assertFalse(criteriaCollector.get(i).findElements(criteriaNameLocator).isEmpty(), "No such criteria!");  // I want function gorlist.get(i).findElements(xPath);
//            String criteriaName = criteriaCollector.get(i).findElement(criteriaNameLocator).getText();
//            if (criteriaName.contains(criteriaNameCheck)) {
//                Assertions.assertFalse(criteriaCollector.get(i).findElements(PRODUCT_CRITERIA_VALUE).isEmpty(), "");  // I want function gorlist.get(i).findElements(xPath);
//                Assertions.assertEquals(criteriaValueCheck, criteriaCollector.get(i).findElement(PRODUCT_CRITERIA_VALUE).getText(), "Values mismatch!");
//            }
//        }
//    }

    public void criteriaCheck(String criteriaNameCheck, String criteriaValueCheck) {
//        for (int i = 1; i <= getRowCount(); i++) {
//            for (int j = 1; j <= getColumnCount(); j++) {
//                if (baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + (i) + "]/td[" + (j) + "]")).equals(criteriaNameCheck)) {
//                    Assertions.assertEquals(criteriaValueCheck,
//                            baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + (i) + "]/td[" + (j) + "]")),
//                            "Criteria value mismatched!");
//                }
//            }
//        }
        for (int i = 1; i <= getRowCount(); i++) {
            if (baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + i + "]/td[1]")).equals(criteriaNameCheck)){
                Assertions.assertEquals(criteriaValueCheck,
                        baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + i + "]/td[2]")),
                        "Criteria value mismatched!");
            }
        }

    }

    public Integer getRowCount() {
        List<WebElement> rows = baseFunctions.getElements(TABLE_ROWS_COUNT);
        return rows.size();
    }

    public Integer getColumnCount() {
        List<WebElement> columns = baseFunctions.getElements(TABLE_COLUMNS_COUNT);
        return columns.size()/getRowCount();
    }




}
