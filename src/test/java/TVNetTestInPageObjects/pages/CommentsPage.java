package TVNetTestInPageObjects.pages;

import org.openqa.selenium.By;

public class CommentsPage {

    BaseFunctions baseFunctions;

    private final By COMENT_COUNT = By.xpath(".//div/span[contains(@class, 'article-comments-heading__count')]");
    private final By PAGE_TITLE = By.xpath(".//*[contains(@class, 'headline')]");

    public CommentsPage(BaseFunctions baseFunctions) {
        this.baseFunctions =  baseFunctions;
    }

    public Integer getCommentsPageCommentsNumber() {
        baseFunctions.isElementPresent(COMENT_COUNT);
        return baseFunctions.getCommentsNumber(baseFunctions.getText(COMENT_COUNT));
    }

    public String getCommentsPageName() {
        baseFunctions.isElementPresent(PAGE_TITLE);
        return baseFunctions.getText(PAGE_TITLE);
    }
}
