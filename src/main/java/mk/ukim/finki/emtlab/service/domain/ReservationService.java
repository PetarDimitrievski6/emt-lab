package mk.ukim.finki.emtlab.service.domain;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Reservation;

import java.util.List;
import java.util.Optional;

public interface ReservationService {
    List<Accommodation> listAllAccommodationsInReservation(Long reservationId);

    Optional<Reservation> getUnconfirmedReservation(String username);

    Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId);

    Optional<Reservation> confirmReservation(String username);
}
