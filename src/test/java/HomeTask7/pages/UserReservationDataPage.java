package HomeTask7.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class UserReservationDataPage {
    BaseFunc baseFunc;

    private final By CHECK_DESTINATION_MESSAGE = By.className("infoTxt");

    public UserReservationDataPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void isUserReservationPageOpened() {
        baseFunc.isElementPresent(CHECK_DESTINATION_MESSAGE);
    }

    public void areTheAirportsCorrect(String departure, String destination) {
        Assertions.assertTrue(baseFunc.textGet(CHECK_DESTINATION_MESSAGE).contains("is " + departure), "Departure airport is wrong!");
        Assertions.assertTrue(baseFunc.textGet(CHECK_DESTINATION_MESSAGE).contains(" to " + destination), "Destination airport is wrong!");
    }

}
