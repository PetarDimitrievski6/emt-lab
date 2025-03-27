package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enumerations.Category;

import java.util.List;

public record DisplayAccommodationDto(Long id, String name, Category category, Long host, Integer numRooms, Boolean rented) {


    public static DisplayAccommodationDto from(Accommodation accommodation) {
        return new DisplayAccommodationDto(accommodation.getId(), accommodation.getName(), accommodation.getCategory(), accommodation.getHost().getId(), accommodation.getNumRooms(), accommodation.getRented());
    }

    public static List<DisplayAccommodationDto> from(List<Accommodation> accommodations){
        return accommodations.stream().map(DisplayAccommodationDto::from).toList();
    }

    public Accommodation toAccommodation(Host host) {
        return new Accommodation(name, category, host, numRooms, rented);
    }
}
