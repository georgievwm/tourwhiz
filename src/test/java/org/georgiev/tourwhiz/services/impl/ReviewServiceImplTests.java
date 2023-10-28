package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.repositories.ReviewRepository;
import org.georgiev.tourwhiz.factories.UserTestFactory;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.validators.ReviewValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.georgiev.tourwhiz.factories.ReviewTestFactory.*;
import static org.georgiev.tourwhiz.factories.UserTestFactory.USER_ID;
import static org.georgiev.tourwhiz.factories.VenueTestFactory.VENUE_ID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTests {

    @Mock
    AuthenticationServiceImpl authenticationService;
    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private ReviewValidator reviewValidator;
    @Mock
    private VenueServiceImpl venueService;
    @Mock
    private UserServiceImpl userService;
    @InjectMocks
    private ReviewServiceImpl reviewService;
    private Review review;
    private Review updated;
    private TourWhizUserPrincipal userPrincipal;
    private User user;

    @BeforeEach
    void setUp() {
        review = createWithSetParameters();
        updated = createWithUpdatedParameters();

        user = UserTestFactory.createWithSetParameters();
        userPrincipal = new TourWhizUserPrincipal(user.getId(), user.getUsername());

        review.setUser(user);
        user.addReview(review);
    }


    @Test
    void creatingReviewSavesAndReturnsIt() {
        when(reviewRepository.save(review)).thenReturn(review);
        when(venueService.findById(VENUE_ID)).thenReturn(REVIEW_VENUE);
        when(userService.findById(USER_ID)).thenReturn(user);
        when(authenticationService.getCurrentPrincipal()).thenReturn(userPrincipal);

        Review created = reviewService.create(VENUE_ID, review);

        assertThat(created).isSameAs(review);
    }

    @Test
    void findReviewByVenueIdReturnsIt() {
        when(reviewRepository.findAllByVenueId(VENUE_ID)).thenReturn(List.of(review));

        List<Review> reviews = reviewService.findAllByVenueId(VENUE_ID);

        assertThat(reviews).containsExactly(review).hasSize(1);
    }

    @Test
    void updateReviewForGivenVenueUpdatesAndReturnsIt() {
        when(reviewRepository.save(review)).thenReturn(review);
        when(reviewRepository.findById(REVIEW_ID)).thenReturn(Optional.of(review));
        when(authenticationService.getCurrentPrincipal()).thenReturn(userPrincipal);

        Review updatedReview = reviewService.update(VENUE_ID, REVIEW_ID, updated);

        assertThat(updatedReview.getId()).isEqualTo(REVIEW_ID);
        assertThat(updatedReview.getText()).isEqualTo(updated.getText());
        assertThat(updatedReview.getVenue()).isSameAs(updated.getVenue());
        assertThat(updatedReview.getRating()).isEqualTo(updated.getRating());
        assertThat(updatedReview.getPublishedOn()).isEqualTo(updated.getPublishedOn());
    }

    @Test
    void deletingReviewSuccessfullyRemovesFromRepository() {
        when(reviewRepository.findById(VENUE_ID)).thenReturn(Optional.of(review));
        when(authenticationService.getCurrentPrincipal()).thenReturn(userPrincipal);
        doNothing().when(reviewRepository).delete(review);

        reviewService.delete(VENUE_ID, REVIEW_ID);
        verify(reviewRepository, times(1)).delete(review);
    }

    @Test
    void findCountForGivenVenueReturnsCorrectNumberOfReviews() {
        when(reviewRepository.findAllByVenueId(VENUE_ID)).thenReturn(List.of(review));
        when(reviewRepository.countByVenueId(VENUE_ID)).thenReturn(1L);
        when(venueService.findById(VENUE_ID)).thenReturn(REVIEW_VENUE);

        List<Review> reviews = reviewService.findAllByVenueId(VENUE_ID);
        Long reviewCount = reviewService.findCountByVenueId(VENUE_ID);

        assertThat(reviews).containsExactly(review);
        assertThat(reviews).hasSize(reviewCount.intValue());
    }

    @Test
    void findAverageRatingForGivenVenueReturnsCorrectValue() {
        when(reviewRepository.findAllByVenueId(VENUE_ID)).thenReturn(List.of(review));

        List<Review> reviews = reviewService.findAllByVenueId(VENUE_ID);
        BigDecimal expectedRating = review.getRating();
        BigDecimal sumRating = reviews.stream().map(Review::getRating).reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal avgRating = sumRating.divide(BigDecimal.valueOf(reviews.size()), RoundingMode.DOWN);

        assertThat(avgRating).isEqualTo(expectedRating);
    }

}
