import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class ThirdHomeTask {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.xpath(".//*[@id='layered_id_attribute_group_13']");
    private final By aaa = By.xpath(".//*[text()='Dresses > Color Orange']");
    private final By bbb = By.xpath(".//*[@class='page-heading product-listing']/text()='Dresses > Color Orange'");
    private final By ccc = By.xpath(".//h1/span[@class='cat-name']/text()='Dresses > Color Orange'");
    private final By ddd = By.xpath(".//h1/span[contains(text(), 'Dresses > Color Orange')]");
    private final By eee = By.xpath(".//h1/span[@class='cat-name']");
    //private final By aaa = By.xpath(".//h1/span[@class='cat-name']");
   // private final By aaa = By.xpath(".//h1/span[@class='cat-name']");
    public WebDriver driver;

    @Test
    public void ladiesStore() {

        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
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
        System.out.println("Page title before: "  + driver.getTitle());
        driver.findElement(ORANGE_FILTER_SEARCH).click();


       // WebDriverWait waiter = new WebDriverWait(driver, 10);

       // driver.navigate().to("http://automationpractice.com/index.php?id_category=8&controller=category#/color-orange");
        driver.get("http://automationpractice.com/index.php?id_category=8&controller=category#/color-orange");
        driver.navigate().refresh();



        String karl = driver.findElement(eee).getText();
        System.out.println("This is filter: " + karl);
//       // System.out.println("Page source: "  + driver.getPageSource());
//        System.out.println("Page title: "  + driver.getTitle().c);
//        System.out.println("Page url: "  + driver.getCurrentUrl());

     //   driver.findElement(aaa).e

        //add check if Orange filter present

       // This xpath .//text()='Dresses > Color Orange' return 1. Think how to check 'if 1 then succees, if 0 - error'

      //  Assertions.assertTrue(driver.getPageSource().contains("Dresses > Color Orange"));
     //   Assertions.assertEquals("Dresses > Color Orange", driver.findElement(ddd).getText(), "Orange filter is not applied!");
     //   Assertions.assertNotEquals(orangeFilterCheck, driver.getCurrentUrl(), "PIPEC!");


    }

    @AfterEach
    public void driverClose(){
        driver.close();
    }

}
