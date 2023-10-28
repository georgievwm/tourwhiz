package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.presentation.dtos.UpdateVenueDto;
import org.georgiev.tourwhiz.presentation.dtos.VenueDto;

public class VenueDtoTestFactory {

    public static VenueDto createVenueDtoWithSetParameters() {
        VenueDto venueDto = new VenueDto();
        venueDto.setType(VenueType.HOTEL);
        venueDto.setCity("Burgas");
        venueDto.setAddress("somewhere street");
        venueDto.setEmail("test@gmail.com");
        venueDto.setName("Hotela");
        venueDto.setPhoneNumber("123123123");
        venueDto.setWebsite("www.hotela.com");

        return venueDto;
    }

    public static UpdateVenueDto createUpdateVenueDtoWithSetParameters() {
        UpdateVenueDto updateVenueDto = new UpdateVenueDto();
        updateVenueDto.setCity("Sofia");
        updateVenueDto.setAddress("updated street");
        updateVenueDto.setEmail("updated123@gmail.com");
        updateVenueDto.setPhoneNumber("666166666");

        return updateVenueDto;
    }
}
