package org.georgiev.tourwhiz.presentation.dtos;

import java.math.BigDecimal;
import java.time.Instant;

public class ReviewDto {

    private String text;
    private Instant publishedOn;
    private Instant updatedOn;
    private BigDecimal rating;

    public ReviewDto(String text, Instant publishedOn, Instant updatedOn, BigDecimal rating) {
        this.text = text;
        this.publishedOn = publishedOn;
        this.updatedOn = updatedOn;
        this.rating = rating;
    }

    public ReviewDto() {
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

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
