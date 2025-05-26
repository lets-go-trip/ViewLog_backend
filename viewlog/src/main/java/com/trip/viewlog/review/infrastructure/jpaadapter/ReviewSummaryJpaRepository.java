package com.trip.viewlog.review.infrastructure.jpaadapter;

import com.trip.viewlog.attraction.infrastructure.jpaadapter.entity.AttractionEntity;
import com.trip.viewlog.review.infrastructure.jpaadapter.entity.ReviewSummaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewSummaryJpaRepository extends JpaRepository<ReviewSummaryEntity, Long> {
   Optional<ReviewSummaryEntity> findByAttractionEntity(AttractionEntity attractionEntity);
}
