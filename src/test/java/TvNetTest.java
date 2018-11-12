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
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");
    //    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//span[contains(@class, 'heading__count')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By COMMENTS_BTN = By.xpath(".//a[contains(@class, 'button--comment')]");
    //    //private final By COMMENTS_BTN = By.xpath(".//div[contains(@class, 'comments-link')]/a[contains(@href, 'comments')]");
//    private final By COMMENTS_BTN = By.xpath(".//div[contains(@class, 'comments-link')]/a");
    public List<WebElement> articles;

    @Test
    public void commentCountTest() {
//        System.setProperty("webdriver.gecko.driver", "C:\\geckodriver-v0.23.0-win64\\geckodriver.exe");
//        browser = new FirefoxDriver();
        System.setProperty("webdriver.chrome.driver", "C:/chromedriver_win32/chromedriver.exe");
        browser = new ChromeDriver();
        browser.manage().window().maximize();
        browser.get(HOME_PAGE);



        articles = browser.findElements(ARTICLE);
        Integer commentCount = Integer.valueOf(articles.get(1).findElement(COMMENT_COUNT).getText());
        System.out.println(commentCount);
        getElementById(1).click();
//        articles.get(3).click();

//        new Actions(browser).moveToElement(browser.findElement(ARTICLE_PAGE_COMENT_COUNT);

//        browser.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

//        JavascriptExecutor jse = (JavascriptExecutor)browser;
//        jse.executeScript("window.scrollBy(1500,0)", "");
//        ((JavascriptExecutor) browser).executeScript("arguments[0].scrollIntoView(true);", browser.findElement(COMMENTS_BTN));



        new Actions(browser).moveToElement(browser.findElement(By.xpath(".//h1[@class='block-title section-font-color']/a"))).perform();
//        new Actions(browser).moveToElement(browser.findElement(By.xpath(".//*[contains(@class, 'comments-block__heading')]"))).perform();
//        new Actions(browser).moveToElement(browser.findElement(ARTICLE_PAGE_COMENT_COUNT)).click().perform();

        WebDriverWait wait = new WebDriverWait(browser, 30);
        wait.until(ExpectedConditions.presenceOfElementLocated(ARTICLE_PAGE_COMENT_COUNT));

//        String a = browser.findElement(By.xpath(".//h1[@class='article-headline']")).getText();
//        System.out.println(a);



        Integer articlePageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());
        System.out.println("Wow, hey! " + articlePageCommentCount);
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");

        wait.until(ExpectedConditions.presenceOfElementLocated(COMMENTS_BTN));
        new Actions(browser).moveToElement(browser.findElement(COMMENTS_BTN)).perform();
        browser.findElement(COMMENTS_BTN).click();
        System.out.println("I made it!");
        Integer commentPageCommentCount = Integer.valueOf(browser.findElement(ARTICLE_PAGE_COMENT_COUNT).getText());
        System.out.println("Comments page comment count: " + commentPageCommentCount);
        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Article page Comment Count");
    }

    private WebElement getElementById(int id) {
//        List<WebElement> articles = browser.findElements(ARTICLE);

        for (int i = 0; i < articles.size(); i++) {
            WebElement element = articles.get(i);
            if (i == id) {
                return element;
            }
        }

        return null;
    }

    @AfterEach
    private void driverClose(){
        browser.close();
        browser.quit();
    }
}
