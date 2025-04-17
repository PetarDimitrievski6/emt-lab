package mk.ukim.finki.emtlab.model.exceptions;

public class AccommodationAlreadyInReservationException extends RuntimeException {
    public AccommodationAlreadyInReservationException(Long accommodationId, String username) {
        super(String.format("Accommodation with id: %d already exists in reservation for user with username %s"));
    }
}
