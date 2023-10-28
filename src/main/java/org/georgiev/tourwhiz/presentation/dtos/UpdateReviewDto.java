package org.georgiev.tourwhiz.presentation.dtos;

import java.math.BigDecimal;

public class UpdateReviewDto {

    private String text;
    private BigDecimal rating;

    public UpdateReviewDto(String text, BigDecimal rating) {
        this.text = text;
        this.rating = rating;
    }

    public UpdateReviewDto() {
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }
}
