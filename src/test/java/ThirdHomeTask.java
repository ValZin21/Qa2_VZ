import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThirdHomeTask {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.xpath(".//*[@id='layered_id_attribute_group_13']");
    private final By ORANGE_FILTER_PRODUCT_COUNT = By.xpath(".//*[@class='layered_color']/a[text()='Orange']/span");
    private final By FILTER_CHECK = By.xpath(".//h1/span[contains(text(), 'Dresses > Color Orange')]");

    private final By FILTERED_PRODUCTS_COLOR_LISTS = By.xpath(".//*[@class='color-list-container']");
    private final By PRODUCT_ORANGE_COLOR = By.xpath(".//*[@class='color-list-container']/ul/li/a[@style='background:#F39C11;']");
    private final By FILTERED_PRODUCTS_COLOR_LISTS_COUNT = By.xpath(".//*[@class='heading-counter']/span");

    private final By PRODUCT_CLICK = By.xpath(".//*[@class = 'product_img_link']");
    private final By IF_PRODUCT_IS_ORANGE = By.xpath(".//a[@name='Orange']");
    private final By CLOSE_PRODUCT = By.xpath(".//*[@title = 'Close']");

    private final By ADD_TO_CART_CLICK = By.xpath(".//a[contains(@class, 'ajax_add_to_cart_button')]");
    private final By CONTINUE_SHOPPING_CLICK = By.xpath(".//span[contains(@title, 'Continue shopping')]");
    private final By SELECT_PRODUCT = By.xpath(".//*[@class='product-container']");
    private final By PRODUCT_PRICE = By.xpath(".//*[@itemprop='price']");
    private final By PRODUCT_NAME = By.xpath(".//*[@class='product-name']");

    private final By SHOPPING_CART = By.xpath(".//*[@title='View my shopping cart']");
    private final By PRODUCT_IN_CART = By.xpath(".//*[contains(@data-id, 'cart_block_product')]");
    private final By PRODUCT_IN_CART_PRICE = By.xpath(".//*[@class='price']");
    private final By PRODUCT_IN_CART_NAME = By.xpath(".//*[@class='cart_block_product_name']");
    private final By PRODUCTS_SHIPPING_PRICE = By.xpath(".//*[contains(@class, 'cart_block_shipping_cost')]");
    private final By PRODUCTS_TOTAL_PRICE = By.xpath(".//*[contains(@class, 'cart_block_total')]");



    private static final Logger LOGGER = LogManager.getLogger(ThirdHomeTask.class);

    public WebDriver driver;
    List<WebElement> filteredProducts = new ArrayList<WebElement>();
    List<WebElement> inStockProducts = new ArrayList<WebElement>();
    List<String> filteredProductsPrices = new ArrayList<String>();

    @Test
    public void ladiesStore() {

        System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOME_PAGE);
        //Select WOMEN - point 1
        WebElement womenCheck = driver.findElement(WOMAN_BUTTON_SEARCH);
        Assertions.assertEquals("WOMEN", womenCheck.getText(), "No 'Women' button detected");
        String indexPageTitle = driver.getTitle();
        womenCheck.click();

        //Select DRESSES - point 2
        String womenPageTitle = driver.getTitle();
        Assertions.assertFalse(indexPageTitle == womenPageTitle, "Page not changed from Main to WOMEN!!");
        WebElement dressesCheck = driver.findElement(DRESSES_BUTTON_SEARCH);
        Assertions.assertEquals("DRESSES", dressesCheck.getText(), "No dresses button Detected");
        dressesCheck.click();

        //Select ORANGE FILTER - point 3
        String dressesPageTitle = driver.getTitle();
        Assertions.assertFalse(dressesPageTitle == womenPageTitle, "Page not changed from WOMEN to DRESSES");
//        System.out.println("Page title before filter is applied: "  + driver.getTitle());
        LOGGER.info("Page title before filter is applied: "  + driver.getTitle());
        driver.findElement(ORANGE_FILTER_SEARCH).click();
        //timer to wait for filter is applied. Try tp rebuil on wait.until(expectedConditions.visibilityOf(<element>))
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  // try-catch would be nice to receive a beautiful message in case element is not found
        //element which appears after filtering only - until it not detected code execution won't go forward
        WebElement elementAppearedAfterFitlteringCheck = driver.findElement(FILTER_CHECK);

//        System.out.println("Page title after filter is applied: " + driver.getTitle());
        LOGGER.info("Page title after filter is applied: " + driver.getTitle());
        String karl = driver.findElement(FILTER_CHECK).getText();
//        System.out.println("This is filtered product text: " + karl);
        LOGGER.info("This is filtered product text: " + karl);

        //add check if Orange filter present
        Assertions.assertEquals("DRESSES > COLOR ORANGE", driver.findElement(FILTER_CHECK).getText(), "Orange filter is not applied!");

        //point 4 - Check if filtered elements has orange color and it count is the same as mareked count(REFACTOR REQUIRED) here
        filteredProducts = driver.findElements(FILTERED_PRODUCTS_COLOR_LISTS);
        Assertions.assertTrue(!filteredProducts.isEmpty(), "No filtered products detected!");
        LOGGER.info(filteredProducts.size());
        //Filter existing object count check
        Assertions.assertEquals(filteredProducts.size(), filteredBracketsNumberCheck(),"Filter in-breakets product count mismatch with filtered products count");
        //Filtered element count check
        Assertions.assertEquals(filteredProducts.size(), filteredProductCountNumber(),"Filtered product count mismatch with total filtered product count text");

        //point5 - open 1 random item and check if orange is selected

        filteredProducts.clear();
        filteredProducts = driver.findElements(PRODUCT_CLICK);
        Assertions.assertTrue(!filteredProducts.isEmpty(), "No filtered products to click on detected!");

        int randomProductSelector = 0 + (int) (Math.random() * filteredProducts.size());
        LOGGER.info("Random is: " + randomProductSelector);

        filteredProducts.get(randomProductSelector).click();
        //Switching to opend frame
//        driver.switchTo().frame(driver.findElement(By.xpath(".//iframe[contains(@id, 'fancybox')]")));
        driver.switchTo().frame(0);
        //try-catch required for NoSuchElementException handle????
        if(!driver.findElements(IF_PRODUCT_IS_ORANGE).isEmpty()){
            LOGGER.info("Gottcha!");
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

            //back to DRESSES page
            driver.switchTo().defaultContent();
            //closing the frame
            driver.findElement(CLOSE_PRODUCT).click();
        }

        //POINT 6

        WebDriverWait wait = new WebDriverWait(driver, 15);
//        driver.manage().window().setSize(new Dimension(750, 1050));
        filteredProducts = driver.findElements(SELECT_PRODUCT);
        Assertions.assertTrue(!filteredProducts.isEmpty(), "Add To cart butonn missed!");
//        driver.manage().timeouts().implicitlyWait(14, TimeUnit.SECONDS);
        double totalRevenue = 0.0;
        //Adding filtered products to cart. Collecting product prices to filteredProductsPrices list
        Actions manipulator = new Actions(driver);

        for (int i = 0; i < filteredProducts.size(); i++) {

            LOGGER.info(filteredProducts.size());
            //Actions allows to navigate on element(imitation of to put cursor to open the hidden element). Full Form: Actions manipulator = new Actions(driver);
            //read about .perform()!!!!
//            new Actions(driver).moveToElement(filteredProducts.get(i)).perform();
            manipulator.moveToElement(filteredProducts.get(i)).perform();
            wait.until(ExpectedConditions.elementToBeClickable(filteredProducts.get(i).findElement(ADD_TO_CART_CLICK)));

            LOGGER.info("StringPrice: " + filteredProducts.get(i).findElement(PRODUCT_PRICE).getText());
//            totalRevenue = totalRevenue + Double.valueOf(filteredProducts.get(i).findElement(PRODUCT_PRICE).getText().substring(1));
            //not needed
            totalRevenue = productsTotalRevenue(i, totalRevenue);
            LOGGER.info("totalRevenues: " + totalRevenue);

            filteredProducts.get(i).findElement(ADD_TO_CART_CLICK).click();
//            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//            driver.switchTo().activeElement();
            wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(CONTINUE_SHOPPING_CLICK)));
            LOGGER.info("It's time to kick ass and chew buuble gum!");
            driver.findElement(CONTINUE_SHOPPING_CLICK).click();
            LOGGER.info("I love you, Pumpkin!");

//            wait.until(ExpectedConditions.elementToBeClickable(filteredProducts.get(i).findElement(PRODUCT_PRICE)));

//            driver.switchTo().defaultContent();
        }

        //not needed
        totalRevenue = roundPriceToTwoSymbols(totalRevenue);
        LOGGER.info("Total revenue after convertation: " + totalRevenue);

        //opening Shopping Carts Drop-down menu
//        new Actions(driver).moveToElement(driver.findElement(SHOPPING_CART)).perform();
        manipulator.moveToElement(driver.findElement(SHOPPING_CART)).perform();
       // driver.switchTo().activeElement();
        wait.until(ExpectedConditions.elementToBeClickable(PRODUCT_IN_CART));

        Double totalRevenue2 = 0.0;

        inStockProducts = driver.findElements(PRODUCT_IN_CART);
        for (int i = 0; i < inStockProducts.size(); i++) {
            Assertions.assertEquals(
                    //comparing if added elements prices match Etalons
                    filteredProductsPrices.get(i),
                    inStockProducts.get(i).findElement(PRODUCT_IN_CART_PRICE).getText(),
                    "Product " + (i+1) + " prices mismatch!");

            LOGGER.info("Product " + (i+1) + " price validation succeed!");

            totalRevenue2 = productsTotalRevenue2(i, totalRevenue2);

            if ((inStockProducts.size() - i) == 1 ) {
                //checking if total price calculated correctly (s)
                totalRevenue2 = roundPriceToTwoSymbols(totalRevenue2);
                totalRevenue2 = totalRevenue2 + Float.valueOf(driver.findElement(PRODUCTS_SHIPPING_PRICE).getText().substring(1));
                Assertions.assertEquals(totalRevenue2, Double.valueOf(driver.findElement(PRODUCTS_TOTAL_PRICE).getText().substring(1)), "Totals mismatch!");
            }
        }

        LOGGER.info("You made it!");

        /*to do
        ** refactor the code!
        */
    }



    @AfterEach
    public void driverClose(){
        driver.close();
        driver.quit();
    }

    public int filteredBracketsNumberCheck(){
        String text = driver.findElement(ORANGE_FILTER_PRODUCT_COUNT).getText();
        Integer result = Integer.valueOf(text.substring(1,text.length()-1));
        return result;
    }

    public int filteredProductCountNumber(){
        String text = driver.findElement(FILTERED_PRODUCTS_COLOR_LISTS_COUNT).getText();
        LOGGER.info("String is: " + text);
        Integer result = Integer.valueOf(text.substring(10,text.length()-10));
        LOGGER.info("filteredProductCountNumber is: " + result);
        return result;
    }

    public double productsTotalRevenue (int i, double totalPrice) { //this method not needed
        totalPrice = totalPrice + Double.valueOf(filteredProducts.get(i).findElement(PRODUCT_PRICE).getText().substring(1));
        filteredProductsPrices.add(filteredProducts.get(i).findElement(PRODUCT_PRICE).getText());
        return totalPrice;
    }

    public double productsTotalRevenue2 (int i, double totalPrice) {
        totalPrice = totalPrice + Double.valueOf(inStockProducts.get(i).findElement(PRODUCT_IN_CART_PRICE).getText().substring(1));
        return totalPrice;
    }

    public double roundPriceToTwoSymbols (double value){
        return Double.valueOf(String.valueOf(value).substring(0,5));
    }

}
