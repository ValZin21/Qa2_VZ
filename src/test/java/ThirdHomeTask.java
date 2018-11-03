import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class ThirdHomeTask {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.xpath(".//*[@id='layered_id_attribute_group_13']");
//    private final By aaa = By.xpath(".//*[text()='Dresses > Color Orange']");
//    private final By bbb = By.xpath(".//*[@class='page-heading product-listing']/text()='Dresses > Color Orange'");
//    private final By ccc = By.xpath(".//h1/span[@class='cat-name']/text()='Dresses > Color Orange'");
    private final By FILTER_CHECK = By.xpath(".//h1/span[contains(text(), 'Dresses > Color Orange')]");
//    private final By eee = By.xpath(".//h1/span[@class='cat-name']");

    public WebDriver driver;

    @Test
    public void ladiesStore() {

        System.setProperty("webdriver.chrome.driver", "C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(HOME_PAGE);
        WebElement womenCheck = driver.findElement(WOMAN_BUTTON_SEARCH);
        Assertions.assertEquals("WOMEN", womenCheck.getText(), "No 'Women' button detected");
        String indexPageTitle = driver.getTitle();
        womenCheck.click();

        String womenPageTitle = driver.getTitle();
        Assertions.assertFalse(indexPageTitle == womenPageTitle, "Page not changed from Main to WOMEN!!");
        WebElement dressesCheck = driver.findElement(DRESSES_BUTTON_SEARCH);
        Assertions.assertEquals("DRESSES", dressesCheck.getText(), "No dresses button Detected");
        dressesCheck.click();

        String dressesPageTitle = driver.getTitle();
        Assertions.assertFalse(dressesPageTitle == womenPageTitle, "Page not changed from WOMEN to DRESSES");
        System.out.println("Page title before filter is applied: "  + driver.getTitle());
        driver.findElement(ORANGE_FILTER_SEARCH).click();
        //timer to wait for filter is applied
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);  // try-catch wuld be nice to receive a beautiful message in case element is not found
        //element which appears after filtering only - until it not detected code execution won't go forward
        WebElement elementAppearedAfterFitlteringCheck = driver.findElement(FILTER_CHECK);

        System.out.println("Page title after filter is applied: " + driver.getTitle());
        String karl = driver.findElement(FILTER_CHECK).getText();
        System.out.println("This is filtered product text: " + karl);

        //add check if Orange filter present
        Assertions.assertEquals("DRESSES > COLOR ORANGE", driver.findElement(FILTER_CHECK).getText(), "Orange filter is not applied!");
    }

    @AfterEach
    public void driverClose(){
        driver.close();
    }

}
