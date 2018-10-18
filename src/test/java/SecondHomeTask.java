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
    private final By WEB_ARTICLE = By.xpath("(.//h3/a[@class='top2012-title'])"); //(.//h3/a[@class = 'top2012-title']/text())[1] - for manual select by article on the web-page
    private final By MOBILE_ARTICLE = By.xpath("(.//a[@class='md-scrollpos']) ");
    private final int halfListSize = 5;
    private final int firstStep = 0;

    @Test
    public void webMobileComparator(){

        List<String> articleCollection = new ArrayList<String>();

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
        List<WebElement> webElementCollection=browserOpener.findElements(WEB_ARTICLE);
        //read first 5 article names to arraay [0-4]
        for (int i = firstStep; i<halfListSize; i++) {   //add method to collect the data in array
            articleCollection.add(webElementCollection.get(i).getText());
            //System.out.println("Web Element " + i + " = " + webElementCollection.get(i).getText());     // Check for articles in webElementCollection list
            System.out.println("Web Element " + i + " = " + articleCollection.get(i));                  // Check for articles in articleCollection list
        }
        //clearing the webElement Array
        webElementCollection.clear();    //not needed due to list will be overwritten automatically - left for unexpected cases
        //open m.rus.delfi.lv
        browserOpener.get(mobileIndex);
        //read all wmobileElements into the list
        webElementCollection=browserOpener.findElements(MOBILE_ARTICLE);
        //read first 5 qrticles to array [5-9]
        for (int i=firstStep; i<halfListSize; i++){      //add method to collect the data in array
            articleCollection.add(webElementCollection.get(i).getText());
            //System.out.println("Mobile Element " + i + " = " + webElementCollection.get(i).getText());    // Check for articles in webElementCollection list
            System.out.println("Mobile Element " + (i) + " = " + articleCollection.get(i+5));             // Check for articles in articleCollection list
        }
        //close the browser
        browserOpener.close();
        //articleCollection check
        for (int i = firstStep; i < halfListSize*2; i++) {
            System.out.println("articleCollection Element # " + (i+1) + " = " + articleCollection.get(i));
        }
        /**compare the results: 0with5, 1with6, 2with7, 3with8, 4with9
         * return assertion in case of mismatch
         */
        for (int i = firstStep; i < halfListSize; i++) {
            Assertions.assertEquals(articleCollection.get(i), articleCollection.get(i+5), "Article mismatch detected!");
        }
    }
}
