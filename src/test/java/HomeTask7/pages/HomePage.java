package HomeTask7.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class HomePage {
    BaseFunc baseFunc;

    private final By DEPARTURE_AIRPORT_DROPDOWN = By.id("afrom");
    private final By DESTINATION_AIRPORT_DROPDOWN = By.id("bfrom");
    private final By GOGOGO_BUTTON = By.className("gogogo");

    public HomePage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void isTHomePageOpened() {
        String pageTitle = baseFunc.pageTitleGet();
        Assertions.assertEquals("RyanBaltic - Title here", pageTitle, "Home page is not opened");
    }

    public void selectDepartureAirport(String departureAirportName) {
        baseFunc.dropDownValueSelect(DEPARTURE_AIRPORT_DROPDOWN, departureAirportName);
    }

    public void selectDestinationAirport(String destinationAirportName) {
        baseFunc.dropDownValueSelect(DESTINATION_AIRPORT_DROPDOWN, destinationAirportName);
    }

    public UserReservationDataPage goToUserReservationDataPage () {
        baseFunc.buttonClick(GOGOGO_BUTTON);
        return new UserReservationDataPage(baseFunc);
    }
}
