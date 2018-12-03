import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class LadiesStoreCheck {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.id("layered_id_attribute_group_13");
    private final By ORANGE_FILTER_PRODUCT_COUNT = By.xpath(".//*[@class='layered_color']/a[text()='Orange']/span");
    private final By FILTER_CHECK = By.xpath(".//h1/span[contains(text(), 'Dresses > Color Orange')]");

    private final By FILTERED_PRODUCTS_COLOR_LISTS = By.className("color-list-container");
    private final By FILTERED_PRODUCTS_COLOR_LISTS_COUNT = By.xpath(".//*[@class='heading-counter']/span");

    private final By PRODUCT_CLICK = By.className("product_img_link");
    private final By IF_PRODUCT_IS_ORANGE = By.xpath(".//a[@name='Orange']");
    private final By CLOSE_PRODUCT = By.xpath(".//*[@title = 'Close']");

    private final By ADD_TO_CART_CLICK = By.xpath(".//a[@title = 'Add to cart']/span");
    private final By CONTINUE_SHOPPING_CLICK = By.xpath(".//span[contains(@title, 'Continue shopping')]");
    private final By SELECT_PRODUCT = By.xpath(".//div[contains(@class, 'product-container')]");
    private final By PRODUCT_PRICE = By.xpath(".//*[@itemprop='price']");

    private final By SHOPPING_CART = By.xpath(".//*[@title='View my shopping cart']");
    private final By PRODUCT_IN_CART = By.xpath(".//*[contains(@data-id, 'cart_block_product')]");
    private final By PRODUCT_IN_CART_PRICE = By.className("price");
    private final By PRODUCTS_SHIPPING_PRICE = By.xpath(".//*[contains(@class, 'cart_block_shipping_cost')]");
    private final By PRODUCTS_TOTAL_PRICE = By.xpath(".//*[contains(@class, 'cart_block_total')]");

    private static final Logger LOGGER = LogManager.getLogger(LadiesStoreCheck.class);

    public WebDriver driver;
    public WebDriverWait wait;
    public Actions moveCursor;
    List<WebElement> filteredProducts = new ArrayList<WebElement>();
    List<WebElement> inStockProducts = new ArrayList<WebElement>();
    List<String> filteredProductsPrices = new ArrayList<String>();

    @Test
    public void dressesCheck() {
        System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 15);
        moveCursor = new Actions(driver);
        driver.manage().window().maximize();
        driver.get(HOME_PAGE);

        //Select WOMEN - point 1
        String indexPageTitle = driver.getTitle();
        WebElement womenCheck = getElement(WOMAN_BUTTON_SEARCH, 0, null);
        Assertions.assertEquals("WOMEN", womenCheck.getText(), "No 'Women' button detected");
        womenCheck.click();

        //Select DRESSES - point 2
        String womenPageTitle = driver.getTitle();
        Assertions.assertFalse(indexPageTitle == womenPageTitle, "Page not changed from Main to WOMEN!!");
        WebElement dressesCheck = getElement(DRESSES_BUTTON_SEARCH, 0 , null);
        Assertions.assertEquals("DRESSES", dressesCheck.getText(), "No 'Dresses' button Detected");
        dressesCheck.click();

        //Select ORANGE FILTER - point 3
        String dressesPageTitle = driver.getTitle();
        Assertions.assertFalse(dressesPageTitle == womenPageTitle, "Page not changed from WOMEN to DRESSES");
        LOGGER.info("Page title before filter is applied: "  + driver.getTitle());

        elementClick(ORANGE_FILTER_SEARCH, 0, null);
        isElementPresent(FILTER_CHECK);
        String elementAppearedAfterFitlteringCheck = textGet(FILTER_CHECK, 0, null);
        LOGGER.info("Page title after filter is applied: " + driver.getTitle());
        LOGGER.info("This is filtered product text: " + elementAppearedAfterFitlteringCheck);
        
        Assertions.assertEquals("DRESSES > COLOR ORANGE", elementAppearedAfterFitlteringCheck, "Orange filter is not applied!");

        //point 4 - Check if filtered elements has orange color and it count is the same as marked count
        filteredProducts = getElements(FILTERED_PRODUCTS_COLOR_LISTS);
        Assertions.assertEquals(filteredProducts.size(), filteredBracketsNumberCheck(),"Filter in-breakets product count mismatch with filtered products count");
        Assertions.assertEquals(filteredProducts.size(), filteredProductCountNumber(),"Filtered product count mismatch with total filtered product count text");

        //point5 - open 1 random item and check if orange is selected
        filteredProducts.clear();
        filteredProducts = getElements(PRODUCT_CLICK);

        checkRandomProductForOrangeColor();

        //POINT 6
        filteredProducts = getElements(SELECT_PRODUCT);

        addProductsToCart();
        
        moveOnElelement(getElement(SHOPPING_CART, 0 , null));
        isElementVisible(PRODUCT_IN_CART);

        checkInCartRevenues();
        LOGGER.info("FIN.");
    }

    @AfterEach
    private void driverClose(){
        driver.quit();
    }

    private int filteredBracketsNumberCheck(){
        String text = textGet(ORANGE_FILTER_PRODUCT_COUNT, 0, null);
        Integer result = Integer.valueOf(text.substring(1,text.length()-1));
        return result;
    }

    private int filteredProductCountNumber(){
        String text = textGet(FILTERED_PRODUCTS_COLOR_LISTS_COUNT, 0, null);
        LOGGER.info("String is: " + text);
        Integer result = Integer.valueOf(text.substring(10,text.length()-10));
        LOGGER.info("filteredProductCountNumber is: " + result);
        return result;
    }

    private double productsTotalRevenue (int i, double totalPrice) {
        totalPrice = totalPrice + Double.valueOf(removeDollar(textGet(PRODUCT_IN_CART_PRICE, i, inStockProducts)));
        return totalPrice;
    }

    private double roundPriceToTwoSymbols (double value){
        return Double.valueOf(String.valueOf(value).substring(0,5));
    }

    private String textGet (By xpath, int i, List<WebElement> list) {
        return getElement(xpath, i , list).getText();
    }

    private WebElement getElement (By xpath, int i, List<WebElement> list) {
        WebElement result;
        if (list == null) {
            result = driver.findElement(xpath);
        }
        else {
            result = list.get(i).findElement(xpath);
        }
        return  result;
    }

    private void elementClick (By xpath, int i, List<WebElement> list) {
        getElement(xpath, i , list).click();
    }

    private String removeDollar (String text) {
        return text.substring(1);
    }

    private List<WebElement> getElements (By xPath) {
        Assertions.assertFalse(driver.findElements(xPath).isEmpty(), "List is empty");
        return driver.findElements(xPath);
    }

    private Double totalRevenueNumberPrecalculated() {
        return Double.valueOf(removeDollar(textGet(PRODUCTS_TOTAL_PRICE, 0, null)));
    }

    private void isElementVisible(By xPath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(xPath));
    }

    private void isElementPresent(By xPath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xPath));
    }

    private void isElemenClickable(By xPath, WebElement element) {
        if (xPath != null) {
            wait.until(ExpectedConditions.elementToBeClickable(xPath));
        }
        else {
            wait.until(ExpectedConditions.elementToBeClickable(element));
        }
    }

    private void moveOnElelement(WebElement element) {
        moveCursor.moveToElement(element).perform();
    }

    private void checkRandomProductForOrangeColor() {
        int randomProductSelector = 0 + (int) (Math.random() * filteredProducts.size());
        LOGGER.info("Random is: " + randomProductSelector);

        filteredProducts.get(randomProductSelector).click();
        driver.switchTo().frame(0);

        Assertions.assertFalse(driver.findElements(IF_PRODUCT_IS_ORANGE).isEmpty(), "No orange filter present for this product!");
        LOGGER.info("Checked element has Orange color!");
        driver.switchTo().defaultContent();
        isElemenClickable(CLOSE_PRODUCT, null);
        elementClick(CLOSE_PRODUCT, 0, null);
    }

    private void addProductsToCart() {
        for (int i = 0; i < filteredProducts.size(); i++) {
            moveOnElelement(filteredProducts.get(i));
            isElemenClickable(null, getElement(ADD_TO_CART_CLICK, i, filteredProducts));
            LOGGER.info("StringPrice: " + textGet(PRODUCT_PRICE , i, filteredProducts));

            filteredProductsPrices.add(filteredProducts.get(i).findElement(PRODUCT_PRICE).getText());

            elementClick(ADD_TO_CART_CLICK, i, filteredProducts);
            isElemenClickable(null, getElement(CONTINUE_SHOPPING_CLICK, 0, null));
            LOGGER.info("Continue shopping button detected!");
            elementClick(CONTINUE_SHOPPING_CLICK, 0 ,null);
            LOGGER.info("Continue shopping button executed!");
        }
    }

    private void checkInCartRevenues() {
        Double totalRevenue = 0.0;

        inStockProducts = getElements(PRODUCT_IN_CART);
        for (int i = 0; i < inStockProducts.size(); i++) {
            isElementVisible(PRODUCT_IN_CART_PRICE);
            Assertions.assertEquals(
                    filteredProductsPrices.get(i),
                    textGet(PRODUCT_IN_CART_PRICE, i, inStockProducts),
                    "Product " + (i+1) + " prices mismatch!");

            LOGGER.info("Product " + (i+1) + " price validation succeed!");

            totalRevenue = productsTotalRevenue(i, totalRevenue);

            if ((inStockProducts.size() - i) == 1 ) {
                //checking if total price calculated correctly (s)
                totalRevenue = roundPriceToTwoSymbols(totalRevenue);
                totalRevenue = totalRevenue + Double.valueOf(removeDollar(textGet(PRODUCTS_SHIPPING_PRICE, 0, null)));
                LOGGER.info("InStock total revenue calculated successfully!");
                Assertions.assertEquals(totalRevenue, totalRevenueNumberPrecalculated(), "Totals mismatch!");
                LOGGER.info("InStock 'Total' revenue field value check succeed!");
            }
        }
    }

}
