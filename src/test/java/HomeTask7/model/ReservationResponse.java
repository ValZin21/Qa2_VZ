package HomeTask7.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ReservationResponse {
    @JsonProperty("reservation")
    private List<Reservation> reservations;

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

//    @JsonProperty("-")
//    private Reservation reservation;
//
//    public Reservation getReservation() {
//        return reservation;
//    }
//
//    public void setReservation(Reservation reservation) {
//        this.reservation = reservation;
//    }
}
