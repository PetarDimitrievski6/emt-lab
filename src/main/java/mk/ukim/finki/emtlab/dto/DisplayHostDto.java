package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Host;

import java.util.List;

public record DisplayHostDto(Long id, String name, String surname, Long country) {
    public static DisplayHostDto from(Host host) {
        return new DisplayHostDto(host.getId(), host.getName(), host.getSurname(), host.getCountry().getId());
    }

    public static List<DisplayHostDto> from(List<Host> hosts) {
        return hosts.stream().map(DisplayHostDto::from).toList();
    }

    public Host toHost(Country country) {
        return new Host(name, surname, country);
    }
}
