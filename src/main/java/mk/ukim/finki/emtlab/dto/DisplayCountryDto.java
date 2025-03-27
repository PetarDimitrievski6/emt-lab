package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Country;

import java.util.List;

public record DisplayCountryDto(String name, String continent) {
    public static DisplayCountryDto from(Country country) {
        return new DisplayCountryDto(country.getName(), country.getContinent());
    }

    public static List<DisplayCountryDto> from(List<Country> countries) {
        return countries.stream().map(DisplayCountryDto::from).toList();
    }

    public Country toCountry() {
        return new Country(name, continent);
    }
}
