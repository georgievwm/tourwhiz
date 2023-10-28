package org.georgiev.tourwhiz.data.models;

import jakarta.persistence.*;

@Entity
@Table(name = "social_medias")
public class SocialMedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SocialMediaType type;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @Column(name = "webpage", nullable = false)
    private String webpage;

    public SocialMediaType getType() {
        return type;
    }

    public void setType(SocialMediaType type) {
        this.type = type;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getWebpage() {
        return this.webpage;
    }

    public void setWebpage(String webpage) {
        this.webpage = webpage;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean hasVenueWithId(Long venueId) {
        return this.venue.getId().equals(venueId);
    }
}
