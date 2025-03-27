package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enumerations.Category;

import java.util.List;

public record CreateAccommodationDto(String name, Category category, Long host, Integer numRooms, Boolean rented) {


    public static CreateAccommodationDto from(Accommodation accommodation) {
        return new CreateAccommodationDto(accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.getRented());
    }

    public static List<CreateAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(CreateAccommodationDto::from).toList();
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, rented);
    }
}
