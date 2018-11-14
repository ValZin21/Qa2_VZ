import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.List;
import java.util.concurrent.TimeUnit;

public class TvNetTest {
    private final String HOME_PAGE = "https://www.tvnet.lv/";
    private final By ARTICLE = By.xpath(".//*[@class = 'list-article']");
    private final By ARTICLE_TITLE = By.xpath(".//*[@class='list-article__headline']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    private final By ARTICLE_AND_COMMENTS_PAGES_TITLE = By.xpath(".//*[contains(@class, 'headline')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By COMMENTS_BTN = By.xpath(".//a[contains(@class, 'button--comment')]");
    private final By LOWER_ADVERTISING = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");
    private final By LOWER_ADV_CLOSE_BUTTON = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");
    private final By COMMENTS_SECTION = By.xpath(".//*[@class = 'article-terms']");

    private WebDriver browser;
    private WebDriverWait wait;
    private Actions manipulator;
    private static final Logger LOGGER = LogManager.getLogger(TvNetTest.class);

    private List<WebElement> articles;

    @Test
    public void commentCountTest() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        wait = new WebDriverWait(browser, 15);
        manipulator = new Actions(browser);

        browser.manage().window().maximize();
        browser.get(HOME_PAGE);

        articles = browser.findElements(ARTICLE);
        Assertions.assertFalse(articles.isEmpty(), "No articles detected!");
        WebElement testArticle = articles.get(3);

        Assertions.assertFalse(testArticle.findElements(COMMENT_COUNT).isEmpty(), "No comments detected!");

        Integer commentCount = getCommentsNumber(textGet(COMMENT_COUNT, testArticle));
        LOGGER.info("Comment count on home page: " + commentCount);
        String articleTitle = textGet(ARTICLE_TITLE, testArticle);
        LOGGER.info("Article title: " + articleTitle);
        testArticle.click();


        String articlePageTitle = textGet(ARTICLE_AND_COMMENTS_PAGES_TITLE, null);
        LOGGER.info("Article page title: " + articlePageTitle);
        Assertions.assertTrue(articlePageTitle.contains(articleTitle), "Article page is wrong!");
        LOGGER.info("Article page title check pass");

        scroolToCommetns();

        closeTheBanner();

        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_PAGE_COMENT_COUNT));

        Integer articlePageCommentCount = getCommentsNumber(textGet(ARTICLE_PAGE_COMENT_COUNT, null));
        LOGGER.info("Article page comments count: " + articlePageCommentCount);
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");
        LOGGER.info("Atricle page comments count check pass");

        wait.until(ExpectedConditions.presenceOfElementLocated(COMMENTS_BTN));
        browser.findElement(COMMENTS_BTN).click();


        String commentsPageTitle = textGet(ARTICLE_AND_COMMENTS_PAGES_TITLE, null);
        Assertions.assertTrue(commentsPageTitle.contains(articlePageTitle), "Comments page is wrong!");
        LOGGER.info("Comments page title check pass");

        Integer commentPageCommentCount = getCommentsNumber(textGet(ARTICLE_PAGE_COMENT_COUNT, null));
        LOGGER.info("Comments page comment count: " + commentPageCommentCount);
        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Article page Comment Count");
        LOGGER.info("Comments page comments count check pass");
    }

    @AfterEach
    private void driverClose(){
        browser.quit();
    }

    private String textGet (By xPath, WebElement elementWeb) {
        if (elementWeb == null){
            return browser.findElement(xPath).getText();
        }
        else {
            return elementWeb.findElement(xPath).getText();
        }
    }

    private Integer getCommentsNumber (String text) {
        return Integer.valueOf(text);
    }

    private void scroolToCommetns() {
        manipulator.moveToElement(browser.findElement(COMMENTS_SECTION)).perform();
        LOGGER.info("Page scrolled to comment count");
    }

    private void closeTheBanner() {
        wait.until(ExpectedConditions.presenceOfElementLocated(LOWER_ADVERTISING));
        browser.findElement(LOWER_ADV_CLOSE_BUTTON).click();
        LOGGER.info("Lower advertising closed");
    }
}
