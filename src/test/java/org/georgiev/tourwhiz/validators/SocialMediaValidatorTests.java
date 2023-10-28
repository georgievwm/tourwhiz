package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.factories.SocialMediaTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.georgiev.tourwhiz.factories.VenueTestFactory.NON_EXISTING_VENUE_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class SocialMediaValidatorTests {
    private final SocialMediaValidator socialMediaValidator = new SocialMediaValidator();
    private SocialMedia socialMedia;

    @BeforeEach
    void setUp() {
        socialMedia = SocialMediaTestFactory.createWithSetParameters();
    }

    @Test
    void validatingWebpageWithMoreThan120CharsThrowsExceptionWhenCreating() {
        socialMedia.setWebpage("x".repeat(121));

        assertThrows(ValidationException.class, () -> {
            socialMediaValidator.validateOnCreate(socialMedia);
        });
    }

    @Test
    void validatingWebpageWithMoreThan120CharsThrowsExceptionWhenUpdating() {
        socialMedia.setWebpage("x".repeat(121));

        assertThrows(ValidationException.class, () -> {
            socialMediaValidator.validateOnUpdate(socialMedia);
        });
    }

    @Test
    void validatingWebpageWithNullValueThrowsExceptionWhenCreating() {
        socialMedia.setWebpage(null);

        assertThrows(ValidationException.class, () -> {
            socialMediaValidator.validateOnCreate(socialMedia);
        });
    }

    @Test
    void validatingWebpageWithNullValueThrowsExceptionWhenUpdating() {
        socialMedia.setWebpage(null);

        assertThrows(ValidationException.class, () -> {
            socialMediaValidator.validateOnUpdate(socialMedia);
        });
    }

    @Test
    void validatingWithNullTypeParamsThrowsExceptionWhenCreating() {
        socialMedia.setType(null);

        assertThrows(ValidationException.class, () -> {
            socialMediaValidator.validateOnCreate(socialMedia);
        });
    }

    @Test
    void validatingNonExistentSocialMediaForGivenVenueThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                socialMediaValidator.validateHasSameVenue(NON_EXISTING_VENUE_ID, socialMedia));
    }
}
