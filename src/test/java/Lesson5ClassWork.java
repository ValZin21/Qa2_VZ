import com.google.common.annotations.VisibleForTesting;
import org.junit.jupiter.api.AfterEach;
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
    public WebDriver driver;

    @Test
    public void delfiPractice() {

        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");

        List<WebElement> articles = driver.findElements(ARTICLE);
        WebElement article = articles.get(1);
        String articleTitle = elementTextPullOuter(article, ARTICLE_TITLE);
        String commentString = elementTextPullOuter(article, COMMENT_COUNT); //article.findElement(COMMENT_COUNT).getText();

        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentString);

        article.click();


        String articlePageTitle = driver.findElement(ARTICLE_PAGE).getText();
        Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
        String articlePageComment = driver.findElement(COMMENT_COUNT).getText();

        Integer articlePageCommentCount = commentsCountBracketsCutterAndToNumberConverter(articlePageComment);

        Assertions.assertEquals(commentCount, articlePageCommentCount, "Comments in article page not Equal");

        driver.findElement(COMMENT_COUNT).click();


        String commentPageTitle = driver.findElement(COMMENT_PAGE).getText();
        Assertions.assertTrue(commentPageTitle.contains(articleTitle));
        String regComment = driver.findElement(REG_COMMENTS).getText();

        Integer regCommentCount = commentsCountBracketsCutterAndToNumberConverter(regComment);

        String anonComment = driver.findElement(ANON_COMMENTS).getText();

        Integer anonCommentCount = commentsCountBracketsCutterAndToNumberConverter(anonComment);

        Integer sum = regCommentCount + anonCommentCount;

        Assertions.assertEquals(commentCount, sum, "Comments not equal");
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
        String elementString = webElement.findElement(xPath).getText();
        return elementString;
    }
}
