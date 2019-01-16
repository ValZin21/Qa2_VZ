package HomeTask7.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

public class UserReservationDataPage {
    BaseFunc baseFunc;

    private final By CHECK_DESTINATION_MESSAGE = By.className("infoTxt");
    private final By NAME_INPUT = By.id("name");
    private final By SURNAME_INPUT = By.id("surname");
    private final By DISCOUNT_INPUT = By.id("discount");
    private final By TRAVELLER_COUNT_INPUT = By.id("adults");
    private final By CHILDREN_COUNT_INPUT = By.id("children");
    private final By LUGGAGE_COUNT_INPUT = By.id("bugs");
    private final By FLIGHT_DATE = By.id("flight");
    private final By GET_PRICE_BUTTON = By.xpath(".//span[text() = 'Get Price']");
    private final By RESERVATION_PRICE_TEXT = By.id("response");
    private final By BOOK_BUTTON = By.id("book2");

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

    public void sendName(String name) {
        baseFunc.keysSend(NAME_INPUT, name);
    }

    public void sendSurname(String surname) {
        baseFunc.keysSend(SURNAME_INPUT, surname);
    }

    public void sendDiscount(String discount) {
        baseFunc.keysSend(DISCOUNT_INPUT, discount);
    }

    public void sendTravellerCount(String travellerCount) {
        baseFunc.keysSend(TRAVELLER_COUNT_INPUT, travellerCount);
    }

    public void sendChildrenCount(String clildrenCount) {
        baseFunc.keysSend(CHILDREN_COUNT_INPUT, clildrenCount);
    }

    public void sendLuggageCount(String luggageCount) {
        baseFunc.keysSend(LUGGAGE_COUNT_INPUT, luggageCount);
    }

    public void sendFlightDate(String flightDate) {
        baseFunc.dropDownValueSelect(FLIGHT_DATE, flightDate);
    }

    public void getPriceButtonClick() {
        baseFunc.buttonClick(GET_PRICE_BUTTON);
    }

    public void reservationPriceCheck() {  //Thin about hardcoded 3070 EUR
        baseFunc.isElementVisible(RESERVATION_PRICE_TEXT);
        Assertions.assertTrue(baseFunc.textGet(RESERVATION_PRICE_TEXT).contains("3070 EUR"), "Reservation price is wrong!");
    }

    public SeatSelectionPage goToSeatSelectionPage () {
        baseFunc.buttonClick(BOOK_BUTTON);
        return new SeatSelectionPage(baseFunc);
    }

}
