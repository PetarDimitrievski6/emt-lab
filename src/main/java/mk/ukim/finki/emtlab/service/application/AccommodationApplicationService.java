package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationDto;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {
    List<DisplayAccommodationDto> findAll();

    Optional<DisplayAccommodationDto> findById(Long id);

    Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto);

    void deleteById(Long id);

    Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto);

    Optional<DisplayAccommodationDto> rent(Long id);

}
