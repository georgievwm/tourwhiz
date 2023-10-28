package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.data.repositories.VenueRepository;
import org.georgiev.tourwhiz.exceptions.ResourceNotFoundException;
import org.georgiev.tourwhiz.services.VenueService;
import org.georgiev.tourwhiz.validators.VenueValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class VenueServiceImpl implements VenueService {

    private final VenueRepository venueRepository;
    private final VenueValidator venueValidator;

    public VenueServiceImpl(VenueRepository venueRepository, VenueValidator venueValidator) {
        this.venueRepository = venueRepository;
        this.venueValidator = venueValidator;
    }

    @Override
    public Venue create(Venue venue) {
        venueValidator.validateOnCreate(venue);

        return venueRepository.save(venue);
    }

    @Transactional
    @Override
    public Venue updateById(Long venueId, Venue updated) {
        venueValidator.validateOnUpdate(updated);
        Venue venueToUpdate = findById(venueId);

        venueToUpdate.setAddress(updated.getAddress());
        venueToUpdate.setCity(updated.getCity());
        venueToUpdate.setPhoneNumber(updated.getPhoneNumber());
        venueToUpdate.setEmail(updated.getEmail());

        return venueRepository.save((venueToUpdate));
    }

    @Transactional
    @Override
    public void deleteById(Long venueId) {
        Venue venueToDelete = findById(venueId);

        venueRepository.delete(venueToDelete);
    }

    @Override
    public Venue findById(Long venueId) {
        return venueRepository.findById(venueId)
                .orElseThrow(() -> new ResourceNotFoundException(Venue.class, venueId));
    }

    @Override
    public List<Venue> findFilteredBy(VenueType type, String city, BigDecimal rating) {
        return venueRepository.findFilteredByTypeCityAndRating(type, city, rating);
    }
}
