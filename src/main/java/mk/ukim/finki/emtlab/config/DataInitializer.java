package mk.ukim.finki.emtlab.config;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.emtlab.model.domain.Accommodation;
import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Host;
import mk.ukim.finki.emtlab.model.enumerations.Category;
import mk.ukim.finki.emtlab.repository.AccommodationRepository;
import mk.ukim.finki.emtlab.repository.CountryRepository;
import mk.ukim.finki.emtlab.repository.HostRepository;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {
    private final AccommodationRepository accommodationRepository;
    private final HostRepository hostRepository;
    private final CountryRepository countryRepository;

    public DataInitializer(AccommodationRepository accommodationRepository, HostRepository hostRepository, CountryRepository countryRepository) {
        this.accommodationRepository = accommodationRepository;
        this.hostRepository = hostRepository;
        this.countryRepository = countryRepository;
    }

    @PostConstruct
    public void init(){
        Country country1 = new Country("USA", "North America");
        Country country2 = new Country("Canada", "North America");
        Country country3 = new Country("France", "Europe");
        countryRepository.save(country1);
        countryRepository.save(country2);
        countryRepository.save(country3);

        Host host1 = new Host("John", "Doe", country1);
        Host host2 = new Host("Jane", "Smith", country2);
        Host host3 = new Host("Mike", "Johnson", country3);
        hostRepository.save(host1);
        hostRepository.save(host2);
        hostRepository.save(host3);

        accommodationRepository.save(new Accommodation("Accommodation 1", Category.HOTEL, host1, 6, false));
        accommodationRepository.save(new Accommodation("Accommodation 2", Category.APARTMENT, host2, 7, false));
        accommodationRepository.save(new Accommodation("Accommodation 3", Category.HOTEL, host3, 8, false));
        accommodationRepository.save(new Accommodation("Accommodation 4", Category.APARTMENT, host1, 9, false));
        accommodationRepository.save(new Accommodation("Accommodation 5", Category.HOTEL, host2, 10, false));
        accommodationRepository.save(new Accommodation("Accommodation 6", Category.APARTMENT, host3, 11, false));
        accommodationRepository.save(new Accommodation("Accommodation 7", Category.HOTEL, host1, 12, false));
        accommodationRepository.save(new Accommodation("Accommodation 8", Category.APARTMENT, host2, 13, false));
        accommodationRepository.save(new Accommodation("Accommodation 9", Category.HOTEL, host3, 14, false));
        accommodationRepository.save(new Accommodation("Accommodation 10", Category.APARTMENT, host1, 15, false));

    }
}
