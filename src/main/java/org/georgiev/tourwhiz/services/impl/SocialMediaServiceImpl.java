package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.repositories.SocialMediaRepository;
import org.georgiev.tourwhiz.exceptions.ResourceNotFoundException;
import org.georgiev.tourwhiz.services.SocialMediaService;
import org.georgiev.tourwhiz.services.VenueService;
import org.georgiev.tourwhiz.validators.SocialMediaValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SocialMediaServiceImpl implements SocialMediaService {

    private final SocialMediaRepository socialMediaRepository;
    private final VenueService venueService;
    private final SocialMediaValidator socialMediaValidator;

    public SocialMediaServiceImpl(SocialMediaRepository socialMediaRepository, VenueService venueService, SocialMediaValidator socialMediaValidator) {
        this.socialMediaRepository = socialMediaRepository;
        this.venueService = venueService;
        this.socialMediaValidator = socialMediaValidator;
    }

    @Transactional
    public SocialMedia create(Long venueId, SocialMedia socialMedia) {
        socialMediaValidator.validateOnCreate(socialMedia);

        Venue venueToSaveIn = venueService.findById(venueId);

        venueToSaveIn.addSocialMedia(socialMedia);

        return socialMediaRepository.save(socialMedia);
    }

    @Transactional
    public SocialMedia update(Long venueId, Long socialMediaId, SocialMedia updatedSocialMedia) {
        socialMediaValidator.validateOnUpdate(updatedSocialMedia);

        SocialMedia existingSocialMedia = findById(socialMediaId);

        socialMediaValidator.validateHasSameVenue(venueId, existingSocialMedia);

        existingSocialMedia.setWebpage(updatedSocialMedia.getWebpage());

        return socialMediaRepository.save(existingSocialMedia);
    }

    @Transactional
    @Override
    public void delete(Long venueId, Long socialMediaId) {
        SocialMedia existingSocialMedia = findById(socialMediaId);

        socialMediaValidator.validateHasSameVenue(venueId, existingSocialMedia);

        socialMediaRepository.delete(existingSocialMedia);
    }

    private SocialMedia findById(Long socialMediaId) {
        return socialMediaRepository.findById(socialMediaId).orElseThrow(() ->
                new ResourceNotFoundException(SocialMedia.class, socialMediaId));
    }

    @Override
    public List<SocialMedia> findAllByVenueId(Long venueId) {
        return socialMediaRepository.findAllByVenueId(venueId);
    }

}
