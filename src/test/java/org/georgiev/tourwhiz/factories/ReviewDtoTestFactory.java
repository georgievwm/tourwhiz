package org.georgiev.tourwhiz.factories;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.presentation.dtos.ReviewDto;

public class ReviewDtoTestFactory {

    public static Review REVIEW_TO_DTO = ReviewTestFactory.createWithSetParameters();

    public static ReviewDto createWithSetParameters() {

        return new ReviewDto(REVIEW_TO_DTO.getText(), REVIEW_TO_DTO.getPublishedOn(),
                REVIEW_TO_DTO.getUpdatedOn(), REVIEW_TO_DTO.getRating());
    }
}
