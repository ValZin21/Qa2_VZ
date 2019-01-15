package HomeTask7;

import HomeTask7.pages.BaseFunc;
import HomeTask7.pages.HomePage;
import HomeTask7.pages.UserReservationDataPage;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;

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

    @Given("Deparutre airport: (.*)")
    public void set_departure_airport (String departureAirport) {
        this.departureAirport = departureAirport;
    }

    @Given("Destination airport: (.*)")
    public void set_destination_airport (String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    @Given("User data is:") // userData model needed
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
    public void set_seat_number (int seatNumber) {
        this.seatNumber = seatNumber;
    }

    @Given("we are on the home page")
    public void set_home_page () {
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


    }

    @When("we are pressing on the Get Price button")
    public void click_get_price_button() {

    }

    @Then("our price will be: 1000 euro")
    public void check_reservation_price() {

    }

    @When("we are pressing on the Book! button")
    public void press_book_button() {

    }

    @Then("we can choose the seat")
    public void get_choose_seat() {

    }

    @When("we are selecting our seat number: 21")
    public void get_seat_number() {

    }

    @When("we are clincking Book! button")
    public void click_book_button() {

    }

    @Then("we are receiving successful registration page")
    public void get_successfull_registration_page() {

    }

    @When("we are requesting reservation list")
    public void get_resrvation_list() {

    }

    @Then("we can see our reservation in the list")
    public void get_reservation_list() {

    }

    @When("we are deleting our reservation tikcet")
    public void delete_reservation() {

    }

    @When("requesting the reservation list again")
    public void get_reservation_list_again() {

    }

    @Then("our reservation disappears from the list")
    public void get_resrvation_list_without_deleted_reservation() {

    }



}
