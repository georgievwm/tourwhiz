package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.georgiev.tourwhiz.data.repositories.SocialMediaRepository;
import org.georgiev.tourwhiz.factories.SocialMediaTestFactory;
import org.georgiev.tourwhiz.validators.SocialMediaValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.georgiev.tourwhiz.factories.SocialMediaTestFactory.SOCIAL_MEDIA_ID;
import static org.georgiev.tourwhiz.factories.SocialMediaTestFactory.SOCIAL_MEDIA_VENUE;
import static org.georgiev.tourwhiz.factories.VenueTestFactory.VENUE_ID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SocialMediaServiceImplTests {
    @Mock
    private SocialMediaRepository socialMediaRepository;
    @Mock
    private VenueServiceImpl venueService;
    @Mock
    private SocialMediaValidator socialMediaValidator;
    @InjectMocks
    private SocialMediaServiceImpl socialMediaService;
    private SocialMedia socialMedia;
    private SocialMedia updated;

    @BeforeEach
    void setup() {
        socialMedia = SocialMediaTestFactory.createWithSetParameters();
        updated = SocialMediaTestFactory.createWithUpdatedParameters();
    }

    @Test
    void creatingSocialMediaSavesAndReturnsIt() {
        when(socialMediaRepository.save(socialMedia)).thenReturn(socialMedia);
        when(venueService.findById(VENUE_ID)).thenReturn(SOCIAL_MEDIA_VENUE);

        SocialMedia created = socialMediaService.create(VENUE_ID, socialMedia);

        assertThat(created).isSameAs(socialMedia);
    }

    @Test
    void findByVenueIdReturnsTheGivenSocialMedia() {
        when(socialMediaRepository.findAllByVenueId(SOCIAL_MEDIA_ID)).thenReturn(List.of(socialMedia));

        List<SocialMedia> existingForGivenVenue = socialMediaService.findAllByVenueId(SOCIAL_MEDIA_ID);

        assertThat(existingForGivenVenue).containsExactly(socialMedia).hasSize(1);
    }

    @Test
    void updateReturnsUpdatedSocialMedia() {
        when(socialMediaRepository.save(socialMedia)).thenReturn(socialMedia);
        when(socialMediaRepository.findById(SOCIAL_MEDIA_ID)).thenReturn(Optional.of(socialMedia));

        SocialMedia updatedSm = socialMediaService.update(VENUE_ID, SOCIAL_MEDIA_ID, updated);

        assertThat(updatedSm.getId()).isEqualTo(updated.getId());
        assertThat(updatedSm.getType()).isEqualTo(updated.getType());
        assertThat(updatedSm.getWebpage()).isEqualTo(updated.getWebpage());
        assertThat(updatedSm.getVenue()).isSameAs(updated.getVenue());
    }

    @Test
    void deleteByIdSuccessfullyRemovesFromRepository() {
        when(socialMediaRepository.findById(SOCIAL_MEDIA_ID)).thenReturn(Optional.of(socialMedia));
        doNothing().when(socialMediaRepository).delete(socialMedia);

        socialMediaService.delete(VENUE_ID, SOCIAL_MEDIA_ID);

        verify(socialMediaRepository, Mockito.times(1)).delete(socialMedia);
    }
}
