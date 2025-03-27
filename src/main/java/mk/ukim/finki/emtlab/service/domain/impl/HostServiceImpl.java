package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.repository.HostRepository;
import mk.ukim.finki.emtlab.service.domain.CountryService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final CountryService countryService;

    public HostServiceImpl(HostRepository hostRepository, CountryService countryService) {
        this.hostRepository = hostRepository;
        this.countryService = countryService;
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
        if (host.getCountry() != null && this.countryService.findById(host.getCountry().getId()).isPresent()) {
            return Optional.of(this.hostRepository.save(new Host(host.getName(), host.getSurname(), this.countryService.findById(host.getCountry().getId()).get())));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.hostRepository.deleteById(id);
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
                    return this.hostRepository.save(existingHost);
                });
    }
}
