package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.presentation.dtos.UpdateVenueDto;

public class VenueTestFactory {
    public static Long VENUE_ID = 1L;
    public static Long NON_EXISTING_VENUE_ID = 15L;

    public static Venue createWithSetParameters() {
        Venue venue = new Venue();
        venue.setType(VenueType.HOTEL);
        venue.setCity("Burgas");
        venue.setAddress("somewhere street");
        venue.setEmail("test@gmail.com");
        venue.setName("Hotela");
        venue.setPhoneNumber("123123123");
        venue.setId(VENUE_ID);
        venue.setWebsite("www.hotela.com");

        return venue;
    }
}
