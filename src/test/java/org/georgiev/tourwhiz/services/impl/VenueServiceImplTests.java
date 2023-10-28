package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.georgiev.tourwhiz.data.repositories.VenueRepository;
import org.georgiev.tourwhiz.exceptions.ResourceNotFoundException;
import org.georgiev.tourwhiz.factories.VenueDtoTestFactory;
import org.georgiev.tourwhiz.factories.VenueTestFactory;
import org.georgiev.tourwhiz.validators.VenueValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.georgiev.tourwhiz.factories.VenueTestFactory.VENUE_ID;
import static org.georgiev.tourwhiz.factories.VenueTestFactory.createWithSetParameters;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VenueServiceImplTests {
    @Mock
    private VenueRepository venueRepository;
    @Mock
    private VenueValidator venueValidator;
    @InjectMocks
    private VenueServiceImpl venueService;
    private Venue venue;
    private Venue updated;

    @BeforeEach
    void setup() {
        venue = createWithSetParameters();
        updated = createWithSetParameters();
        updated.setAddress("newaddress");
    }

    @Test
    void creatingVenueAndAddingItSavesInRepo() {
        Mockito.when(venueRepository.save(venue)).thenReturn(venue);

        Venue created = venueService.create(venue);
        assertThat(created).isSameAs(venue);
    }

    @Test
    void findingAllVenuesReturnsListOfThem() {
        Mockito.when(venueRepository.findAll()).thenReturn(List.of(venue));

        List<Venue> found = venueRepository.findAll();

        assertThat(found).containsExactly(venue).hasSize(1);
    }

    @Test
    void findingVenueByIdReturnsIt() {
        when(venueRepository.findById(VENUE_ID)).thenReturn(Optional.of(venue));

        Venue toFindById = venueService.findById(VENUE_ID);

        assertThat(toFindById).isSameAs(venue);
    }

    @Test
    void deleteByIdSuccessfullyRemovesFromRepository() {
        when(venueRepository.findById(VENUE_ID)).thenReturn(Optional.of(venue));

        venueService.deleteById(VENUE_ID);
        List<Venue> remaining = venueService.findFilteredBy(null, null, null);

        assertThat(remaining).isEmpty();
    }

    @Test
    void updatingExistingVenueSavesItWithNewAddressCityPhoneAndEmail() {
        when(venueRepository.save(venue)).thenReturn(venue);
        when(venueRepository.findById(VENUE_ID)).thenReturn(Optional.of(venue));

        Venue updatedAndSaved = venueService.updateById(VENUE_ID, updated);

        assertThat(updatedAndSaved.getAddress()).isEqualTo(venue.getAddress());
        assertThat(updatedAndSaved.getCity()).isEqualTo(venue.getCity());
        assertThat(updatedAndSaved.getPhoneNumber()).isEqualTo(venue.getPhoneNumber());
        assertThat(updatedAndSaved.getEmail()).isEqualTo(venue.getEmail());
    }

    @Test
    void callingFindByIdOnNoneExistentVenueThrowsException() {
        when(venueRepository.findById(VENUE_ID)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            venueService.findById(VENUE_ID);
        });
    }

    @Test
    void applyingFiltersReturnsTheCorrectFilteredVenues() {
        when(venueRepository.findFilteredByTypeCityAndRating(VenueType.HOTEL, "Burgas",
                BigDecimal.valueOf(1))).thenReturn(List.of(venue));

        List<Venue> filteredByAllParameters = venueService.findFilteredBy(VenueType.HOTEL,
                "Burgas", BigDecimal.valueOf(1));

        assertThat(filteredByAllParameters.get(0)).isSameAs(venue);
    }

    @Test
    void applyingFiltersOfNonExistentVenuesReturnsEmptyList() {
        when(venueRepository.findFilteredByTypeCityAndRating(VenueType.RESTAURANT, "Krichim",
                BigDecimal.valueOf(4))).thenReturn(List.of());

        List<Venue> filteredByAllParameters = venueService.findFilteredBy(VenueType.RESTAURANT,
                "Krichim", BigDecimal.valueOf(4));

        assertThat(filteredByAllParameters).isEmpty();
    }
}