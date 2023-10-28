package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.data.models.SocialMediaType;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class SocialMediaValidator {

    private void validateWebpage(SocialMedia socialMedia) {
        final String webpage = socialMedia.getWebpage();
        if (webpage == null || webpage.isBlank() || webpage.length() > 120) {
            throw new ValidationException("The 'webpage' field character length cannot exceed 120 or be equal to zero.");
        }
    }

    private void validateType(SocialMedia socialMedia) {
        final SocialMediaType socialMediaType = socialMedia.getType();
        if (socialMediaType == null) {
            throw new ValidationException("The 'type' field cannot be null.");
        }
    }

    public void validateOnCreate(SocialMedia socialMedia) {
        validateType(socialMedia);
        validateWebpage(socialMedia);
    }

    public void validateOnUpdate(SocialMedia socialMedia) {
        validateWebpage(socialMedia);
    }

    public void validateHasSameVenue(Long venueId, SocialMedia socialMedia) {
        if (!socialMedia.hasVenueWithId(venueId)) {
            throw new IllegalArgumentException("Social media with ID = %s, associated with venue with ID = %s was not found."
                    .formatted(socialMedia.getId(), venueId));
        }
    }
}
