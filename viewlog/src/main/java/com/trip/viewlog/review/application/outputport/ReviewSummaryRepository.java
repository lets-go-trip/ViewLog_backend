package com.trip.viewlog.review.application.outputport;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.review.domain.ReviewSummary;

import java.util.Optional;

public interface ReviewSummaryRepository {
    ReviewSummary save(ReviewSummary reviewSummary);

    Optional<ReviewSummary> findByAttraction(Attraction attraction);
}
