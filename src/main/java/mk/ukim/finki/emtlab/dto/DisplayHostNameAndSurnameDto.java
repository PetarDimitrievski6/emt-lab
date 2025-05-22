package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.projections.HostProjection;

import java.util.List;

public record DisplayHostNameAndSurnameDto(String name, String surname) {
    public static DisplayHostNameAndSurnameDto from(HostProjection hostProjection) {
        return new DisplayHostNameAndSurnameDto(hostProjection.getName(), hostProjection.getSurname());
    }

    public static List<DisplayHostNameAndSurnameDto> from(List<HostProjection> hostProjections) {
        return hostProjections.stream().map(DisplayHostNameAndSurnameDto::from).toList();
    }
}
