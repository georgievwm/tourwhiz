package org.georgiev.tourwhiz.mappers;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.presentation.dtos.ReviewDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateReviewDto;
import org.springframework.stereotype.Component;

@Component
public class ReviewMapper {

    public Review dtoToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setPublishedOn(reviewDto.getPublishedOn());
        review.setText(reviewDto.getText());
        review.setRating(reviewDto.getRating());

        return review;
    }

    public Review updateDtoToEntity(UpdateReviewDto updateReviewDto) {
        Review review = new Review();
        review.setText(updateReviewDto.getText());
        review.setRating(updateReviewDto.getRating());

        return review;
    }

    public ReviewDto toDto(Review review) {
        return new ReviewDto(review.getText(), review.getPublishedOn(), review.getUpdatedOn(), review.getRating());
    }

}
