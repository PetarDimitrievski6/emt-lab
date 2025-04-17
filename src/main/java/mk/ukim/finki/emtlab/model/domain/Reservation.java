package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtlab.model.enumerations.ReservationStatus;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToMany
    private List<Accommodation> accommodations;

    @Enumerated(EnumType.STRING)
    private ReservationStatus reservationStatus = ReservationStatus.UNCONFIRMED;

    public Reservation(User user, List<Accommodation> accommodations) {
        this.user = user;
        this.accommodations = accommodations;
    }

    public Reservation(User user) {
        this.user = user;
    }
}
