package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {

    BaseFunctions baseFunctions;

    private static final By FOR_MEN = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Vīriešiem')]");
    private static final By KURPES = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");

    private static final By TABLE_ROWS_COUNT = By.xpath(".//table[@class='table']/tbody/tr");

    private static final By PRODUCT_NAME_CHECK = By.xpath(".//h1[@itemprop='name']");
    private static final By PRODUCT_PRICE_CHECK = By.xpath(".//div[contains(@class, 'col-sm-6 price')]/meta[@itemprop='price']");
    private static final By PRODUCT_CURRENCY_CHECK = By.xpath(".//div[contains(@class, 'col-sm-6 price')]/meta[@itemprop='priceCurrency']");

    private static Logger LOGGER = LogManager.getLogger(ProductPage.class);

    public ProductPage(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    private void goBack() {
        LOGGER.info("Going back to ApaviPage");
        baseFunctions.driver.navigate().back();
    }

    private void isForMen() {
        baseFunctions.isElementPresent(FOR_MEN);
        LOGGER.info("Is Vīriešiem");
    }

    private void isKurpes() {
        baseFunctions.isElementPresent(KURPES);
        LOGGER.info("Is kurpes");
    }

    protected void checkProductPage() {
        isCorrectProductSelected();
        isForMen();
        isKurpes();
        LOGGER.info("RowCount: " + getRowCount());
        checkColor();
        checkState();
        goBack();
    }

    private void isCorrectProductSelected() {
        Assertions.assertEquals(baseFunctions.productCheckList.get(0), baseFunctions.textGet(PRODUCT_NAME_CHECK), "Product name mismatch!");
        LOGGER.info("Product name check succeed");
        Assertions.assertEquals(baseFunctions.productCheckList.get(1), baseFunctions.attributeGet(PRODUCT_CURRENCY_CHECK, "content"), "Product currency mismatch!");
        LOGGER.info("Product currency check succeed");
        Assertions.assertTrue(baseFunctions.attributeGet(PRODUCT_PRICE_CHECK, "content").contains(baseFunctions.productCheckList.get(2)));
        LOGGER.info("Product price check succeed");
    }

    private void checkColor() {
        criteriaCheck("Krāsa", baseFunctions.checkColor);
        LOGGER.info("Product color checked successfully");
    }

    private void checkState() {
        criteriaCheck("Stāvoklis", baseFunctions.checkState);
        LOGGER.info("Product state checked successfully");
    }

    private void criteriaCheck(String criteriaNameCheck, String criteriaValueCheck) {
        for (int i = 1; i <= getRowCount(); i++) {
            if (baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + i + "]/td[1]")).equals(criteriaNameCheck)){
                Assertions.assertEquals(criteriaValueCheck,
                        baseFunctions.textGet(By.xpath(".//table[@class='table']/tbody/tr[" + i + "]/td[2]")),
                        "Criteria value mismatched!");
            }
        }
    }

    private Integer getRowCount() {
        List<WebElement> rows = baseFunctions.getElements(TABLE_ROWS_COUNT);
        return rows.size();
    }

}
