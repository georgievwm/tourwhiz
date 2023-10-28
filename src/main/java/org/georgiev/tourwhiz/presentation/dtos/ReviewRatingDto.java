package org.georgiev.tourwhiz.presentation.dtos;

import java.math.BigDecimal;

public class ReviewRatingDto {

    private Long numberOfReviews;
    private BigDecimal averageRating;

    public ReviewRatingDto(Long numberOfReviews, BigDecimal averageRating) {
        this.numberOfReviews = numberOfReviews;
        this.averageRating = averageRating;
    }

    public ReviewRatingDto() {
    }

    public Long getNumberOfReviews() {
        return numberOfReviews;
    }

    public void setNumberOfReviews(Long numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    public BigDecimal getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(BigDecimal averageRating) {
        this.averageRating = averageRating;
    }
}
