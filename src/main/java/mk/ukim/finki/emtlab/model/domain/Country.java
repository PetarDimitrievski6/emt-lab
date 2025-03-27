package mk.ukim.finki.emtlab.model.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String continent;

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }
}
