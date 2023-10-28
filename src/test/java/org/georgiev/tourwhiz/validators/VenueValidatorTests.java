package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.factories.VenueTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class VenueValidatorTests {
    private final VenueValidator venueValidator = new VenueValidator();
    private Venue venue;

    @BeforeEach
    void setUp() {
        venue = VenueTestFactory.createWithSetParameters();
    }

    @Test
    void validatingNameWithMoreThan120CharactersThrowsExceptionWhenCreating() {
        venue.setName("x".repeat(121));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingNameWithNullValueThrowsExceptionWhenCreating() {
        venue.setName(null);

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingCityWithMoreThan40CharactersThrowsExceptionWhenCreating() {
        venue.setCity("x".repeat(41));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingCityWithMoreThan40CharactersThrowsExceptionWhenUpdating() {
        venue.setCity("x".repeat(41));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingCityWithNullValueThrowsExceptionWhenCreating() {
        venue.setCity(null);

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingCityWithNullValueThrowsExceptionWhenUpdating() {
        venue.setCity(null);

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingWithInvalidAddressThrowsExceptionWhenCreating() {
        venue.setAddress("");

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingWithInvalidAddressThrowsExceptionWhenUpdating() {
        venue.setAddress("");

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingWithNullAddressThrowsExceptionWhenCreating() {
        venue.setAddress(null);

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingWithNullAddressThrowsExceptionWhenUpdating() {
        venue.setAddress(null);

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingEmailWithMoreThan30CharactersWhenCreating() {
        venue.setEmail("x".repeat(31));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingEmailWithMoreThan30CharactersWhenUpdating() {
        venue.setEmail("x".repeat(31));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingPhoneNumberWithMoreThan20CharactersThrowsExceptionWhenCreating() {
        venue.setPhoneNumber("x".repeat(21));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

    @Test
    void validatingPhoneNumberWithMoreThan20CharactersThrowsExceptionWhenUpdating() {
        venue.setPhoneNumber("x".repeat(21));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnUpdate(venue));
    }

    @Test
    void validatingWebsiteWithMoreThan120CharactersThrowsExceptionWhenCreating() {
        venue.setWebsite("x".repeat(121));

        assertThrows(ValidationException.class, () -> venueValidator.validateOnCreate(venue));
    }

}
