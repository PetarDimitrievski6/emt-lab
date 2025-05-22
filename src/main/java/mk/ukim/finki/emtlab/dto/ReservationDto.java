package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Reservation;
import mk.ukim.finki.emtlab.model.enumerations.ReservationStatus;


import java.util.List;

public record ReservationDto(
        Long id,
        DisplayUserDto user,
        List<DisplayAccommodationDto> products,
        ReservationStatus status
) {
    public static ReservationDto from(Reservation reservation) {
        return new ReservationDto(
                reservation.getId(),
                DisplayUserDto.from(reservation.getUser()),
                DisplayAccommodationDto.from(reservation.getAccommodations()),
                reservation.getReservationStatus()
        );
    }
}
