package TVNetTestInPageObjects.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    BaseFunctions baseFunctions;
    private final By ARTICLE = By.xpath(".//*[@class = 'list-article']");
    private final By ARTICLE_TITLE = By.xpath(".//*[@class='list-article__headline']");
    private final By COMMENT_COUNT = By.xpath(".//span[contains(@class, 'list-article__comment')]");

    public HomePage (BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public WebElement getElementById (int id){
        return baseFunctions.getElements(ARTICLE).get(id);
    }

    public String getArticleName(WebElement element) {
        ifElementExists(element, ARTICLE_TITLE);
        return element.findElement(ARTICLE_TITLE).getText();
    }

    public Integer getCommentsNumber(WebElement element) {
        ifElementExists(element, COMMENT_COUNT);
        return baseFunctions.getCommentsNumber(element.findElement(COMMENT_COUNT).getText());
    }

    public void ifElementExists (WebElement element, By xPath){
        Assertions.assertFalse(element.findElements(xPath).isEmpty(), "Element doesn't exist!");
    }

    public ArticlePage goToArticle (int id) {
        getElementById(id).click();
        return new ArticlePage(baseFunctions);
    }
}
