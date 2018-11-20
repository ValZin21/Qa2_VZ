package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ProductPage {

    BaseFunctions baseFunctions;


    private static final By KURPES = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By MElNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By JAUNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");

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
        isKurpes();
        goBack();
    }
}
