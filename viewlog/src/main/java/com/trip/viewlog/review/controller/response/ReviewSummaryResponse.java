package com.trip.viewlog.review.controller.response;

import com.trip.viewlog.review.domain.ReviewSummary;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class ReviewSummaryResponse {
    private String summary;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ReviewSummaryResponse from(ReviewSummary reviewSummary) {
        return ReviewSummaryResponse.builder()
                .summary(reviewSummary.getSummary())
                .createdAt(reviewSummary.getCreatedAt())
                .updatedAt(reviewSummary.getUpdatedAt())
                .build();
    }
}
