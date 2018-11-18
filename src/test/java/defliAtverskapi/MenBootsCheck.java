package defliAtverskapi;

import defliAtverskapi.pages.BaseFunctions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class MenBootsCheck {

    private BaseFunctions baseFunctions = new BaseFunctions();
    private static final String HOME_PAGE = "http://atverskapi.delfi.lv/ru/style";

    @Test
    public void new5BlackBootsCheck() {
        baseFunctions.goToPage(HOME_PAGE);



    }

    @AfterEach
    private void drvierQuit(){
        baseFunctions.driver.quit();
    }
}
