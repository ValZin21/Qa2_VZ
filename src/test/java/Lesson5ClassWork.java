import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.InputMismatchException;

import java.util.List;

public class Lesson5ClassWork {
    private final By ARTICLE = By.xpath(".//h3[@class = 'top2012-title']");
    private final By ARTICLE_TITLE = By.xpath(".//a[@class = 'top2012-title']");
    private final By COMMENT_COUNT = By.xpath(".//a[@class = 'comment-count']");
//    private final By ARTICLE_PAGE = By.xpath(".//h1/a[@class= 'article-title-link']"); //for special pages like  WOMAN
    private final By ARTICLE_PAGE_WITH_COMMENTS = By.xpath(".//span[@itemprop = 'headline name']");
    private final By ARTICLE_PAGE_WITHOUT_COMMENTS = By.xpath(".//h1[@itemprop = 'name']");
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
        WebElement article = articles.get(1);

        String articleTitle = elementTextPullOuter(article, ARTICLE_TITLE);
        String commentString = elementTextPullOuter(article, COMMENT_COUNT);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentString);

        article.click();


        Integer articlePageCommentCount = commentIntegerCountDetector(driver, COMMENT_COUNT);
        String articlePageTitle;
        if (articlePageCommentCount == 0){
            articlePageTitle = elementDetector(driver, ARTICLE_PAGE_WITHOUT_COMMENTS);
        }
        else {
            articlePageTitle = elementDetector(driver, ARTICLE_PAGE_WITH_COMMENTS);
        }

        Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
        Assertions.assertEquals(commentCount, articlePageCommentCount, "Comments count not equal");


        if (articlePageCommentCount == 0) {
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
        return elementString;
    }

    public String elementDetector (WebDriver driver, By xPath){
        String detectedElement = null;
        try {
            detectedElement = driver.findElement(xPath).getText();
            System.out.println("Molodec2");
        }
        catch (org.openqa.selenium.NoSuchElementException e) {
            e.getMessage();
            detectedElement = "(0)";
            System.out.println("Pidor");
        }
        return detectedElement;
    }

    public Integer commentIntegerCountDetector(WebDriver driver2, By xPath){
        String commentElement = elementDetector(driver2, xPath);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentElement);
        return commentCount;
    }
}


