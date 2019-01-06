package apiSteps;

import apiSteps.model.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import org.junit.jupiter.api.Assertions;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherStepDefs {
    private String city;
    private WeatherRequester requester = new WeatherRequester();
    private Response response = new Response();

    @Given("city name: (.*)")
    public void set_city(String city) {
        this.city = city; //class level parameter set on method level parameter with same name
    }

    @When("we are requesting weather info")
    public void request_weather() throws IOException {
        response = requester.getWeather(city);
    }

    @Then("lon is: (.*)")
    public void check_lon(double lon) {
        assertEquals(lon, response.getCoord().getLon(), "Lon is incorrect!");
    }

    @Then("lat is: (.*)")
    public void check_lat(double lat) {
        assertEquals(lat, response.getCoord().getLat(), "Lat is incorrect!");
    }
}
