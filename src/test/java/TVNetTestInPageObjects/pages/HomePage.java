package TVNetTestInPageObjects.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {

    BaseFunctions baseFunctions;
    private final By ARTICLE = By.className("list-article");
    private final By ARTICLE_TITLE = By.className("list-article__headline");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");

    private static Logger LOGGER = LogManager.getLogger(HomePage.class);

    public HomePage (BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public WebElement getElementById (int id){
        return baseFunctions.getElements(ARTICLE).get(id);
    }

    public String getArticleTitle(WebElement element) {
        ifElementExists(element, ARTICLE_TITLE);
        String articleTitle = element.findElement(ARTICLE_TITLE).getText();
        LOGGER.info("Test article title is: " + articleTitle);
        return articleTitle;
    }

    public Integer getCommentsNumber(WebElement element) {
        ifElementExists(element, COMMENT_COUNT);
        Integer commentsNumber = baseFunctions.getCommentsNumber(element.findElement(COMMENT_COUNT).getText());
        LOGGER.info("Test article comments count is: " + commentsNumber);
        return commentsNumber;
    }

    public void ifElementExists (WebElement element, By xPath){
        Assertions.assertFalse(element.findElements(xPath).isEmpty(), "Element <<<<" + xPath + ">>>> doesn't exist!");
    }

    public ArticlePage goToArticle (WebElement element) {
        element.click();
        return new ArticlePage(baseFunctions);
    }
}
