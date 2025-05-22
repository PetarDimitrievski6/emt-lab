package mk.ukim.finki.emtlab.service.domain.impl;

import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.emtlab.repository.AccommodationsPerHostViewRepository;
import mk.ukim.finki.emtlab.service.domain.AccommodationService;
import mk.ukim.finki.emtlab.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {
    private final AccommodationRepository accommodationRepository;
    private final HostService hostService;
    private final AccommodationsPerHostViewRepository accommodationsPerHostViewRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository, HostService hostService, AccommodationsPerHostViewRepository accommodationsPerHostViewRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostService = hostService;
        this.accommodationsPerHostViewRepository = accommodationsPerHostViewRepository;
    }


    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> findById(Long id) {
        return this.accommodationRepository.findById(id);
    }

    @Override
    public Optional<Accommodation> save(Accommodation accommodation) {
        if (accommodation.getHost() != null &&
                this.hostService.findById(accommodation.getHost().getId()).isPresent()){
            return Optional.of(accommodationRepository.save(new Accommodation(
                    accommodation.getName(),
                    accommodation.getCategory(),
                    this.hostService.findById(accommodation.getHost().getId()).get(),
                    accommodation.getNumRooms(),
                    accommodation.getRented()
            )));
        }
        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) {
        this.accommodationRepository.deleteById(id);
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    if (accommodation.getName() != null){
                        existingAccommodation.setName(accommodation.getName());
                    }
                    if (accommodation.getCategory() != null){
                        existingAccommodation.setCategory(accommodation.getCategory());
                    }
                    if (accommodation.getHost() != null && this.hostService.findById(accommodation.getHost().getId()).isPresent()){
                        existingAccommodation.setHost(this.hostService.findById(accommodation.getHost().getId()).get());
                    }
                    if (accommodation.getNumRooms() != null){
                        existingAccommodation.setNumRooms(accommodation.getNumRooms());
                    }
                    return this.accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public Optional<Accommodation> rent(Long id) {
        return this.accommodationRepository.findById(id)
                .map(existingAccommodation -> {
                    existingAccommodation.setRented(!existingAccommodation.getRented());
                    return this.accommodationRepository.save(existingAccommodation);
                });
    }

    @Override
    public void refreshMaterializedView() {
        this.accommodationsPerHostViewRepository.refreshMaterializedView();
    }

    @Override
    public List<AccommodationsPerHostView> getAccommodationsPerHostView() {
        return this.accommodationsPerHostViewRepository.findAll();
    }
}
