package HomeTask7;

import HomeTask7.model.Reservation;
import HomeTask7.model.ReservationResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ReservationRequester {

    private final String GET_RESERVATION_LIST = "http://qaguru.lv:8090/tickets/getReservations.php";
    RestTemplate restTemplate = new RestTemplate();
//    private ReservationResponse reservationResponse;

    public ReservationResponse getReservationList() throws IOException  {
//        this.reservationResponse = reservationResponse;

        String responseToParse = restTemplate.getForEntity(GET_RESERVATION_LIST, String.class).getBody(); // getting the GET response (in STRING due to API bug)


        System.out.println("responseToParse: " + responseToParse);

        responseToParse = "{\"reservation\":" + responseToParse + "}";

        System.out.println("responseToParse modified: " + responseToParse);

        ObjectMapper objectMapper = new ObjectMapper(); // template to map JSON with model (сирилизация, обратный процесс - десирилизация)
        return objectMapper.readValue(responseToParse, ReservationResponse.class);
//        objectMapper.writeValueAsString(responseToParse)
//        return objectMapper.readValue(responseToParse, new TypeReference<List<ReservationResponse>>(){});
//         return null;

//        return Arrays.asList(objectMapper.readValue(responseToParse, ReservationResponse.class));  // return JSON model from GET response (String)
    }

    public void deleteReservation(String id) {
        restTemplate.delete("http://qaguru.lv:8090/tickets/delete.php?id=" + id);
    }
}
