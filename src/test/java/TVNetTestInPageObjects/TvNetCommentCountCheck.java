package TVNetTestInPageObjects;

import TVNetTestInPageObjects.pages.ArticlePage;
import TVNetTestInPageObjects.pages.BaseFunctions;
import TVNetTestInPageObjects.pages.CommentsPage;
import TVNetTestInPageObjects.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
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

        WebElement testArticle = homePage.getElementById(0);
        Integer commentCount = homePage.getCommentsNumber(testArticle);
        String articleTitle = homePage.getArticleName(testArticle);
        ArticlePage articlePage = homePage.goToArticle(testArticle);

        String articlePageTitle = articlePage.getArticlePageName();
        Assertions.assertTrue(articleTitle.contains(articlePageTitle), "Article page is wrong!");
        articlePage.closeTheBanner();
        Integer articlePageCommentCount = articlePage.getArticlePageCommentsNumber();
        Assertions.assertTrue(commentCount == articlePageCommentCount, "Wrong Article page Comment Count");
        CommentsPage commentsPage = articlePage.goToComments();

        String commentsPageTitle = commentsPage.getCommentsPageName();
        Assertions.assertTrue(articleTitle.contains(commentsPageTitle), "Comments page is wrong!");
        Integer commentPageCommentCount = commentsPage.getCommentsPageCommentsNumber();
        Assertions.assertTrue(commentCount == commentPageCommentCount, "Wrong Comment page Comment Count");

    }

    @AfterEach
    private void driverQuit(){
        baseFunctions.driver.quit();
    }
}
