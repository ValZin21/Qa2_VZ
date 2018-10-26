import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ThirdHomeTask {

    private final String HOME_PAGE = "http://automationpractice.com/index.php";
    private final By WOMAN_BUTTON_SEARCH = By.xpath(".//li/a[@title='Women']");
    private final By DRESSES_BUTTON_SEARCH = By.xpath(".//ul[@class='sf-menu clearfix menu-content sf-js-enabled sf-arrows']/li/a[@title='Dresses']");
    private final By ORANGE_FILTER_SEARCH = By.xpath(".//*[@id='layered_id_attribute_group_13']");
    private final By aaa = By.xpath(".//*[text()='Dresses > Color Orange']");

    @Test
    public void ladiesStore() {

        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
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

        String dressePageTitle = driver.getTitle();
        Assertions.assertFalse(dressePageTitle == womenPageTitle, "Page not changed from WOMEN to DRESSES");
        driver.findElement(ORANGE_FILTER_SEARCH).click();

        //add check if Orange filter present

        Assertions.assertEquals("Dresses > Color Orange", driver., "Orange filter is not applied!");
     //   Assertions.assertNotEquals(orangeFilterCheck, driver.getCurrentUrl(), "PIPEC!");

        //driver.close();
    }

}
