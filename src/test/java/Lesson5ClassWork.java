import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Lesson5ClassWork {
    private final By ARTICLE = By.xpath(".//h3[@class = 'top2012-title']");
    private final By ARTICLE_TITLE = By.xpath(".//a[@class = 'top2012-title']");
    private final By COMMENT_COUNT = By.xpath(".//a[@class = 'comment-count']");
    private final By ARTICLE_PAGE = By.xpath(".//span[@itemprop = 'headline name']");
    private final By COMMENT_PAGE = By.xpath(".//a[@class = 'comment-main-title-link']");
    private final By REG_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-reg')]/span");
    private final By ANON_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-anon')]/span");

    @Test
    public void delfiPractice() {
        //driver
        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        //Open Browser
        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        //Open Home page
        driver.get("http://rus.delfi.lv/");
        //Find Articles
        List<WebElement> articles = driver.findElements(ARTICLE);
        //find second article
        WebElement article = articles.get(1);
        //find title
        String articleTitle = article.findElement(ARTICLE_TITLE).getText();
        //find comment
        String commentString = article.findElement(COMMENT_COUNT).getText();
        //remove brackets
        commentString = commentString.substring(1,commentString.length()-1);
        //convert string to integer
        Integer commentCount = Integer.valueOf(commentString);
        //store comment count
        //click to Article
        article.click();
        //find Article title
        String articlePageTitle = driver.findElement(ARTICLE_PAGE).getText();
        //Assertion title
        Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
        // find comment
        String articlePageComment = driver.findElement(COMMENT_COUNT).getText();
        //remove brackets
        articlePageComment = articlePageComment.substring(1,articlePageComment.length()-1);
        //convert String to Integer
        Integer articlePageCommentCount = Integer.valueOf(articlePageComment);
        //Assertion comment
        Assertions.assertEquals(commentCount, articlePageCommentCount, "Comments in article page not Equal");
        //Click to comment
        driver.findElement(COMMENT_COUNT).click();
        //find article title
        String commentPageTitle = driver.findElement(COMMENT_PAGE).getText();
        //contains given title
        Assertions.assertTrue(commentPageTitle.contains(articleTitle));
        //find register comments
        String regComment = driver.findElement(REG_COMMENTS).getText();
        //remove brackets
        regComment = regComment.substring(1, regComment.length()-1);
        //convert string to Integer
        Integer regCommentCount = Integer.valueOf(regComment);
        //store registered comments

        // find anonymous comments
        String anonComment = driver.findElement(ANON_COMMENTS).getText();
        //remove brackets
        anonComment = anonComment.substring(1, anonComment.length()-1);
        //convert string to Integer
        Integer anonCommentCount = Integer.valueOf(anonComment);
        //store anonymous comments
        //sum comment count
        Integer sum = regCommentCount + anonCommentCount;
        Assertions.assertEquals(commentCount, sum, "Comments not equal");
        //Assertion comments
    }
}
