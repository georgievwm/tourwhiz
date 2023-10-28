package org.georgiev.tourwhiz.data.repositories;

import org.georgiev.tourwhiz.data.models.Venue;
import org.georgiev.tourwhiz.data.models.VenueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    @Query("""
              SELECT v
              FROM Venue v
              WHERE
                (:type = NULL OR v.type = :type) AND
                (:city = NULL OR v.city = :city) AND
                (:rating = NULL or v.id IN
                   (SELECT r.venue.id
                   FROM Review r
                   WHERE
                   (SELECT AVG (r.rating)
                   FROM Review r
                   WHERE r.venue.id = v.id)
                   > :rating))
            """)
    List<Venue> findFilteredByTypeCityAndRating(@Param("type") VenueType type,
                                                @Param("city") String city,
                                                @Param("rating") BigDecimal rating);

}
