package com.trip.viewlog.review.controller.inputport;

import com.trip.viewlog.review.domain.ReviewSummary;

public interface ReviewSummaryService {
    ReviewSummary getOrCreateOrUpdate(Long attractionId);
}
