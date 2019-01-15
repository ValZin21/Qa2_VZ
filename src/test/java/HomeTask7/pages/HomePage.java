package HomeTask7.pages;

import apiSteps.model.Sys;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

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
//        System.out.println(pageTitle);
        Assertions.assertEquals("RyanBaltic - Title here", pageTitle, "Home page is not opened");
    }

    public void selectDepartureAirport(String departureAirportName) {
        dropDownValueSelect(DEPARTURE_AIRPORT_DROPDOWN, departureAirportName);
    }

    public void selectDestinationAirport(String destinationAirportName) {
        dropDownValueSelect(DESTINATION_AIRPORT_DROPDOWN, destinationAirportName);
    }

    private void dropDownValueSelect(By locator, String airport) {
        Select dropDown = new Select (baseFunc.getElement(locator));
        List<WebElement> list = dropDown.getOptions();

        String key = "a";
        for (int i  = 0; i < list.size(); i++) {
            if (list.get(i).getText().equals(airport)) {
                key = list.get(i).getText();
                break;
            }
        }

        Assertions.assertTrue(airport.equals(key), "No such airport");
        dropDown.selectByValue(airport);
    }

    public UserReservationDataPage goToUserReservationDataPage () {
        baseFunc.getElement(GOGOGO_BUTTON).click();
        return new UserReservationDataPage(baseFunc);
    }
}
