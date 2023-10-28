package org.georgiev.tourwhiz.data.repositories;

import org.georgiev.tourwhiz.data.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByVenueId(Long venueId);

    Long countByVenueId(Long venueId);

    List<Review> findAllByUserId(Long userId);

    @Query("""
               SELECT AVG(r.rating)
               FROM Review r
               WHERE
               r.venue.id = :venueId
            """)
    BigDecimal findAverageRatingByVenueId(Long venueId);
}
