package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.domain.Country;
import mk.ukim.finki.emtlab.model.domain.Host;

import java.util.List;

public record CreateHostDto(String name, String surname, Long country) {
    public static CreateHostDto from(Host host){
        return new CreateHostDto(host.getName(), host.getSurname(), host.getCountry().getId());
    }
    public static List<CreateHostDto> from(List<Host> hosts){
        return hosts.stream().map(CreateHostDto::from).toList();
    }
    public Host toHost(Country country){
        return new Host(name, surname, country);
    }
}
