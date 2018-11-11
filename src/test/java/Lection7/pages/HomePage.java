package Lection7.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class HomePage {
    BaseFunc baseFunc;

    private final By ARTICLE = By.xpath(".//span[@class='text-size-22']");
    private final By COMMENT_COUNT = By.xpath(".//a[contains(@class, 'comment-count')]");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;  //peremennaja urovnja klass = peremennaja na urovne funkcii

    }

    public Integer getCommentCountById(int id) {
        WebElement article = getArticleById(id);
        String commentCount = article.findElement(COMMENT_COUNT).getText();

        commentCount = commentCount.substring(1, commentCount.length() - 1);
        return Integer.valueOf(commentCount);
    }

    public WebElement getArticleById(int id) {
        return baseFunc.getElements(ARTICLE).get(id);
    }

    public ArticlePage goToArticle(int id) {
        getArticleById(id).click();
        return new ArticlePage(baseFunc);
    }
}
