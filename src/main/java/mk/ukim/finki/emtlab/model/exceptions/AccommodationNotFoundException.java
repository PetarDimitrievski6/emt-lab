package mk.ukim.finki.emtlab.model.exceptions;

public class AccommodationNotFoundException extends RuntimeException {
    public AccommodationNotFoundException(Long accommodationId) {
      super(String.format("Accommodation with id: %d was not found", accommodationId));
    }
}
