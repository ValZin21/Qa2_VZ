import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class SecondHomeTask {

    private final String webIndex = "http://rus.delfi.lv";
    private final String mobileIndex = "http://m.rus.delfi.lv";
    private final By WEB_ARTICLE_SCOUT = By.xpath("(.//h3/a[@class='top2012-title'])");
    private final By MOBILE_ARTICLE_SCOUT = By.xpath("(.//a[@class='md-scrollpos'])");
    private final int halfListSize = 5;
    private final int firstStep = 0;
    private final String webArticleCheck = ": Web Article ";
    private final String mobileArticleCheck = ": Mobile Article ";

    List<String> articleCollection = new ArrayList<String>();
    List<WebElement> webElementCollection = new ArrayList<WebElement>();

    @Test
    public void webMobileComparator(){
        //setup driver
        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        //initiate WebDriver
        WebDriver browserOpener = new ChromeDriver();
        //open browser
        //maximize window
        browserOpener.manage().window().maximize();
        //go to rus.delfi.lv
        browserOpener.get(webIndex);
        //read all webElements into the list
        webElementCollection=browserOpener.findElements(WEB_ARTICLE_SCOUT);
        //read first (web) 5 article names to array [0-4]
        articleCollector(webArticleCheck, firstStep);
        //clearing the webElement Array
        webElementCollection.clear();    //not needed due to list will be overwritten automatically during mobile articles adding - left for unexpected cases
        //open m.rus.delfi.lv
        browserOpener.get(mobileIndex);
        //read all wmobileElements into the list
        webElementCollection=browserOpener.findElements(MOBILE_ARTICLE_SCOUT);
        //read second (mobile) 5 qrticles to array [5-9]
        articleCollector(mobileArticleCheck, halfListSize);
        //close the browser
        browserOpener.close();
        /**compare the results: 0with5, 1with6, 2with7, 3with8, 4with9
         * return assertion in case of mismatch
         */
        checkEqualRows();
    }

    private void articleCollector(String outputText, int step){
        for (int i = firstStep; i < halfListSize; i++) {
            articleCollection.add(webElementCollection.get(i).getText());
            System.out.println("AC Element " +(i+step)+ outputText + (i+1) + " = " + articleCollection.get(i));  // Check for articles in articleCollection list
        }
    }

    private void checkEqualRows(){
        for (int i = firstStep; i < halfListSize; i++) {
            Assertions.assertEquals(articleCollection.get(i), articleCollection.get(i + halfListSize), "Article mismatch detected!");
        }
    }
}
