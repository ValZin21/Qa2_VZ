import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class SecondHomeTask {

    private final String webIndex = "http://rus.delfi.lv";
    private final String mobileIndex = "http://m.rus.delfi.lv";
    private final By WEB_ARTICLE = By.xpath("(.//a[@class='top2012-title'])");    //[1]
    private final By MOBILE_ARTICLE = By.xpath("(.//a[@class='md-scrollpos']) "); //[1]
    private final int halfListSize = 5;
    private final int webFirstStep = 0;
  //  private final int mobileFirstStep = 5;

    @Test
    public void webMobileComparator(){


        List<String> articleCollection = new ArrayList<String>();


        //setuo driver
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
        for (int i = webFirstStep; i<halfListSize; i++) {   //add method to collect the data in array
            articleCollection.add(webElementCollection.get(i).getText());
            System.out.println("Web Element " + i + " = " + webElementCollection.get(i).getText());     // Check for articles

        }
        //clearing the webElement Array
        webElementCollection = null;
        //open m.rus.delfi.lv
        browserOpener.get(mobileIndex);
        //read all wmobileElements into the list
        webElementCollection=browserOpener.findElements(MOBILE_ARTICLE);
        //read first 5 qrticles to array [5-9]
        for (int i=webFirstStep; i<halfListSize; i++){      //add method to collect the data in array
            articleCollection.add(webElementCollection.get(i).getText());
            System.out.println("Mobile Element " + i + " = " + webElementCollection.get(i).getText());
        }
        //close the browser
        browserOpener.close();
        //articleCollection check
        for (int i = webFirstStep; i < halfListSize*2; i++) {
            System.out.println("articleCollection Element # " + (i+1) + " = " + articleCollection.get(i));
        }
        /**compare the results: 0with5, 1with6, 2with7, 3with8, 4with9
         * return assrtion in case of mismatch
         */ //assertion not working
        for (int i = webFirstStep; i < halfListSize; i++) {
            Assertions.assertSame(articleCollection.get(i), articleCollection.get(i+5), "Article mismatch detected!");
//            if (articleCollection.get(i) == articleCollection.get(i+3)){
//                System.out.println("Article number " + (i+1) + " does not match");
//                break;
//            }  // not working if
        }


       // browserOpener.close();  //close the
    }
}
