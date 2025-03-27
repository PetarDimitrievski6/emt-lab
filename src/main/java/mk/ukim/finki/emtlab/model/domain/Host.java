package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Host {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    private Country country;

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }
}
