package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class VenueValidator {

    private void validateName(Venue venue) {
        final String name = venue.getName();

        if (name == null || name.isBlank() || name.length() > 120) {
            throw new ValidationException("The 'name' field character length cannot exceed 120 or be equal to zero.");
        }
    }

    private void validateCity(Venue venue) {
        final String city = venue.getCity();

        if (city == null || city.isBlank() || city.length() > 40) {
            throw new ValidationException("The 'city' field character length cannot exceed 40 or be equal to zero.");
        }
    }

    private void validateType(Venue venue) {
        final VenueType venueType = venue.getType();

        if (venueType == null) {
            throw new ValidationException("The 'type' field cannot be null.");
        }
    }

    private void validateAddress(Venue venue) {
        final String address = venue.getAddress();

        if (address == null || address.isBlank()) {
            throw new ValidationException("The 'address' field cannot be empty.");
        }
    }

    private void validateEmail(Venue venue) {
        final String email = venue.getEmail();

        if (email.isBlank() || email.length() > 30) {
            throw new ValidationException("The 'email' field character length cannot exceed 30 or be equal to zero.");
        }
    }

    private void validatePhoneNumber(Venue venue) {
        final String phoneNumber = venue.getPhoneNumber();

        if (phoneNumber.isBlank() || phoneNumber.length() > 20) {
            throw new ValidationException("The 'phone number' field character length cannot exceed 20 or be equal to zero.");
        }
    }

    private void validateWebsite(Venue venue) {
        final String website = venue.getWebsite();

        if (website.isBlank() || website.length() > 30) {
            throw new ValidationException("The 'website' field character length cannot exceed 30 or be equal to zero.");
        }
    }

    public void validateOnCreate(Venue venue) {
        validateName(venue);
        validateCity(venue);
        validateType(venue);
        validateAddress(venue);
        validateEmail(venue);
        validatePhoneNumber(venue);
        validateWebsite(venue);
    }

    public void validateOnUpdate(Venue venue) {
        validateCity(venue);
        validateAddress(venue);
        validateEmail(venue);
        validatePhoneNumber(venue);
    }
}
