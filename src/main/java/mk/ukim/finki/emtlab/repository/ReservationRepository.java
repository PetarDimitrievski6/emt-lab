package mk.ukim.finki.emtlab.repository;

import mk.ukim.finki.emtlab.model.domain.Reservation;
import mk.ukim.finki.emtlab.model.domain.User;
import mk.ukim.finki.emtlab.model.enumerations.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    Optional<Reservation> findByUserAndReservationStatus(User user, ReservationStatus reservationStatus);
}
