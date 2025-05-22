package mk.ukim.finki.emtlab.dto;

import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;
import mk.ukim.finki.emtlab.model.views.HostsPerCountryView;

import java.util.List;

public record DisplayHostPerCountryViewDto(Long countryId, Integer numHosts) {
    public static DisplayHostPerCountryViewDto from(HostsPerCountryView hostsPerCountryView) {
        return new DisplayHostPerCountryViewDto(hostsPerCountryView.getCountryId(), hostsPerCountryView.getNumHosts());
    }

    public static List<DisplayHostPerCountryViewDto> from(List<HostsPerCountryView> hosts){
        return hosts.stream().map(DisplayHostPerCountryViewDto::from).toList();
    }

    public HostsPerCountryView toHostsPerCountryView() {
        return new HostsPerCountryView(countryId, numHosts);
    }
}
