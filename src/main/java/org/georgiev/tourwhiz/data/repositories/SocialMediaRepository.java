package org.georgiev.tourwhiz.data.repositories;

import org.georgiev.tourwhiz.data.models.SocialMedia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SocialMediaRepository extends JpaRepository<SocialMedia, Long> {

    List<SocialMedia> findAllByVenueId(Long venueId);
}
