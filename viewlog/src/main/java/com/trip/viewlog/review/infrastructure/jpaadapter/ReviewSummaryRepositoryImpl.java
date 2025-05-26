package com.trip.viewlog.review.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.domain.Attraction;
import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.review.application.outputport.ReviewSummaryRepository;
import com.trip.viewlog.review.domain.ReviewSummary;
import com.trip.viewlog.review.infrastructure.jpaadapter.entity.ReviewSummaryEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ReviewSummaryRepositoryImpl implements ReviewSummaryRepository {
    private final ReviewSummaryJpaRepository reviewSummaryJpaRepository;

    @Override
    public ReviewSummary save(ReviewSummary reviewSummary) {
        return reviewSummaryJpaRepository.save(ReviewSummaryEntity.from(reviewSummary)).toModel();
    }

    @Override
    public Optional<ReviewSummary> findByAttraction(Attraction attraction) {
        return reviewSummaryJpaRepository.findByAttractionEntity(AttractionEntity.from(attraction)).map(ReviewSummaryEntity::toModel);
    }
}
