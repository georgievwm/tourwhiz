package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.data.models.SocialMediaType;
import org.georgiev.tourwhiz.data.models.Venue;

import java.util.List;

public class SocialMediaTestFactory {
    public static Long SOCIAL_MEDIA_ID = 1L;
    public static Venue SOCIAL_MEDIA_VENUE = VenueTestFactory.createWithSetParameters();

    public static SocialMedia createWithSetParameters() {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setWebpage("www.test.com");
        socialMedia.setType(SocialMediaType.FACEBOOK);
        socialMedia.setId(SOCIAL_MEDIA_ID);
        socialMedia.setVenue(SOCIAL_MEDIA_VENUE);
        SOCIAL_MEDIA_VENUE.setSocialMedias(List.of(socialMedia));

        return socialMedia;
    }

    public static SocialMedia createWithUpdatedParameters() {
        SocialMedia socialMedia = new SocialMedia();
        socialMedia.setWebpage("www.updated.com");
        socialMedia.setType(SocialMediaType.FACEBOOK);
        socialMedia.setId(SOCIAL_MEDIA_ID);
        socialMedia.setVenue(SOCIAL_MEDIA_VENUE);
        SOCIAL_MEDIA_VENUE.setSocialMedias(List.of(socialMedia));


        return socialMedia;
    }

}
