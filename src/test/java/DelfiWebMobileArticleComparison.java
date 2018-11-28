import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.lang.String;

public class DelfiWebMobileArticleComparison {

    private final String webHomePage= "http://rus.delfi.lv";
    private final String mobileHomePage = "http://m.rus.delfi.lv";
    private final By WEB_ARTICLE_SCOUT = By.xpath(".//h1[contains(@class, 'headline__title')]");
    private final By MOBILE_ARTICLE_SCOUT = By.xpath("(.//a[@class='md-scrollpos'])");
    private final int firstMobileArticleText = 5;
    private final int firstWebArticleText = 0;
    private final String webArticleTextsCheckMessageText = ": Web Article ";
    private final String mobileArticleTextsCheckMessageText = ": Mobile Article ";

    private List<String> articleTexts = new ArrayList<String>();
    private List<WebElement> articles = new ArrayList<WebElement>();

    private WebDriver browserOpener;

    private static Logger LOGGER = LogManager.getLogger(DelfiWebMobileArticleComparison.class);

    @Test
    public void webMobileArticlesComparator(){
        System.setProperty("webdriver.chrome.driver","C://chromedriver_win32/chromedriver.exe");
        browserOpener = new ChromeDriver();
        browserOpener.manage().window().maximize();

        browserOpener.get(webHomePage);
        articles=browserOpener.findElements(WEB_ARTICLE_SCOUT);
        isArticleArrayEmpty();
        articleTextsCollector(webArticleTextsCheckMessageText, firstWebArticleText);
        articles.clear();

        browserOpener.get(mobileHomePage);
        articles=browserOpener.findElements(MOBILE_ARTICLE_SCOUT);
        isArticleArrayEmpty();
        articleTextsCollector(mobileArticleTextsCheckMessageText, firstMobileArticleText);

        checkEqualRows();
    }

    private void articleTextsCollector(String outputText, int firstTextElement){
        for (int i = 0; i < 5; i++) {
            articleTexts.add(articles.get(i).getText());
            LOGGER.info("AC Element " +(i+firstTextElement)+ outputText + (i+1) + " = " + articleTexts.get(i));
        }
    }

    private void checkEqualRows(){
        for (int i = 0; i < 5; i++) {
            Assertions.assertEquals(articleTexts.get(i), articleTexts.get(i + 5), "Article mismatch detected!");
        }
    }

    private void isArticleArrayEmpty () {
        Assertions.assertFalse(articles.isEmpty());
    }

    @AfterEach
    private void driverClose() {
        browserOpener.quit();
    }
}
