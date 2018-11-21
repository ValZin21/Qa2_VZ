package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class ProductPage {

    BaseFunctions baseFunctions;


    private static final By KURPES = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By MElNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By JAUNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
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
}
