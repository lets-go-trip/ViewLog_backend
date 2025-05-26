package com.trip.viewlog.review.domain;

import com.trip.viewlog.attraction.domain.Attraction;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReviewSummary {
    private final Long id;
    private final Attraction attraction;
    private final String summary;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public ReviewSummary(Long id, Attraction attraction, String summary, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.attraction = attraction;
        this.summary = summary;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static ReviewSummary from(Attraction attraction, String summary) {
        return ReviewSummary.builder()
                .attraction(attraction)
                .summary(summary)
                .build();
    }

    public ReviewSummary update(Attraction attraction, String summary) {
        return ReviewSummary.builder()
                .id(id)
                .attraction(attraction)
                .summary(summary)
                .build();
    }
}
