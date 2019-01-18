package ticketReservationCheckFeBe.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SeatSelectionPage {
    BaseFunc baseFunc;
    private List<WebElement> seatNumbers = new ArrayList<WebElement>();

    private final By YOUR_CRAFT_TEXT = By.xpath(".//*[@id = 'book']/div[text() = 'Your craft is Boeing 737']");
    private final By SEAT_NUMBERS = By.xpath(".//*[contains(@class, 'seat')]");
    private final By SELECTED_SEAT_NUMBER_TEXT = By.xpath(".//*[@id = 'book']/div[contains(text(), 'Your seat is:')]");
    private final By BOOK_BUTTON = By.id("book3");

    public SeatSelectionPage(BaseFunc baseFunc) {
        this.baseFunc = baseFunc;
    }

    public void isSeatSelectionPageOpened() {
        baseFunc.isElementVisible(YOUR_CRAFT_TEXT);
    }

    private List<WebElement> seatNumbers(By locator) {
        return baseFunc.getElements(locator);
    }

//    public void randomSeatSelection() {
//        seatNumbers = seatNumbers(SEAT_NUMBERS);
//        Random randomSeat = new Random();
//        int seatNumber = randomSeat.nextInt(seatNumbers.size()-1) + 1;
//        seatNumbers.get(seatNumber).click();
//    }

    public void predefinedSeatSelection(int seatNumber) {
        seatNumbers = seatNumbers(SEAT_NUMBERS);
        boolean isSuchSeatPresent = false;
        for (int i = 0; i < seatNumbers.size(); i++) {
            if (Integer.valueOf(seatNumbers.get(i).getText()).equals(seatNumber)) {
                seatNumbers.get(i).click();
                isSuchSeatPresent = true;
                break;
            }
        }
        Assertions.assertTrue(isSuchSeatPresent, "No such seat: " + seatNumber);
    }

    private Integer getSeatNumberFromString (String textString) {
        textString = textString.substring(14, textString.length());
        System.out.println("result is: " + textString);
        Integer commentsCountNumber = Integer.valueOf(textString);
        return commentsCountNumber;
    }

    public void checkReservedSeatNumber(Integer number) {
        baseFunc.isElementVisible(SELECTED_SEAT_NUMBER_TEXT);
        String text = baseFunc.textGet(SELECTED_SEAT_NUMBER_TEXT);
        Assertions.assertEquals(number, getSeatNumberFromString(text), "Reserved seat is wrong!");
    }

    public SuccessfulRegistrationPage goToSuccessfulRegistrationPage () {
        baseFunc.buttonClick(BOOK_BUTTON);
        return new SuccessfulRegistrationPage(baseFunc);
    }
}
