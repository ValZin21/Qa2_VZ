package ticketReservationCheckFeBe;

import ticketReservationCheckFeBe.model.ReservationResponse;
import ticketReservationCheckFeBe.pages.*;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Map;

public class TicketReservatioStepDefs {
    private String departureAirport;
    private String destinationAirport;
    private UserData userData = new UserData();
    private int seatNumber;

    private BaseFunc baseFunc = new BaseFunc();
    private final String HOME_PAGE = "http://qaguru.lv:8090/tickets/";
    private HomePage homePage;
    private UserReservationDataPage userReservationDataPage;
    private SeatSelectionPage seatSelectionPage;
    private SuccessfulRegistrationPage successfulRegistrationPage;

    private ReservationRequester reservationRequester = new ReservationRequester();
    private ReservationResponse reservationResponse = new ReservationResponse();
    private int idToDelete;

    @Given("Deparutre airport: (.*)")
    public void set_departure_airport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    @Given("Destination airport: (.*)")
    public void set_destination_airport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    @Given("User data is:")
    public void set_user_data(Map<String, String> params) {

        userData.setName(params.get("name"));
        userData.setSurname(params.get("surname"));
        userData.setDiscountCode(params.get("discountCode"));
        userData.setTravellerCount(Integer.valueOf(params.get("travellerCount")));
        userData.setChildrenCount(Integer.valueOf(params.get("childrenCount")));
        userData.setLuggageCount(Integer.valueOf(params.get("luggageCount")));
        userData.setNextFlight(params.get("nextFlight"));
    }

    @Given("seatNumber is: (.*)")
    public void set_seat_number(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Given("we are on the home page")
    public void set_home_page() {
        baseFunc.goToPage(HOME_PAGE);
        homePage = new HomePage(baseFunc);
        homePage.isTHomePageOpened();
    }

    @When("we are selecting airports")
    public void set_airports() {
        homePage.selectDepartureAirport(departureAirport);
        homePage.selectDestinationAirport(destinationAirport);
    }

    @When("pressing on the GOGOGO button")
    public void click_gogogo_button() {
        userReservationDataPage = homePage.goToUserReservationDataPage();
    }

    @Then("registration page appears")
    public void get_registration_page() {
        userReservationDataPage.isUserReservationPageOpened();
        userReservationDataPage.areTheAirportsCorrect(departureAirport, destinationAirport);
    }

    @When("we are filling the registration form")
    public void fill_registration_form() {
        userReservationDataPage.sendName(userData.getName());
        userReservationDataPage.sendSurname(userData.getSurname());
        userReservationDataPage.sendDiscount(userData.getDiscountCode());
        userReservationDataPage.sendTravellerCount(String.valueOf(userData.getTravellerCount()));
        userReservationDataPage.sendChildrenCount(String.valueOf(userData.getChildrenCount()));
        userReservationDataPage.sendLuggageCount(String.valueOf(userData.getLuggageCount()));
        userReservationDataPage.sendFlightDate(userData.getNextFlight());
    }

    @When("we are pressing on the Get Price button")
    public void click_get_price_button() {
        userReservationDataPage.getPriceButtonClick();
    }

    @Then("our price will be 3070 EUR")
    public void check_reservation_price() {
        userReservationDataPage.reservationPriceCheck();
    }

    @When("we are pressing on the Book! button")
    public void press_book_button() {
        seatSelectionPage = userReservationDataPage.goToSeatSelectionPage();
    }

    @Then("we can choose the seat")
    public void get_choose_seat() {
        seatSelectionPage.isSeatSelectionPageOpened();
    }

    @When("we are selecting our seat number: predefined")
    public void get_predefined_seat_number() {
        seatSelectionPage.predefinedSeatSelection(seatNumber);
        seatSelectionPage.checkReservedSeatNumber(seatNumber);
    }

//    @When("we are selecting our seat number: random")
//    public void get_random_seat_number() {
//        seatSelectionPage.randomSeatSelection();
//    }

    @When("we are clicking Book! button")
    public void click_book_button() {
        successfulRegistrationPage = seatSelectionPage.goToSuccessfulRegistrationPage();
    }

    @Then("we are receiving successful registration page")
    public void get_successfull_registration_page() {
        successfulRegistrationPage.isSuccessfulRegistrationPageOpened();
    }

    @When("we are requesting reservation list")
    public void get_reservation_list() throws IOException {
        reservationResponse = reservationRequester.getReservationList();
        Assertions.assertTrue(reservationResponse.getReservations().size() > 0, "No reservations list!");
    }

    @Then("we can see our reservation in the list")
    public void check_reservation_list() {
        boolean isReservationPresent = false;

        for (int i = 0; i < reservationResponse.getReservations().size(); i++) {
            if (reservationResponse.getReservations().get(i).getSurname().equals(userData.getSurname()) &&
                    reservationResponse.getReservations().get(i).getName().equals(userData.getName()) &&
                    reservationResponse.getReservations().get(i).getDiscount().equals(userData.getDiscountCode()) &&
                    reservationResponse.getReservations().get(i).getAfrom().equals(departureAirport) &&
                    reservationResponse.getReservations().get(i).getAto().equals(destinationAirport)
            ) {
                isReservationPresent = true;
                break;
            }
        }

        Assertions.assertTrue(isReservationPresent, "Reservation wasn't added!");
    }

    @When("we are deleting our reservation tikcet")
    public void delete_reservation() {

        for (int i = 0; i < reservationResponse.getReservations().size(); i++) {
            if (reservationResponse.getReservations().get(i).getSurname().equals(userData.getSurname()) &&
                    reservationResponse.getReservations().get(i).getName().equals(userData.getName()) &&
                    reservationResponse.getReservations().get(i).getDiscount().equals(userData.getDiscountCode()) &&
                    reservationResponse.getReservations().get(i).getAfrom().equals(departureAirport) &&
                    reservationResponse.getReservations().get(i).getAto().equals(destinationAirport)
            ) {
                reservationRequester.deleteReservation(String.valueOf(reservationResponse.getReservations().get(i).getId()));
            }
        }
    }

    @When("requesting the reservation list again")
    public void get_reservation_list_again() throws IOException {
        reservationResponse = reservationRequester.getReservationList();
        Assertions.assertTrue(reservationResponse.getReservations().size() > 0, "No reservations list!");
    }

    @Then("our reservation disappears from the list")
    public void get_resrvation_list_without_deleted_reservation() {
        boolean isReservationDeleted = false;

        for (int i = 0; i < reservationResponse.getReservations().size(); i++) {
            if (reservationResponse.getReservations().get(i).getSurname().equals(userData.getSurname()) &&
                    reservationResponse.getReservations().get(i).getName().equals(userData.getName()) &&
                    reservationResponse.getReservations().get(i).getDiscount().equals(userData.getDiscountCode()) &&
                    reservationResponse.getReservations().get(i).getAfrom().equals(departureAirport) &&
                    reservationResponse.getReservations().get(i).getAto().equals(destinationAirport)
            ) {
                isReservationDeleted = true;
                break;
            }
        }

        Assertions.assertFalse(isReservationDeleted, "Reservation wasn't deleted!");

    }


}
