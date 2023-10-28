package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.data.models.User;
import org.georgiev.tourwhiz.data.models.Venue;

import java.math.BigDecimal;

public class ReviewTestFactory {

    public static Long REVIEW_ID = 1L;
    public static Venue REVIEW_VENUE = VenueTestFactory.createWithSetParameters();
    public static User REVIEW_USER = UserTestFactory.createWithSetParameters();

    public static Review createWithSetParameters() {
        Review review = new Review();
        review.setId(REVIEW_ID);
        review.setPublishedOnWhenCreating();
        review.setText("random");
        review.setRating(BigDecimal.ONE);

        REVIEW_VENUE.addReview(review);

        return review;
    }

    public static Review createWithUpdatedParameters() {
        Review review = createWithSetParameters();
        review.setText("updated");
        review.setRating(BigDecimal.valueOf(4.1));
        review.setUpdatedOnWhenUpdating();

        return review;
    }
}
