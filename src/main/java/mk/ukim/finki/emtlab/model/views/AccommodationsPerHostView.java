package mk.ukim.finki.emtlab.model.views;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;
import org.hibernate.annotations.Subselect;

@Data
@Entity
@Subselect("SELECT * FROM public.accommodations_per_host")
@Immutable
@NoArgsConstructor
public class AccommodationsPerHostView {
    @Id
    @Column(name = "host_id")
    private Long hostId;

    @Column(name = "num_accommodations")
    private Integer numAccommodations;

    public AccommodationsPerHostView(Long hostId, Integer numAccommodations) {
        this.hostId = hostId;
        this.numAccommodations = numAccommodations;
    }
}
