import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ThirdHomeTask {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.xpath(".//*[@id='layered_id_attribute_group_13']");
    private final By ORANGE_FILTER_ELEMENT_COUNT = By.xpath(".//*[@class='layered_color']/a[text()='Orange']/span");
//    private final By aaa = By.xpath(".//*[text()='Dresses > Color Orange']");
//    private final By bbb = By.xpath(".//*[@class='page-heading product-listing']/text()='Dresses > Color Orange'");
//    private final By ccc = By.xpath(".//h1/span[@class='cat-name']/text()='Dresses > Color Orange'");
    private final By FILTER_CHECK = By.xpath(".//h1/span[contains(text(), 'Dresses > Color Orange')]");
    private final By FILTERED_ELEMENTS = By.xpath(".//*[@class='color-list-container']");
    private final By ELEMENT_ORANGE_COLOR = By.xpath(".//*[@class='color-list-container']/ul/li/a[@style='background:#F39C11;']");
//    private final By eee = By.xpath(".//h1/span[@class='cat-name']");

    private static final Logger LOGGER = LogManager.getLogger(ThirdHomeTask.class);

    public WebDriver driver;

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

        //Check if filtered elements has orange color and it count is the same as mareked count - point 4
        List<WebElement> filteredElements = driver.findElements(FILTERED_ELEMENTS);
        Assertions.assertTrue(!filteredElements.isEmpty());
        LOGGER.info(filteredElements.size());
        Assertions.assertEquals(filteredElements.size(), filteredElementCountNumber(),"IpatijKrjilov");
    }


    
    @AfterEach
    public void driverClose(){
        driver.close();
    }

    public int filteredElementCountNumber(){
        String text = driver.findElement(ORANGE_FILTER_ELEMENT_COUNT).getText();
        Integer result = Integer.valueOf(text.substring(1,text.length()-1));
        return result;
    }

}
