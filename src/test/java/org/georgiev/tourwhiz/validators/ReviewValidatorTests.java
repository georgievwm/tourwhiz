package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.georgiev.tourwhiz.factories.ReviewTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.georgiev.tourwhiz.factories.VenueTestFactory.NON_EXISTING_VENUE_ID;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class ReviewValidatorTests {

    private final ReviewValidator reviewValidator = new ReviewValidator();
    private Review review;

    @BeforeEach
    void setUp() {
        review = ReviewTestFactory.createWithSetParameters();
    }

    @Test
    void validateCreatingAndUpdatingWithBlankTextParameterThrowsException() {
        review.setText("");

        assertThrows(ValidationException.class, () -> {
            reviewValidator.validateOnCreateAndUpdate(review);
        });
    }

    @Test
    void validateCreatingAndUpdatingWithCharactersExceedingTheLimitParameterThrowsException() {
        review.setText("x".repeat(5000));

        assertThrows(ValidationException.class, () -> {
            reviewValidator.validateOnCreateAndUpdate(review);
        });
    }

    @Test
    void validateNonExistentReviewForGivenVenueThrowsException() {
        assertThrows(IllegalArgumentException.class, () ->
                reviewValidator.validateHasSameVenue(NON_EXISTING_VENUE_ID, review));
    }

    @Test
    void validateCreatingOrUpdatingReviewWithNegativeRatingThrowsException() {
        review.setRating(BigDecimal.valueOf(-13.2));

        assertThrows(ValidationException.class, () -> {
            reviewValidator.validateOnCreateAndUpdate(review);
        });
    }

    @Test
    void validateCreatingOrUpdatingReviewWithRatingValueHigherThan5ThrowsException() {
        review.setRating(BigDecimal.valueOf(6));

        assertThrows(ValidationException.class, () -> {
            reviewValidator.validateOnCreateAndUpdate(review);
        });
    }

    @Test
    void validateCreatingOrUpdatingReviewWithNullRatingValueThrowsException() {
        review.setRating(null);

        assertThrows(ValidationException.class, () -> {
            reviewValidator.validateOnCreateAndUpdate(review);
        });
    }
}
