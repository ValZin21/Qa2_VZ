package TVNetTestInPageObjects.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class CommentsPage {

    BaseFunctions baseFunctions;

    private final By COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By PAGE_TITLE = By.xpath(".//*[contains(@class, 'headline')]");

    private static Logger LOGGER = LogManager.getLogger(CommentsPage.class);

    public CommentsPage(BaseFunctions baseFunctions) {
        this.baseFunctions =  baseFunctions;
    }

    public Integer getCommentsPageCommentsNumber() {
        baseFunctions.isElementPresent(COMENT_COUNT);
        Integer commentsPageCommentsNumber = baseFunctions.getCommentsNumber(baseFunctions.getText(COMENT_COUNT));
        LOGGER.info("Comments page comments count is: " + commentsPageCommentsNumber);
        return commentsPageCommentsNumber;
    }

    public String getCommentsPageTitle() {
        baseFunctions.isElementPresent(PAGE_TITLE);
        String commentsPageTitle = baseFunctions.getText(PAGE_TITLE);
        LOGGER.info("Comments page title is: " + commentsPageTitle);
        return commentsPageTitle;
    }
}
