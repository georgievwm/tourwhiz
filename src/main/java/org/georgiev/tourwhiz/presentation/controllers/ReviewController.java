package org.georgiev.tourwhiz.presentation.controllers;

import org.georgiev.tourwhiz.data.models.Review;
import org.georgiev.tourwhiz.mappers.ReviewMapper;
import org.georgiev.tourwhiz.presentation.dtos.ReviewDto;
import org.georgiev.tourwhiz.presentation.dtos.ReviewRatingDto;
import org.georgiev.tourwhiz.presentation.dtos.UpdateReviewDto;
import org.georgiev.tourwhiz.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    public ReviewController(ReviewService reviewService, ReviewMapper reviewMapper) {
        this.reviewService = reviewService;
        this.reviewMapper = reviewMapper;
    }

    @GetMapping("/venues/{venueId}/reviews")
    public ResponseEntity<List<ReviewDto>> findAll(@PathVariable Long venueId) {
        final List<ReviewDto> reviews = reviewService.findAllByVenueId(venueId).stream().map(reviewMapper::toDto).toList();

        return ResponseEntity.ok(reviews);
    }

    @PostMapping("/venues/{venueId}/reviews")
    public ResponseEntity<ReviewDto> create(@PathVariable Long venueId, @RequestBody ReviewDto reviewDto) {
        final Review created = reviewService.create(venueId, reviewMapper.dtoToEntity(reviewDto));
        final ReviewDto createdDto = reviewMapper.toDto(created);

        return ResponseEntity.ok(createdDto);
    }

    @PutMapping("/venues/{venueId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDto> update(@PathVariable Long venueId, @PathVariable Long reviewId,
                                            @RequestBody UpdateReviewDto updatedReviewDto) {
        final Review toUpdate = reviewMapper.updateDtoToEntity(updatedReviewDto);
        final Review updated = reviewService.update(venueId, reviewId, toUpdate);
        final ReviewDto updatedDto = reviewMapper.toDto(updated);

        return ResponseEntity.ok(updatedDto);
    }

    @DeleteMapping("/venues/{venueId}/reviews/{reviewId}")
    public ResponseEntity<Void> delete(@PathVariable Long venueId, @PathVariable Long reviewId) {
        reviewService.delete(venueId, reviewId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/venues/{venueId}/rating")
    public ResponseEntity<ReviewRatingDto> findCountAndAverageRating(@PathVariable Long venueId) {
        Long reviewCount = reviewService.findCountByVenueId(venueId);
        BigDecimal avgRating = reviewService.calculateAverageRatingByVenueId(venueId);

        ReviewRatingDto reviewRatingDto = new ReviewRatingDto(reviewCount, avgRating);

        return ResponseEntity.ok(reviewRatingDto);
    }

}
