package org.georgiev.tourwhiz.validators;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ReviewValidator {

    public void validateText(Review review) {
        final String text = review.getText();

        if (text.isBlank() || text.length() > 4096) {
            throw new ValidationException("The 'text' field character length cannot exceed 4096 or be equal to zero.");
        }
    }

    public void validateRating(Review review) {
        final BigDecimal rating = review.getRating();

        if (rating == null || rating.compareTo(BigDecimal.valueOf(1)) < 0 || rating.compareTo(BigDecimal.valueOf(5)) > 0) {
            throw new ValidationException("Rating field value cannot be less than 1, more than 5 or empty.");
        }
    }

    public void validateHasSameVenue(Long venueId, Review review) {
        if (!review.hasVenueWithId(venueId)) {
            throw new IllegalArgumentException("Review with ID = %s, associated with venue with ID = %s was not found."
                    .formatted(review.getId(), venueId));
        }
    }

    public void validateOnCreateAndUpdate(Review review) {
        validateText(review);
        validateRating(review);
    }
}
