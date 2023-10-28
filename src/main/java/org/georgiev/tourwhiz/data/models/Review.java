package org.georgiev.tourwhiz.data.models;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "published_on", nullable = false)
    private Instant publishedOn;

    @Column(name = "updated_on")
    private Instant updatedOn;

    @ManyToOne
    @JoinColumn(name = "venue_id", nullable = false)
    private Venue venue;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "rating", nullable = false)
    private BigDecimal rating;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getPublishedOn() {
        return publishedOn;
    }

    public void setPublishedOn(Instant publishedOn) {
        this.publishedOn = publishedOn;
    }

    public Instant getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Instant updatedOn) {
        this.updatedOn = updatedOn;
    }

    public boolean hasVenueWithId(Long venueId) {
        return this.venue.getId().equals(venueId);
    }

    @PrePersist
    public void setPublishedOnWhenCreating() {
        this.publishedOn = Instant.now();
    }

    @PreUpdate
    public void setUpdatedOnWhenUpdating() {
        this.updatedOn = Instant.now();
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
