package mk.ukim.finki.emtlab.model.exceptions;

public class HostNotFoundException extends RuntimeException {
    public HostNotFoundException(Long hostId) {
        super(String.format("Host with id: %d was not found", hostId));
    }
}