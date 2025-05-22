package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.events.HostChangeEvent;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.exceptions.HostNotFoundException;
import mk.ukim.finki.emtlab.model.projections.HostProjection;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;
import mk.ukim.finki.emtlab.repository.HostRepository;
import mk.ukim.finki.emtlab.repository.HostsPerCountryViewRepository;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;
    private final HostsPerCountryViewRepository hostsPerCountryViewRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService, HostsPerCountryViewRepository hostsPerCountryViewRepository, ApplicationEventPublisher applicationEventPublisher) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
        this.hostsPerCountryViewRepository = hostsPerCountryViewRepository;
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public List<Host> findAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return this.hostRepository.findById(id);
    }

    @Override
    public Optional<Host> save(Host host) {
        Optional<Host> savedHost = Optional.empty();

        if (host.getCountry() != null && this.countryService.findById(host.getCountry().getId()).isPresent()) {
            savedHost =  Optional.of(this.hostRepository.save(new Host(host.getName(), host.getSurname(), this.countryService.findById(host.getCountry().getId()).get())));
        }
        savedHost.ifPresent(h -> this.applicationEventPublisher.publishEvent(new HostChangeEvent(h)));
        return savedHost;
    }

    @Override
    public void deleteById(Long id) {
        Host deletedHost = this.findById(id).orElseThrow(() -> new HostNotFoundException(id));
        this.hostRepository.deleteById(id);
        this.applicationEventPublisher.publishEvent(new HostChangeEvent(deletedHost));
    }

    @Override
    public Optional<Host> update(Long id, Host host) {
        return this.hostRepository.findById(id)
                .map(existingHost -> {
                    if (host.getName() != null){
                        existingHost.setName(host.getName());
                    }
                    if (host.getSurname() != null){
                        existingHost.setSurname(host.getSurname());
                    }
                    if (host.getCountry() != null && this.countryService.findById(host.getCountry().getId()).isPresent()){
                        existingHost.setCountry(this.countryService.findById(host.getCountry().getId()).get());
                    }

                    Host updatedHost = this.hostRepository.save(existingHost);

                    this.applicationEventPublisher.publishEvent(new HostChangeEvent(updatedHost));

                    return updatedHost;
                });
    }

    @Override
    public void refreshMaterializedViews() {
        this.hostsPerCountryViewRepository.refreshMaterializedView();
    }

    @Override
    public List<HostsPerCountryView> getHostsPerCountryView() {
        return this.hostsPerCountryViewRepository.findAll();
    }

    @Override
    public List<HostProjection> takeNameAndSurnameByProjection() {
        return this.hostRepository.takeNameAndSurnameByProjection();
    }
}
