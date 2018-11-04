import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.InputMismatchException;

import java.util.List;

public class Lesson5ClassWork {
    private final By ARTICLE = By.xpath(".//h3[@class = 'top2012-title']");
    private final By ARTICLE_TITLE = By.xpath(".//a[@class = 'top2012-title']");
    private final By COMMENT_COUNT = By.xpath(".//a[@class = 'comment-count']");
    private final By ARTICLE_PAGE_WITH_COMMENTS = By.xpath(".//span[@itemprop = 'headline name']");
    private final By ARTICLE_PAGE_WITHOUT_COMMENTS = By.xpath(".//h1[@itemprop = 'name']");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//div[@class='article-title']/a");
    private final By COMMENT_PAGE = By.xpath(".//a[@class = 'comment-main-title-link']");
    private final By REG_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-reg')]/span");
    private final By ANON_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-anon')]/span");
    public WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(Lesson5ClassWork.class);

    @Test
    public void delfiPractice() throws NoSuchElementException {

        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://rus.delfi.lv/");

        List<WebElement> articles = driver.findElements(ARTICLE);
        WebElement article = articles.get(1);

        String articleTitle = articleAndCommentStringDetector(null, article, ARTICLE_TITLE);
        Assertions.assertNotEquals("0", articleTitle, "FAILURE! Second article doesn't exists!");
        String commentString = articleAndCommentStringDetector(null, article, COMMENT_COUNT);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentString);

        LOGGER.info("Moving to Article page");

        article.click();


        Integer articlePageCommentCount = commentIntegerCountDetector(driver, null, ARTICLE_PAGE_COMMENT_COUNT);

        String articlePageTitle;
        if (articlePageCommentCount == 0){
            LOGGER.info("No comments detected for article - comparing only titles");

            articlePageTitle = articleAndCommentStringDetector(driver, null, ARTICLE_PAGE_WITHOUT_COMMENTS);
            Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
        }
        else {
            LOGGER.info("Comparing as articles as comments count");

            articlePageTitle = articleAndCommentStringDetector(driver, null, ARTICLE_PAGE_WITH_COMMENTS);
            Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
            Assertions.assertEquals(commentCount, articlePageCommentCount, "Article page comment count not equal with home page!");
        }


        if (articlePageCommentCount != 0) {

            LOGGER.info("Moving to Comments page");

            driver.findElement(ARTICLE_PAGE_COMMENT_COUNT).click();

            String commentPageTitle = articleAndCommentStringDetector(driver, null, COMMENT_PAGE);
            Assertions.assertTrue(commentPageTitle.contains(articleTitle));

            Integer regCommentCount = commentIntegerCountDetector(driver, null, REG_COMMENTS);
            Integer anonCommentCount = commentIntegerCountDetector(driver, null, ANON_COMMENTS);

            Integer sum = regCommentCount + anonCommentCount;

            Assertions.assertEquals(commentCount, sum, "Comments page comment count not equal with home page!");
        }
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
    
    public String articleAndCommentStringDetector (WebDriver driver, WebElement webElement, By xPath){
        String titleOrCommentText = null;
        try {
            if (driver != null) {
                titleOrCommentText = driver.findElement(xPath).getText();
            }
            else {
                titleOrCommentText = webElement.findElement(xPath).getText();
            }
        }
        catch (NoSuchElementException e) {
            titleOrCommentText = "(0)";
        }
        return titleOrCommentText;
    }

    public Integer commentIntegerCountDetector(WebDriver driver, WebElement webElement, By xPath){
        String commentText = articleAndCommentStringDetector(driver, webElement, xPath);
        Integer commentCount = commentsCountBracketsCutterAndToNumberConverter(commentText);
        return commentCount;
    }

    public Integer commentsCountBracketsCutterAndToNumberConverter (String textString) {
        textString = textString.substring(1, textString.length()-1);
        Integer commentsCountNumber = Integer.valueOf(textString);
        return commentsCountNumber;
    }
}


