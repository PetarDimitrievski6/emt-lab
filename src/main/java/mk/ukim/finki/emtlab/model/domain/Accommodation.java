package mk.ukim.finki.emtlab.model.domain;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.emtlab.model.enumerations.Category;

@Entity
@Data
@NoArgsConstructor
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @ManyToOne
    private Host host;
    private Integer numRooms;
    private Boolean rented;

    public Accommodation(String name, Category category, Host host, Integer numRooms, Boolean rented) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.rented = false;
    }
}
