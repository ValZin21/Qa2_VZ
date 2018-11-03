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

    private final String webHomePage= "http://rus.delfi.lv";
    private final String mobileHomePage = "http://m.rus.delfi.lv";
    private final By WEB_ARTICLE_SCOUT = By.xpath("(.//h3/a[@class='top2012-title'])");
    private final By MOBILE_ARTICLE_SCOUT = By.xpath("(.//a[@class='md-scrollpos'])");
    private final int mobileArticleTextsCheckStep = 5;
    private final int webArticleTextsCheckStep = 0;
    private final String webArticleTextsCheckMessageText = ": Web Article ";
    private final String mobileArticleTextsCheckMessageText = ": Mobile Article ";

    List<String> articleTexts = new ArrayList<String>();
    List<WebElement> articles = new ArrayList<WebElement>();

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
        browserOpener.get(webHomePage);
        //read all webElements into the list
        articles=browserOpener.findElements(WEB_ARTICLE_SCOUT);
        //read first (web) 5 article names to array [0-4]
        articleCollector(webArticleTextsCheckMessageText, webArticleTextsCheckStep);
        //clearing the webElement Array
        articles.clear();    //not needed due to list will be overwritten automatically during mobile articles adding - left for unexpected cases
        //open m.rus.delfi.lv
        browserOpener.get(mobileHomePage);
        //read all wmobileElements into the list
        articles=browserOpener.findElements(MOBILE_ARTICLE_SCOUT);
        //read second (mobile) 5 qrticles to array [5-9]
        articleCollector(mobileArticleTextsCheckMessageText, mobileArticleTextsCheckStep);
        //close the browser
        browserOpener.close();
        /**compare the results: 0with5, 1with6, 2with7, 3with8, 4with9
         * return assertion in case of mismatch
         */
        checkEqualRows();
    }

    private void articleCollector(String outputText, int step){
        for (int i = 0; i < 5; i++) {
            articleTexts.add(articles.get(i).getText());
            System.out.println("AC Element " +(i+step)+ outputText + (i+1) + " = " + articleTexts.get(i));  // Check for articles in articleTexts list
        }
    }

    private void checkEqualRows(){
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(articleTexts.get(i), articleTexts.get(i + 5), "Article mismatch detected!");
        }
    }
}
