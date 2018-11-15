package TVNetTestInPageObjects;

import TVNetTestInPageObjects.pages.ArticlePage;
import TVNetTestInPageObjects.pages.BaseFunctions;
import TVNetTestInPageObjects.pages.HomePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

public class TvNetCommentCountCheck {

    private BaseFunctions baseFunctions = new BaseFunctions();
    private final String HOME_PAGE = "https://www.tvnet.lv/";

    @Test
    public void commentCheck() {
        baseFunctions.goToPage(HOME_PAGE);
        HomePage homePage = new HomePage(baseFunctions);

        WebElement testArticle = homePage.getElementById(3);
        Integer commentCount = homePage.getCommentsNumber(testArticle);
        String articleTitle = homePage.getArticleName(testArticle);
        ArticlePage articlePage = homePage.goToArticle(3);



        String articlePageTitle = articlePage.getArticlePageName();
        Assertions.assertTrue(articlePageTitle.contains(articleTitle), "Article page is wrong!");
        articlePage.closeTheBanner();
        Integer articlePageCommentCount = articlePage.getArticlePageCommentsNumber();
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");

    }
}
