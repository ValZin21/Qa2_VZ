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

    @Then("temp is: (.*)")
    public void check_temp(double temp) {
        assertEquals(temp, response.getMain().getTemp(), "Temp is incorrect!");
    }

    @Then("pressure is: (.*)")
    public void check_pressure(double pressure) {
        assertEquals(pressure, response.getMain().getPressure(), "Temp is incorrect!");
    }

    @Then("temp_min is: (.*)")
    public void check_tempMin(double tempMin) {
        assertEquals(tempMin, response.getMain().getTemp_min(), "Temp_min is incorrect!");
    }

    @Then("temp_max is: (.*)")
    public void check_tempMax(double tempMax) {
        assertEquals(tempMax, response.getMain().getTemp_max(), "Temp_max is incorrect!");
    }

    @Then("humidity is: (.*)")
    public void check_humidity(double humidity) {
        assertEquals(humidity, response.getMain().getHumidity(), "Humidity is incorrect!");
    }

    @Then("speed is: (.*)")
    public void check_speed(double speed) {
        assertEquals(speed, response.getWind().getSpeed(), "Speed is incorrect!");
    }

    @Then("deg is: (.*)")
    public void check_deg(double deg) {
        assertEquals(deg, response.getWind().getDeg(), "Deg is incorrect!");
    }

    @Then("all is: (.*)")
    public void check_all(double all) {
        assertEquals(all, response.getClouds().getAll(), "Clouds <All> parameter is incorrect!");
    }

    @Then("type is: (.*)")
    public void check_type(int type) {
        assertEquals(type, response.getSys().getType(), "Type is incorrect!");
    }

    @Then("sys id is: (.*)")
    public void check_sysId(String sysId) {
        assertEquals(sysId, response.getSys().getId(), "Sys <Id> is incorrect!");
    }

    @Then("message is: (.*)")
    public void check_message(double message) {
        assertEquals(message, response.getSys().getMessage(), "Message is incorrect!");
    }

    @Then("country is: (.*)")
    public void check_country(String country) {
        assertEquals(country, response.getSys().getCountry(), "Country is incorrect!");
    }

    @Then("sunrise is: (.*)")
    public void check_sunrise(String sunrise) {
        assertEquals(sunrise, response.getSys().getSunrise(), "Sunrise is incorrect!");
    }

    @Then("sunset is: (.*)")
    public void check_sunset(String sunset) {
        assertEquals(sunset, response.getSys().getSunset(), "Sunset is incorrect!");
    }

    @Then("base is: (.*)")
    public void check_base(String base) {
        assertEquals(base, response.getBase(), "Base is incorrect!");
    }

    @Then("visibility is: (.*)")
    public void check_visibility(int visibility) {
        assertEquals(visibility, response.getVisibility(), "Visibility is incorrect!");
    }

    @Then("dt is: (.*)")
    public void check_dt(String dt) {
        assertEquals(dt, response.getDt(), "Dt is incorrect!");
    }

    @Then("additional id is: (.*)")
    public void check_additionalId(String additionalId) {
        assertEquals(additionalId, response.getId(), "Additional Id is incorrect!");
    }

    @Then("name is: (.*)")
    public void check_name(String name) {
        assertEquals(name, response.getName(), "Name is incorrect!");
    }

    @Then("cod is: (.*)")
    public void check_cod(String cod) {
        assertEquals(cod, response.getCod(), "Cod is incorrect!");
    }

}
