import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class DelfiArticleTest {

    private final By ARTICLE = By.xpath(".//span[@class = 'text-size-22']");
    private final By ARTICLE_TITLE = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");
    private final By ARTICLE_PAGE_WITH_COMMENTS = By.xpath(".//h1[contains(@class, 'text-size-22')]");
    private final By ARTICLE_PAGE_COMMENT_COUNT = By.xpath(".//a[contains(@class, 'text-size-19')]");
    private final By COMMENT_PAGE = By.xpath(".//a[@class = 'text-mine-shaft']");
    private final By REG_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-reg')]/span");
    private final By ANON_COMMENTS = By.xpath(".//a[contains(@class,'comment-thread-switcher-list-a-anon')]/span");
    private final String DEFLI_HOME_PAGE = "http://rus.delfi.lv/";

    public WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(DelfiArticleTest.class);

    @Test
    public void delfiPractice() throws NoSuchElementException {

        System.setProperty("webdriver.gecko.driver", "C:/geckodriver-v0.23.0-win64/geckodriver.exe"); //set the system property
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get(DEFLI_HOME_PAGE);

        List<WebElement> articles = driver.findElements(ARTICLE);
        Assertions.assertTrue(!articles.isEmpty());
        WebElement article = articles.get(1);

        String articleTitle = articleAndCommentStringDetector(article, ARTICLE_TITLE);
        LOGGER.info("Detected article is: " + articleTitle);
        Assertions.assertNotEquals("0", articleTitle, "FAILURE! Second article doesn't exists!");
        String commentString = articleAndCommentStringDetector(article, COMMENT_COUNT);
        LOGGER.info("Articles comment count is: " + commentString);
        Integer commentCount = getCommentsFromString(commentString);

        LOGGER.info("Moving to Article page");

        article.click();

        Integer articlePageCommentCount = commentIntegerCountDetector( null, ARTICLE_PAGE_COMMENT_COUNT);

        String articlePageTitle;
        if (articlePageCommentCount == 0){
            LOGGER.info("No comments detected for article - comparing only titles");

            articlePageTitle = articleAndCommentStringDetector(null, ARTICLE_PAGE_WITH_COMMENTS); //without comments was here
            Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
        }
        else {
            LOGGER.info("Comparing as articles as comments count");

            articlePageTitle = articleAndCommentStringDetector(null, ARTICLE_PAGE_WITH_COMMENTS);
            Assertions.assertEquals(articleTitle, articlePageTitle, "Articles not equal");
            Assertions.assertEquals(commentCount, articlePageCommentCount, "Article page comment count not equal with home page!");
        }

        if (articlePageCommentCount != 0) {

            LOGGER.info("Moving to Comments page");

            driver.findElement(ARTICLE_PAGE_COMMENT_COUNT).click();

            String commentPageTitle = articleAndCommentStringDetector(null, COMMENT_PAGE);
            Assertions.assertTrue(commentPageTitle.contains(articleTitle));

            Integer regCommentCount = commentIntegerCountDetector(null, REG_COMMENTS);
            Integer anonCommentCount = commentIntegerCountDetector(null, ANON_COMMENTS);

            Integer sum = regCommentCount + anonCommentCount;

            Assertions.assertEquals(commentCount, sum, "Comments page comment count not equal with home page!");
        }
    }

    @AfterEach
    public void closeDriver(){
        driver.close();
    }
    
    public String articleAndCommentStringDetector (WebElement inputText, By xPath){
        String titleOrCommentText;

        if (inputText != null) {
            if (!inputText.findElements(xPath).isEmpty()) {
                titleOrCommentText = inputText.findElement(xPath).getText();
                LOGGER.info("inputText article present");
            }
            else {
                titleOrCommentText = "(0)";
                LOGGER.info("inputText article does not present");
            }
        }
        else {
            List<WebElement> inputTextList = driver.findElements(xPath);
            if (!inputTextList.isEmpty()) {
                titleOrCommentText = inputTextList.get(0).getText();
                LOGGER.info("inputTextList article present");
            }
            else {
                titleOrCommentText = "(0)";
                LOGGER.info("inputTextList article does not present");
            }
        }

        return titleOrCommentText;
    }

    public Integer commentIntegerCountDetector(WebElement webElement, By xPath){
        String commentText = articleAndCommentStringDetector(webElement, xPath);
        Integer commentCount = getCommentsFromString(commentText);
        return commentCount;
    }

    public Integer getCommentsFromString (String textString) {
        textString = textString.substring(1, textString.length()-1);
        Integer commentsCountNumber = Integer.valueOf(textString);
        return commentsCountNumber;
    }
}


