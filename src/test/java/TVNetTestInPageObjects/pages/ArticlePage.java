package TVNetTestInPageObjects.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class ArticlePage {

    BaseFunctions baseFunctions;
    private static final Logger LOGGER = LogManager.getLogger(ArticlePage.class);
    private final By ARTICLE_AND_COMMENTS_PAGES_TITLE = By.xpath(".//*[contains(@class, 'headline')]");
    private final By ARTICLE_PAGE_COMENT_COUNT = By.xpath(".//a[contains(@class, 'article-share__item--comments')]");
    private final By BIG_10S_BANNER = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975/www.tvnet.lv/621_0')]");
    private final By LOWER_ADVERTISING = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");
    private final By LOWER_ADV_CLOSE_BUTTON = By.xpath(".//*[contains(@id, 'google_ads_iframe_/84367975')]/div[contains(@style, 'position')]");

    public ArticlePage(BaseFunctions baseFunctions) {
        this.baseFunctions = baseFunctions;
    }

    public String getArticlePageName() {
        baseFunctions.isElementPresent(ARTICLE_AND_COMMENTS_PAGES_TITLE);
        return baseFunctions.getText(ARTICLE_AND_COMMENTS_PAGES_TITLE);
    }

    public Integer getArticlePageCommentsNumber() {
        baseFunctions.isElementPresent(ARTICLE_PAGE_COMENT_COUNT);
        return baseFunctions.getCommentsNumber(baseFunctions.getElement(ARTICLE_PAGE_COMENT_COUNT).getAttribute("data-content"));
    }

    public void closeTheBanner() {
        baseFunctions.isElementInvisible(BIG_10S_BANNER);
        LOGGER.info("Big banner vanished");
        baseFunctions.isElementPresent(LOWER_ADVERTISING);
        LOGGER.info("Lower advertising banner exists");
        baseFunctions.isElementClickable(LOWER_ADV_CLOSE_BUTTON);
        LOGGER.info("Lower advertising banner close button available");
        baseFunctions.getElement(LOWER_ADV_CLOSE_BUTTON).click();
        LOGGER.info("Lower advertising closed");
    }

    public CommentsPage goToComments () {
        baseFunctions.isElementClickable(ARTICLE_PAGE_COMENT_COUNT);
        baseFunctions.getElement(ARTICLE_PAGE_COMENT_COUNT).click();
        return new CommentsPage(baseFunctions);
    }
}
