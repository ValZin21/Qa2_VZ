import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import java.util.List;

public class Lesson5ClassWork {
    private final By ARTICLE = By.xpath(".//h3[@class = 'top2012-title']");
    private final By ARTICLE_TITLE = By.xpath(".//a[@class = 'top2012-title']");
    private final By COMMENT_COUNT = By.xpath(".//a[@class = 'comment-count']");
    private final By ARTICLE_PAGE = By.xpath(".//h1/a[@class= 'article-title-link']");
//    private final By ARTICLE_PAGE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By COMMENT_PAGE = By.xpath(".//a[@class = 'comment-main-title-link']");
    private final By REG_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-reg')]/span");
    private final By ANON_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-anon')]/span");
    public WebDriver driver;

    @Test
    public void delfiPractice() throws InputMismatchException {

        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");

        List<WebElement> articles = driver.findElements(ARTICLE);
        WebElement article = articles.get(6);

        String articleTitle = elementTextPullOuter(article, ARTICLE_TITLE);
        //add check for case if no comments present
        // and (?) if additional breakets with string added in the end of the article on the home page
        String commentString = elementTextPullOuter(article, COMMENT_COUNT);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentString);

        article.click();

        String articlePageTitle = elementDetector(driver, ARTICLE_PAGE);
        Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");

        Integer articlePageCommentCount = commentIntegerCountDetector(driver, COMMENT_COUNT);

        Assertions.assertEquals(commentCount, articlePageCommentCount, "Comments count not equal");

        boolean g = xPathChecker(COMMENT_COUNT);
        if (g) {
            //elementDetector(driver, COMMENT_COUNT).click();
            driver.findElement(COMMENT_COUNT).click();

            String commentPageTitle = elementDetector(driver, COMMENT_PAGE);
            Assertions.assertTrue(commentPageTitle.contains(articleTitle));

            Integer regCommentCount = commentIntegerCountDetector(driver, REG_COMMENTS);
            Integer anonCommentCount = commentIntegerCountDetector(driver, ANON_COMMENTS);

            Integer sum = regCommentCount + anonCommentCount;

            Assertions.assertEquals(commentCount, sum, "Comments not equal");
        }
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }

    public Integer commentsCountBracketsCutterAndToNumberConverter (String textString) {
        textString = textString.substring(1, textString.length()-1);
        Integer commentsCountNumber = Integer.valueOf(textString);
        return commentsCountNumber;
    }

//    public String elementTextPullOuter (WebElement webElement, By xPath, boolean ifExists){
//        String elementString = null;
//        if (ifExists) {
//            elementString = webElement.findElement(xPath).getText();
//        }
//        return elementString;
//    }

    public String elementTextPullOuter (WebElement webElement, By xPath){
        String elementString = null;
        try {
            elementString = webElement.findElement(xPath).getText();
            System.out.println("Molodec1");
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            e.getMessage();
            System.out.println("Indus!1");
            elementString = "(0)";
        }
        //String elementString = webElement.findElement(xPath).getText();
        return elementString;
    }

//    public String elementTextPullOuter (WebElement webElement, By xPath){
//        String elementString = null;
//        Boolean elementExists = driver.findElements(xPath).size() > 0;
//        if (elementExists == true) {
//            elementString = webElement.findElement(xPath).getText();
//        }
//        else {
//            elementString = "0";
//        }
//        // String elementString = webElement.findElement(xPath).getText();
//        return elementString;
//    }

    //    boolean present;
//try {
//        driver.findElement(By.id("logoutLink"));
//        present = true;
//        } catch (NoSuchElementException e) {
//        present = false;
//        }

//    public WebElement elementDetector (WebDriver driver, By xPath, boolean ifExists){
//        WebElement detectedElement = null;
//        if (ifExists) {
//            detectedElement = driver.findElement(xPath);
//        }
//        return detectedElement;
//    }

    public String elementDetector (WebDriver driver, By xPath){
      //  boolean isFull;
        String detectedElement = null;
        try {
            detectedElement = driver.findElement(xPath).getText();
            System.out.println("Molodec2");
       //     isFull = true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            e.getMessage(); //workin if catch an exception!!!!
      //      isFull = false;
            System.out.println("TurboIndus");
            detectedElement = "(0)";
            //detectedElement.sendKeys("(0)");
        }


        // WebElement detectedElement = driver.findElement(xPath);
        return detectedElement;
    }

//    public WebElement elementDetector (WebDriver driver, By xPath){
//
//        WebElement detectedElement = null;
//        boolean elementExists = driver.findElements(xPath).size() > 0;
//        if (elementExists == true) {
//            detectedElement = driver.findElement(xPath);
//        }
//        else {
//            detectedElement.sendKeys("0");
//        }
//
//        // WebElement detectedElement = driver.findElement(xPath);
//        return detectedElement;
//    }

    public Integer commentIntegerCountDetector(WebDriver driver2, By xPath){
        String commentElement = elementDetector(driver2, xPath);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentElement);
        return commentCount;
    }

    public boolean xPathChecker (By xPath) {
        boolean ifExists;
        WebElement detectedElement = null;
        try {
            detectedElement = driver.findElement(xPath);
            ifExists = true;
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            e.getMessage();
            ifExists = false;
        }
        return ifExists;
    }
}


