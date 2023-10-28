package org.georgiev.tourwhiz.services;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;

import java.math.BigDecimal;
import java.util.List;

public interface VenueService {

    Venue create(Venue venue);

    Venue updateById(Long venueId, Venue venueToUpdate);

    void deleteById(Long venueId);

    Venue findById(Long venueId);

    List<Venue> findFilteredBy(VenueType type, String city, BigDecimal rating);

}
