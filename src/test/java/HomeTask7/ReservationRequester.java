package HomeTask7;

import HomeTask7.model.ReservationResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class ReservationRequester {

    private final String GET_RESERVATION_LIST = "http://qaguru.lv:8090/tickets/getReservations.php";
    RestTemplate restTemplate = new RestTemplate();

    public ReservationResponse getReservationList() throws IOException  {

        String responseToParse = restTemplate.getForEntity(GET_RESERVATION_LIST, String.class).getBody();

        responseToParse = "{\"reservation\":" + responseToParse + "}";

        ObjectMapper objectMapper = new ObjectMapper(); // template to map JSON with model (сирилизация, обратный процесс - десирилизация)
        return objectMapper.readValue(responseToParse, ReservationResponse.class);
    }

    public void deleteReservation(String id) {
        restTemplate.delete("http://qaguru.lv:8090/tickets/delete.php?id=" + id);
    }
}
