package Lection7;

import Lection7.pages.ArticlePage;
import Lection7.pages.BaseFunc;
import Lection7.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DelfiCommentTest {
    private BaseFunc baseFunc = new BaseFunc(); //creation of baseFunc Object copy to use BaseFunc class functionality
    private final String HOME_PAGE = "http://rus.delfi.lv";

    @Test
    public void commentCheck() {
        baseFunc.goToPage(HOME_PAGE);
        HomePage homePage = new HomePage(baseFunc); //rabotajes s home page s onom brauzera iz baseFunc
        Integer commentCount = homePage.getCommentCountById(1);
        ArticlePage articlePage = homePage.goToArticle(1);


        Integer articlePageComments = articlePage.getCommentCount();
        Assertions.assertEquals(commentCount, articlePageComments, "Wrong comments!");
    }
}
