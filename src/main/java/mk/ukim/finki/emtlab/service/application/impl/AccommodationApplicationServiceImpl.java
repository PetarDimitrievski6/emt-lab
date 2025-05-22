package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.CreateAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationDto;
import mk.ukim.finki.emtlab.dto.DisplayAccommodationsPerHostViewDto;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.service.application.AccommodationApplicationService;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {
    private final AccommodationService accommodationService;
    private final HostService hostService;

    public AccommodationApplicationServiceImpl(AccommodationService accommodationService, HostService hostService) {
        this.accommodationService = accommodationService;
        this.hostService = hostService;
    }

    @Override
    public List<DisplayAccommodationDto> findAll() {
        return this.accommodationService.findAll().stream().map(DisplayAccommodationDto::from).toList();
    }

    @Override
    public Optional<DisplayAccommodationDto> findById(Long id) {
        return this.accommodationService.findById(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> save(CreateAccommodationDto createAccommodationDto) {
        return this.hostService.findById(createAccommodationDto.host())
                .flatMap(host -> this.accommodationService.save(createAccommodationDto.toAccommodation(host))
                        .map(DisplayAccommodationDto::from));
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationService.deleteById(id);
    }

    @Override
    public Optional<DisplayAccommodationDto> update(Long id, CreateAccommodationDto createAccommodationDto) {
        Optional<Host> host = this.hostService.findById(createAccommodationDto.host());

        return this.accommodationService.update(id, createAccommodationDto.toAccommodation(host.orElse(null))).map(DisplayAccommodationDto::from);
    }

    @Override
    public Optional<DisplayAccommodationDto> rent(Long id) {
        return this.accommodationService.rent(id).map(DisplayAccommodationDto::from);
    }

    @Override
    public List<DisplayAccommodationsPerHostViewDto> getAccommodationsPerHostView() {
        return this.accommodationService.getAccommodationsPerHostView().stream().map(DisplayAccommodationsPerHostViewDto::from).toList();
    }
}
