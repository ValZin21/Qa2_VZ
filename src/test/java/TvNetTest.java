import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TvNetTest {
    private final String HOME_PAGE = "https://www.tvnet.lv/";
    private final By ARTICLE = By.xpath(".//*[@class = 'list-article']");
    private final By ARTICLE_TITLE = By.xpath(".//*[@class='list-article__headline']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    private final By ARTICLE_AND_COMMENTS_PAGES_TITLE = By.xpath(".//*[contains(@class, 'headline')]");
    private final By COMMENTS_PAGE_COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//a[contains(@class, 'article-share__item--comments')]");
    private final By LOWER_ADVERTISING = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");
    private final By LOWER_ADV_CLOSE_BUTTON = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");
    private final By BIG_10S_BANNER = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975/www.tvnet.lv/621_0')]");

    private WebDriver browser;
    private WebDriverWait wait;
    private static final Logger LOGGER = LogManager.getLogger(TvNetTest.class);

    private List<WebElement> articles;

    @Test
    public void commentCountTest() {
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        wait = new WebDriverWait(browser, 15);

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


        isElementPresent(ARTICLE_AND_COMMENTS_PAGES_TITLE);
        String articlePageTitle = textGet(ARTICLE_AND_COMMENTS_PAGES_TITLE, null);
        LOGGER.info("Article page title: " + articlePageTitle);
        Assertions.assertTrue(articleTitle.contains(articlePageTitle), "Article page is wrong!");
        LOGGER.info("Article page title check pass");

        closeTheBanner();

        Integer articlePageCommentCount = getArticlePageCommentsCount();
        LOGGER.info("Article page comments count: " + articlePageCommentCount);
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");
        LOGGER.info("Atricle page comments count check pass");

        articlePageClick();


        isElementPresent(ARTICLE_AND_COMMENTS_PAGES_TITLE);
        String commentsPageTitle = textGet(ARTICLE_AND_COMMENTS_PAGES_TITLE, null);
        Assertions.assertTrue(articleTitle.contains(commentsPageTitle), "Comments page is wrong!");
        LOGGER.info("Comments page title check pass");

        Integer commentPageCommentCount = getCommentsNumber(textGet(COMMENTS_PAGE_COMENT_COUNT, null));
        LOGGER.info("Comments page comment count: " + commentPageCommentCount);
        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Comment page Comment Count");
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

    private void closeTheBanner() {
        isElementInvisible(BIG_10S_BANNER);
        isElementPresent(LOWER_ADVERTISING);
        browser.findElement(LOWER_ADV_CLOSE_BUTTON).click();
        LOGGER.info("Lower advertising closed");
    }

    private Integer getArticlePageCommentsCount() {
        return Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getAttribute("data-content"));
    }

    private void isElementPresent(By xPath) {
        wait.until(ExpectedConditions.presenceOfElementLocated(xPath));
    }

    private void isElementInvisible(By xPath) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(xPath));
    }

    private void articlePageClick() {
        wait.until(ExpectedConditions.elementToBeClickable(ARTICLE_PAGE_COMENT_COUNT));
        browser.findElement(ARTICLE_PAGE_COMENT_COUNT).click();
    }
}
