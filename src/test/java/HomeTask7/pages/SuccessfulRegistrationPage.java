package HomeTask7.pages;

import org.openqa.selenium.By;

public class SuccessfulRegistrationPage {
    BaseFunc baseFunc;

    private final By GRATITTUDE_MESSAGE = By.xpath(".//*[@class = 'finalTxt'][text() = 'Thank You for flying with us!']");

    SuccessfulRegistrationPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void isSuccessfulRegistrationPageOpened() {
        baseFunc.isElementPresent(GRATITTUDE_MESSAGE);
    }
}
