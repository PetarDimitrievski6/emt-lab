package mk.ukim.finki.emtlab.model.exceptions;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Long reservationId) {
        super(String.format("Reservation with id: %d was not found", reservationId));
    }
}
