package org.georgiev.tourwhiz.mappers;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.presentation.dtos.UpdateVenueDto;
import org.georgiev.tourwhiz.presentation.dtos.VenueDto;
import org.springframework.stereotype.Component;

@Component
public class VenueMapper {

    public VenueDto toDto(Venue venue) {
        return new VenueDto(venue.getName(), venue.getType(), venue.getCity(),
                venue.getAddress(), venue.getEmail(),
                venue.getPhoneNumber(), venue.getWebsite());
    }

    public UpdateVenueDto toUpdateDto(Venue venue) {
        return new UpdateVenueDto(venue.getAddress(), venue.getCity(),
                venue.getPhoneNumber(), venue.getEmail());
    }

    public Venue dtoToEntity(VenueDto venueDto) {
        Venue venue = new Venue();
        venue.setName(venueDto.getName());
        venue.setCity(venueDto.getCity());
        venue.setType(venueDto.getType());
        venue.setAddress(venueDto.getAddress());
        venue.setEmail(venueDto.getEmail());
        venue.setPhoneNumber(venueDto.getPhoneNumber());
        venue.setWebsite(venueDto.getWebsite());

        return venue;
    }

    public Venue updateDtoToEntity(UpdateVenueDto updateVenueDto) {
        Venue venue = new Venue();
        venue.setAddress(updateVenueDto.getAddress());
        venue.setPhoneNumber(updateVenueDto.getPhoneNumber());
        venue.setEmail(updateVenueDto.getEmail());
        venue.setCity(updateVenueDto.getCity());

        return venue;
    }
}
