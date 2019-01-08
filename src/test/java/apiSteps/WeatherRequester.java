package apiSteps;

import apiSteps.model.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

//designed to work with weather API
public class WeatherRequester {

    private final String PREFIX = "https://samples.openweathermap.org/data/2.5/weather?q=";
    private final String POSTFIX = "&appid=b6907d289e10d714a6e88b30761fae22";

    public Response getWeather(String city) throws IOException {
        String url = PREFIX + city + POSTFIX;

        RestTemplate restTemplate = new RestTemplate(); // template to work with requests
        String responseToParse = restTemplate.getForEntity(url, String.class).getBody(); // getting the GET response (in STRING due to API bug)

        ObjectMapper objectMapper = new ObjectMapper(); // template to map JSON with model (сирилизация, обратный процесс - десирилизация)
        return objectMapper.readValue(responseToParse, Response.class);  // return JSON model from GET response (String)
    }

}
