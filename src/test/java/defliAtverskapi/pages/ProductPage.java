package defliAtverskapi.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ProductPage {

    BaseFunctions baseFunctions;


    private static final By KURPES = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By MElNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");
    private static final By JAUNS = By.xpath(".//a[@itemprop='item']/span[contains(text(), 'Kurpes')]");

    public ProductPage(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public void goBack() {
        baseFunctions.driver.navigate().back();

    }

    public void isKurpes(By xPath) {
        baseFunctions.isElementPresent(KURPES);
    }

    public void checkProductPage() {
        goBack();
    }
}
