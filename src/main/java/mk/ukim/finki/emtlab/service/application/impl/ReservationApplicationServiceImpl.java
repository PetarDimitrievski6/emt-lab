package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.ReservationDto;
import mk.ukim.finki.emtlab.service.application.ReservationApplicationService;
import mk.ukim.finki.emtlab.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReservationApplicationServiceImpl implements ReservationApplicationService {
    private final ReservationService reservationService;

    public ReservationApplicationServiceImpl(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @Override
    public Optional<ReservationDto> getPendingReservation(String username) {
        return reservationService.getUnconfirmedReservation(username).map(ReservationDto::from);
    }

    @Override
    public Optional<ReservationDto> addAccommodationToReservation(String username, Long accommodationId) {
        return reservationService.addAccommodationToReservation(username, accommodationId).map(ReservationDto::from);
    }
}
