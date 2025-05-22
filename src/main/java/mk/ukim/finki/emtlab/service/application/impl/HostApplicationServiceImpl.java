package mk.ukim.finki.emtlab.service.application.impl;

import mk.ukim.finki.emtlab.dto.CreateHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostDto;
import mk.ukim.finki.emtlab.dto.DisplayHostNameAndSurnameDto;
import mk.ukim.finki.emtlab.dto.DisplayHostPerCountryViewDto;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.service.application.HostApplicationService;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostApplicationServiceImpl implements HostApplicationService {
    private final HostService hostService;
    private final CountryService countryService;

    public HostApplicationServiceImpl(HostService hostService, CountryService countryService) {
        this.hostService = hostService;
        this.countryService = countryService;
    }

    @Override
    public List<DisplayHostDto> findAll() {
        return this.hostService.findAll().stream().map(DisplayHostDto::from).toList();
    }

    @Override
    public Optional<DisplayHostDto> findById(Long id) {
        return this.hostService.findById(id).map(DisplayHostDto::from);
    }

    @Override
    public Optional<DisplayHostDto> save(CreateHostDto createHostDto) {
        return this.countryService.findById(createHostDto.country()).flatMap(country -> this.hostService.save(createHostDto.toHost(country))).map(DisplayHostDto::from);
    }

    @Override
    public void deleteById(Long id) {
        this.hostService.deleteById(id);
    }

    @Override
    public Optional<DisplayHostDto> update(Long id, CreateHostDto createHostDto) {
        Optional<Country> country = this.countryService.findById(createHostDto.country());
        return this.hostService.update(id, createHostDto.toHost(
                country.orElse(null)
        )).map(DisplayHostDto::from);
    }

    @Override
    public List<DisplayHostPerCountryViewDto> getHostsPerCountryView() {
        return DisplayHostPerCountryViewDto.from(this.hostService.getHostsPerCountryView());
    }

    @Override
    public List<DisplayHostNameAndSurnameDto> takeNameAndSurnameByProjection() {
        return DisplayHostNameAndSurnameDto.from(this.hostService.takeNameAndSurnameByProjection());
    }
}
