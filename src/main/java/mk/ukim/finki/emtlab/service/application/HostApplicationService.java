package mk.ukim.finki.emtlab.service.application;

import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostNameAndSurnameDto;
import mk.ukim.finki.emtlab.dto.DisplayHostPerCountryViewDto;
import mk.ukim.finki.emtlab.model.projections.HostProjection;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    List<DisplayHostDto> findAll();

    Optional<DisplayHostDto> findById(Long id);

    Optional<DisplayHostDto> save(CreateHostDto createHostDto);

    void deleteById(Long id);

    Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto);

    List<DisplayHostPerCountryViewDto> getHostsPerCountryView();

    List<DisplayHostNameAndSurnameDto> takeNameAndSurnameByProjection();
}
