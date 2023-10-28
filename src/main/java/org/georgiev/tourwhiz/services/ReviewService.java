package org.georgiev.tourwhiz.services;

import org.georgiev.tourwhiz.data.models.Review;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewService {

    List<Review> findAllByVenueId(Long venueId);

    Review create(Long venueId, Review review);

    Review update(Long venueId, Long reviewId, Review updatedReview);

    void delete(Long venueId, Long reviewId);

    BigDecimal calculateAverageRatingByVenueId(Long venueId);

    Long findCountByVenueId(Long venueId);

    Review findById(Long reviewId);

}
