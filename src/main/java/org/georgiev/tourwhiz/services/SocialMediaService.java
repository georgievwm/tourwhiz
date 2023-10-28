package org.georgiev.tourwhiz.services;

import org.georgiev.tourwhiz.data.models.SocialMedia;

import java.util.List;

public interface SocialMediaService {

    SocialMedia create(Long venueId, SocialMedia socialMedia);

    SocialMedia update(Long venueId, Long socialMediaId, SocialMedia updatedSocialMedia);

    void delete(Long venueId, Long socialMediaId);

    List<SocialMedia> findAllByVenueId(Long venueId);


}
