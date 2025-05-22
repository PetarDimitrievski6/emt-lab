package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.ReservationDto;

import java.util.Optional;

public interface ReservationApplicationService {
    Optional<ReservationDto> getPendingReservation(String username);

    Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId);
}
