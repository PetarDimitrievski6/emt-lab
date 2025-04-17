package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Reservation;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.enumerations.ReservationStatus;
import mk.ukim.finki.emtlab.model.exceptions.AccommodationAlreadyInReservationException;
import mk.ukim.finki.emtlab.model.exceptions.AccommodationAlreadyRented;
import mk.ukim.finki.emtlab.model.exceptions.AccommodationNotFoundException;
import mk.ukim.finki.emtlab.model.exceptions.ReservationNotFoundException;
import mk.ukim.finki.emtlab.repository.ReservationRepository;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.ReservationService;
import mk.ukim.finki.emtlab.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
class ReservationServiceImpl implements ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserService userService;
    private final AccommodationService accommodationService;

    ReservationServiceImpl(ReservationRepository reservationRepository, UserService userService, AccommodationService accommodationService) {
        this.reservationRepository = reservationRepository;
        this.userService = userService;
        this.accommodationService = accommodationService;
    }


    @Override
    public List<Accommodation> listAllAccommodationsInReservation(Long reservationId) {
        if (this.reservationRepository.findById(reservationId).isEmpty()){
            throw new ReservationNotFoundException(reservationId);
        }
        return this.reservationRepository.findById(reservationId).get().getAccommodations();
    }

    @Override
    public Optional<Reservation> getUnconfirmedReservation(String username) {
        User user = userService.findByUsername(username);

        return Optional.of(this.reservationRepository.findByUserAndStatus(
                user,
                ReservationStatus.UNCONFIRMED
        ).orElseGet(() -> this.reservationRepository.save(new Reservation(user))));
    }

    @Override
    public Optional<Reservation> addAccommodationToReservation(String username, Long accommodationId) {
        if (this.getUnconfirmedReservation(username).isPresent()){
            Reservation reservation = getUnconfirmedReservation(username).get();

            Accommodation accommodation = this.accommodationService.findById(accommodationId)
                    .orElseThrow(() -> new AccommodationNotFoundException(accommodationId));
            if (accommodation.getRented()){
                throw new AccommodationAlreadyRented();
            }
            if (!reservation.getAccommodations().stream().filter(i -> i.getId().equals(accommodationId)).toList().isEmpty()){
                throw new AccommodationAlreadyInReservationException(accommodationId, username);
            }
            reservation.getAccommodations().add(accommodation);
            return Optional.of(reservationRepository.save(reservation));
        }
        return Optional.empty();
    }

    @Override
    public Optional<Reservation> confirmReservation(String username) {
        if (this.getUnconfirmedReservation(username).isPresent()) {
            Reservation reservation = getUnconfirmedReservation(username).get();
            if (!reservation.getAccommodations().stream().filter(accommodation -> accommodation.getRented() == true).toList().isEmpty()){
                throw new AccommodationAlreadyRented();
            }
            reservation.getAccommodations().stream().forEach(accommodation -> accommodation.setRented(true));
            return Optional.of(reservation);
        }
        return Optional.empty();
    }
}
