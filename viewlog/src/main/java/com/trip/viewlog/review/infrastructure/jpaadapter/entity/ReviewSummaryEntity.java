package com.trip.viewlog.review.infrastructure.jpaadapter.entity;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.global.entity.BaseTimeEntity;
import com.trip.viewlog.review.domain.ReviewSummary;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "review_summary")
@Getter
public class ReviewSummaryEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "attraction_id")
    @OneToOne
    private AttractionEntity attractionEntity;

    private String summary;

    public static ReviewSummaryEntity from(ReviewSummary reviewSummary) {
        ReviewSummaryEntity reviewSummaryEntity = new ReviewSummaryEntity();
        reviewSummaryEntity.id = reviewSummary.getId();
        reviewSummaryEntity.attractionEntity = AttractionEntity.from(reviewSummary.getAttraction());
        reviewSummaryEntity.summary = reviewSummary.getSummary();
        return reviewSummaryEntity;
    }

    public ReviewSummary toModel() {
        return ReviewSummary.builder()
                .id(id)
                .attraction(attractionEntity.toModel())
                .summary(summary)
                .createdAt(getCreatedAt())
                .updatedAt(getUpdatedAt())
                .build();
    }
}
