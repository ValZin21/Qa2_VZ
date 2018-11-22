package defliAtverskapi;

import defliAtverskapi.pages.ApaviPage;
import defliAtverskapi.pages.BaseFunctions;
import defliAtverskapi.pages.HomePage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class MenBootsCheck {

    private BaseFunctions baseFunctions = new BaseFunctions();
    private static final String HOME_PAGE = "http://atverskapi.delfi.lv/lv/style";

    @Test
    public void new5BlackBootsCheck() {
        baseFunctions.goToPage(HOME_PAGE);

        String homePageTitle = baseFunctions.titleGet();
        HomePage homePage = new HomePage(baseFunctions);
        homePage.findAndOpenVirieshiDropDownMenu();
        ApaviPage apaviPage = homePage.goToApaviPage(homePage.findApaviProduct());

        String apaviPageTitle = baseFunctions.titleGet();
        Assertions.assertFalse(homePageTitle.equals(apaviPageTitle), "Pages not switched!");
        apaviPage.selectKurpes();
        apaviPage.selectBlackColor();
        apaviPage.selectNewState();
        apaviPage.checkFiveProducts();
    }

    @AfterEach
    private void drvierQuit(){
        baseFunctions.driver.quit();
    }
}