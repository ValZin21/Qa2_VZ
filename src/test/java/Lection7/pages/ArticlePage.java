package Lection7.pages;

import org.openqa.selenium.By;

public class ArticlePage {

    BaseFunc baseFunc;

    private final By COMMENT_COUNT = By.xpath(".//a[contains(@class, 'text-size-19')]");

    public ArticlePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;  //peremennaja urovnja klass = peremennaja na urovne funkcii
    }

    public Integer getCommentCount() {
        String comments = baseFunc.getElement(COMMENT_COUNT).getText();
        comments = comments.substring(1, comments.length() - 1);
        return Integer.valueOf(comments);
    }

}
