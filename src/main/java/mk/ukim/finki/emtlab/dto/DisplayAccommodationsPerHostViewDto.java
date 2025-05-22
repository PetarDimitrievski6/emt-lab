package mk.ukim.finki.emtlab.dto;



import mk.ukim.finki.emtlab.model.views.AccommodationsPerHostView;

import java.util.List;

public record DisplayAccommodationsPerHostViewDto(Long hostId, Integer numAccommodations) {
    public static DisplayAccommodationsPerHostViewDto from(AccommodationsPerHostView accommodationsPerHostView) {
        return new DisplayAccommodationsPerHostViewDto(accommodationsPerHostView.getHostId(), accommodationsPerHostView.getNumAccommodations());
    }

    public static List<DisplayAccommodationsPerHostViewDto> from(List<AccommodationsPerHostView> accommodations){
        return accommodations.stream().map(DisplayAccommodationsPerHostViewDto::from).toList();
    }

    public AccommodationsPerHostView toAccommodationsPerHostView() {
        return new AccommodationsPerHostView(hostId, numAccommodations);
    }
}
