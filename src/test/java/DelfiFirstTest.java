import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DelfiFirstTest {
    //final - parameter can not be changed
    private final By ARTICLE_TITLE = By.xpath(".//a[@class='top2012-title']");  // my first locator
    private final String HOME_PAGE = "http://rus.delfi.lv";
    private final String articleNameToCheck = "Воскресенье стало самым теплым 14 октября в истории метеонаблюдений";
    /**
     *  This test will test first article title presents on page
     */
    @Test
    public void delfiFirstTitleTest () {
        //set the article name
       // String articleNameToCheck = "Воскресенье стало самым теплым 14 октября в истории метеонаблюдений";
        //enable webDriver
        //open browser
        System.setProperty("webdriver.gecko.driver", "C:/geckodriver-v0.23.0-win64/geckodriver.exe"); //set the system property
        WebDriver browser = new FirefoxDriver();
        //change window size
        browser.manage().window().maximize();
        //go to delfi index page
        browser.get(HOME_PAGE);
        //wait some for the page is opened and load - not needed due to Selenium wait itself;

        //find the first news article element
        WebElement article = browser.findElement(ARTICLE_TITLE); // store the dected element as an article parameter
        //check if the first article elements name equals with expected value
        Assertions.assertEquals(articleNameToCheck, article.getText(), "This is not true atricle");



    }

}
