package org.georgiev.tourwhiz.services.impl;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.repositories.ReviewRepository;
import org.georgiev.tourwhiz.exceptions.ResourceNotFoundException;
import org.georgiev.tourwhiz.exceptions.UnauthorizedActionException;
import org.georgiev.tourwhiz.security.TourWhizUserPrincipal;
import org.georgiev.tourwhiz.services.AuthenticationService;
import org.georgiev.tourwhiz.services.ReviewService;
import org.georgiev.tourwhiz.services.UserService;
import org.georgiev.tourwhiz.services.VenueService;
import org.georgiev.tourwhiz.validators.ReviewValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewServiceImpl.class);
    private final ReviewRepository reviewRepository;
    private final VenueService venueService;
    private final ReviewValidator reviewValidator;
    private final AuthenticationService authenticationService;
    private final UserService userService;


    public ReviewServiceImpl(ReviewRepository reviewRepository, VenueService venueService, ReviewValidator reviewValidator, AuthenticationService authenticationService, UserService userService) {
        this.reviewRepository = reviewRepository;
        this.venueService = venueService;
        this.reviewValidator = reviewValidator;
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @Override
    public List<Review> findAllByVenueId(Long venueId) {
        return reviewRepository.findAllByVenueId(venueId);
    }

    @Transactional
    @Override
    public Review create(Long venueId, Review review) {
        reviewValidator.validateOnCreateAndUpdate(review);

        Venue venueToSaveIn = venueService.findById(venueId);

        final TourWhizUserPrincipal principal = authenticationService.getCurrentPrincipal();
        final User currentUser = userService.findById(principal.getId());

        currentUser.addReview(review);
        venueToSaveIn.addReview(review);

        return reviewRepository.save(review);
    }

    @Transactional
    @Override
    public Review update(Long venueId, Long reviewId, Review updatedReview) {
        reviewValidator.validateOnCreateAndUpdate(updatedReview);

        Review existingReview = findById(reviewId);
        User reviewPublisher = existingReview.getUser();

        reviewValidator.validateHasSameVenue(venueId, existingReview);

        final TourWhizUserPrincipal principal = authenticationService.getCurrentPrincipal();

        if (!principal.getUsername().equals(reviewPublisher.getUsername())) {
            LOGGER.warn("The current user is not authorized to edit this review as he is not the one who created it.");

            throw new UnauthorizedActionException("You do not have permission to edit other users' reviews.");
        }

        existingReview.setText(updatedReview.getText());
        existingReview.setRating(updatedReview.getRating());

        return reviewRepository.save(existingReview);
    }

    @Transactional
    @Override
    public void delete(Long venueId, Long reviewId) {
        Review reviewToDelete = findById(reviewId);

        final User reviewPublisher = reviewToDelete.getUser();

        reviewValidator.validateHasSameVenue(venueId, reviewToDelete);

        final TourWhizUserPrincipal principal = authenticationService.getCurrentPrincipal();

        if (!principal.getUsername().equals(reviewPublisher.getUsername())) {
            LOGGER.warn("The current user is not authorized to delete this review as he is not the one who created it.");

            throw new UnauthorizedActionException("You do not have permission to delete other users' reviews.");
        }

        reviewRepository.delete(reviewToDelete);
    }

    @Override
    public BigDecimal calculateAverageRatingByVenueId(Long venueId) {
        Venue venue = venueService.findById(venueId);

        return reviewRepository.findAverageRatingByVenueId(venue.getId());
    }

    @Override
    public Long findCountByVenueId(Long venueId) {
        Venue venue = venueService.findById(venueId);

        return reviewRepository.countByVenueId(venue.getId());
    }

    @Override
    public Review findById(Long reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() ->
                new ResourceNotFoundException(Review.class, reviewId));
    }
}
