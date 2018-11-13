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
    private WebDriver browser;

    //    private final By ARTICLE = By.xpath(".//*[@itemprop = 'url']");
    private final By ARTICLE = By.xpath(".//*[@class = 'list-article']");
    private final By ARTICLE_TITLE = By.xpath(".//*[@class='list-article__headline']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    private final By ARTICLE_AND_COMMENTS_PAGES_TITLE = By.xpath(".//*[contains(@class, 'headline')]");
    //    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//span[contains(@class, 'heading__count')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By COMMENTS_BTN = By.xpath(".//a[contains(@class, 'button--comment')]");
    //    //private final By COMMENTS_BTN = By.xpath(".//div[contains(@class, 'comments-link')]/a[contains(@href, 'comments')]");
//    private final By COMMENTS_BTN = By.xpath(".//div[contains(@class, 'comments-link')]/a");
    public List<WebElement> articles;

    private static final Logger LOGGER = LogManager.getLogger(TvNetTest.class);

    @Test
    public void commentCountTest() {
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.23.0-win64\\geckodriver.exe");
//        browser = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(browser, 15);
        browser.manage().window().maximize();
        browser.get(HOME_PAGE);

        articles = browser.findElements(ARTICLE);
        Assertions.assertFalse(articles.isEmpty(), "No articles detected!");
        WebElement testArticle = articles.get(2);//3
       // WebElement commentCountPresenseCheck = articles.get(articleSelector);
        Assertions.assertFalse(testArticle.findElements(COMMENT_COUNT).isEmpty(), "No comments detected!");
        Integer commentCount = Integer.valueOf(testArticle.findElement(COMMENT_COUNT).getText());
        LOGGER.info(commentCount);

        String articleTitle = testArticle.findElement(ARTICLE_TITLE).getText();
        LOGGER.info("Article title: " + articleTitle);
        LOGGER.info("testArticle: " + testArticle);
        testArticle.click();
//        articles.get(3).click();

//        new Actions(browser).moveToElement(browser.findElement(ARTICLE_PAGE_COMENT_COUNT);

//        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        JavascriptExecutor jse = (JavascriptExecutor)browser;
//        jse.executeScript("window.scrollBy(1500,0)", "");
//        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", browser.findElement(COMMENTS_BTN));

        //Article page

        String articlePageTitle = browser.findElement(ARTICLE_AND_COMMENTS_PAGES_TITLE).getText();
        LOGGER.info("Article page title: " + articlePageTitle);
        Assertions.assertTrue(articlePageTitle.contains(articleTitle), "Article page is wrong!");
        LOGGER.info("Article page title check pass");

//        new Actions(browser).moveToElement(browser.findElement(By.xpath(".//h1[@class='block-title section-font-color']/a"))).perform();
        new Actions(browser).moveToElement(browser.findElement(By.xpath(".//*[@class = 'article-terms']"))).perform();
//        new Actions(browser).moveToElement(browser.findElement(By.xpath(".//*[contains(@class, 'comments-block__heading')]"))).perform();
//        new Actions(browser).moveToElement(browser.findElement(ARTICLE_PAGE_COMENT_COUNT)).click().perform();

//        browser.switchTo().frame("google_ads_iframe_/84367975/www.tvnet.lv/59_0");
//        wait.until(ExpectedConditions.elementToBeSelected(By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]")));
      //  wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("google_ads_iframe_/84367975/www.tvnet.lv/59_0__container__"));
//        browser.switchTo().frame("google_ads_iframe_/84367975/www.tvnet.lv/59_0__container__");
       // browser.switchTo().activeElement();
//        browser.switchTo().frame(browser.findElement(By.xpath(".//*[@id = 'google_ads_iframe_/84367975/www.tvnet.lv/59_0']")));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]")));
        browser.findElement(By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]")).click();
//        browser.switchTo().defaultContent();
            //    .//*[@id = 'google_ads_iframe_/84367975/www.tvnet.lv/59_0']/div


        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_PAGE_COMENT_COUNT));

//        String a = browser.findElement(By.xpath(".//h1[@class='article-headline']")).getText();
//        System.out.println(a);



        Integer articlePageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());
        LOGGER.info("Article page comments count: " + articlePageCommentCount);
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");
        LOGGER.info("Comments page title check pass");

        wait.until(ExpectedConditions.presenceOfElementLocated(COMMENTS_BTN));
        new Actions(browser).moveToElement(browser.findElement(COMMENTS_BTN)).perform();
        browser.findElement(COMMENTS_BTN).click();


        //Comments page

        String commentsPageTitle = browser.findElement(ARTICLE_AND_COMMENTS_PAGES_TITLE).getText();
        Assertions.assertTrue(commentsPageTitle.contains(articlePageTitle), "Comments page is wrong!");

        LOGGER.info("I made it!");
        Integer commentPageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());
        LOGGER.info("Comments page comment count: " + commentPageCommentCount);
        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Article page Comment Count");
    }

    @AfterEach
    private void driverClose(){
        browser.quit();
    }
}
